package de.marcus.javagame.datahandling.data.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import de.marcus.javagame.datahandling.Loadable;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.windows.InventoryWindow;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Inventory extends Loadable {
    @JsonIgnore
    public static final int MAX_ITEM_STACK = 64;
    @JsonIgnore
    public static final int MAX_POTION_STACK = 6;
    @JsonIgnore
    public static final int MAX_WEAPON_STACK = 1;
    @JsonIgnore
    public static final int INVENTORY_SIZE = 30;
    @JsonIgnore
    public static final int HOTBAR_SIZE = 10;

    @JsonIgnore
    public static final int INFINITE_STACK = Integer.MAX_VALUE;

    @JsonIgnore
    Player p;

    @JsonProperty("inventory_data")
    public ArrayList<InventorySlot> inventory;

    @JsonProperty("money")
    public int money;

    @JsonProperty("hotbar_data")
    public ArrayList<InventorySlot> hotbar;

    @JsonIgnore
    private InventoryWindow inventoryWindow;

    public Inventory(ArrayList<InventorySlot> inventory, ArrayList<InventorySlot> hotbar) {
        this.inventory = inventory;
        this.hotbar = hotbar;
    }


    @JsonSetter
    public void setHotbar(ArrayList<InventorySlot> hotbar) {
        ArrayList<InventorySlot> list = new ArrayList<>(10);
        list.addAll(hotbar);
        this.hotbar = list;
        System.out.println("size " + hotbar.size());
    }

    public Inventory() {
        System.out.println("Called constructor");
        this.inventory = new ArrayList<>();
        this.hotbar = new ArrayList<>(10);
        this.money = 200;

        p = null;
    }

    @JsonIgnore
    public void setPlayer(Player player) {
        this.p = player;

    }

    public boolean removeItem(int slot, int amount) {
        if (slot < inventory.size()) {
            InventorySlot remove = inventory.get(slot);
            int itemsLeft = remove.getItemCount() - amount;
            System.out.println("items left " + itemsLeft);

            if (remove.getItem().isDeletable()) {
                if (itemsLeft <= 0) {
                    inventory.remove(remove);
                    System.out.println("resetting texture at " + slot);
                    inventoryWindow.setItemAtPosition(slot, null, 0);
                } else {
                    System.out.println("what?");
                    remove.setItemCount(itemsLeft);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean useItem(int selectedItem) {
        if (selectedItem < inventory.size()) {
            InventorySlot slot = inventory.get(selectedItem);
            int itemsLeft = slot.getItemCount() - 1;

            if (slot.getItem().isUsable()) {
                if (itemsLeft < 0) {
                    inventory.remove(selectedItem);

                } else {
                    slot.setItemCount(itemsLeft);
                    inventoryWindow.setItemAtPosition(selectedItem, slot.getTexture(), itemsLeft);
                }
                p.useItem(slot.getItem());
                return true;
            } else {
                return false;
            }
        }

        return true;

    }

    /**
     * @return If the item was added
     */
    public boolean addItem(InventorySlot slot) {
        if (!inventory.isEmpty()) {
            for (InventorySlot inventorySlot : inventory) {
                if (inventorySlot.getItem().equals(slot.getItem()) && (inventorySlot.getItemCount() + slot.getItemCount()) < inventorySlot.getItem().getMaxStackSize()) {
                    inventorySlot.setItemCount(inventorySlot.getItemCount() + slot.getItemCount());
                    inventoryWindow.setItemAtPosition(inventory.size(), slot.getTexture(), slot.getItemCount());
                    return true;
                } else if (inventory.size() < INVENTORY_SIZE) {
                    inventory.add(slot);
                    inventoryWindow.setItemAtPosition(inventory.size(), slot.getTexture(), slot.getItemCount());
                    return true;
                } else {
                    return false;
                }
            }
            //This should never happen. At least I hope so...
            return false;
        } else {
            inventory.add(slot);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory=" + inventory +
                ", hotbar=" + hotbar +
                '}';
    }

    @JsonIgnore
    public void setInventoryWindow(InventoryWindow window) {
        this.inventoryWindow = window;

        for (int i = 0; i < inventory.size(); i++) {
            InventorySlot inventorySlot = inventory.get(i);
            inventorySlot.createTexture();
            inventoryWindow.setItemAtPosition(i, inventorySlot.getTexture(), inventorySlot.getItemCount());
        }

        for (int i = 0; i < hotbar.size(); i++) {
            InventorySlot inventorySlot = hotbar.get(i);

            if (inventorySlot.getItem() != null) {
                inventorySlot.createTexture();
                inventoryWindow.setItemIntoHotbar(i, inventorySlot.getTexture(), inventorySlot.getItemCount());
            }
        }
    }

    public boolean isItemEquitable(int selectedItem) {
        if (selectedItem < inventory.size()) {
            boolean usable = inventory.get(selectedItem).getItem().isHotbarSelectable();
            System.out.println("use " + usable);
            return usable;
        } else {
            return false;
        }
    }

    public boolean moveItemToQuickbar(int selectedItem, int quickbarSlot) {
        if (selectedItem < inventory.size()) {
            InventorySlot slot = inventory.get(selectedItem);
            System.out.println(hotbar.size());

            if (slot.getItem().isHotbarSelectable()) {
                System.out.println("slot " + quickbarSlot);
                hotbar.set(quickbarSlot, slot);

                if(slot.getTexture() == null)
                    slot.createTexture();

                inventoryWindow.addToQuickbar(quickbarSlot, slot.getTexture());

                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
