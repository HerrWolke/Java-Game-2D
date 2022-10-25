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
import java.util.List;

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
    }

    public Inventory() {
        this.inventory = new ArrayList<>();
        this.hotbar = new ArrayList<>(10);
        this.money = 50;

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

            if (remove.getItem().isDeletable()) {
                if (itemsLeft <= 0) {
                    inventoryWindow.setItemAtPosition(slot, null, 0);
                    boolean isThereItemOfType = false;
                    for (InventorySlot inventorySlot : inventory) {
                        if(remove.getItem().equals(inventorySlot.getItem()) && inventorySlot!=remove) {
                            isThereItemOfType = true;
                            break;
                        }
                    }

                    if (!isThereItemOfType) {
                        removeHotbarItemConnectionOfType(remove.getItem());
                    }

                    inventory.remove(remove);
                    moveInventoryItems();
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


    private void removeHotbarItemConnectionOfType(InventoryItem item) {
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < hotbar.size(); i++) {
            if(hotbar.get(i).getItem() != null && hotbar.get(i).getItem().equals(item)) {
                toRemove.add(i);
                System.out.println("removing item " + i + " type is " + item.name());

            }
        }
        removeHotbarItemsCorrectly(toRemove);
        hotbar.forEach(inventorySlot -> {
            System.out.println("slot " + hotbar.indexOf(inventorySlot) + ": " + inventorySlot.getItem());
        });
    }

    private void removeHotbarItemsCorrectly(List<Integer> slotsToReplace) {
        for (int slot : slotsToReplace) {
            hotbar.set(slot, new InventorySlot());
            if (slot != 9) {
                inventoryWindow.setItemIntoHotbar(Math.abs(slot - 9), null, 0);
            } else {
                System.out.println("nine");
                inventoryWindow.setItemIntoHotbar(0, null, 0);
            }
        }
    }

    private void moveInventoryItems() {
        for (InventorySlot slot : inventory) {
            inventoryWindow.setItemAtPosition(inventory.indexOf(slot), slot.getTexture(), slot.getItemCount());
            inventoryWindow.setItemAtPosition(inventory.indexOf(slot) + 1, null, 0);
        }
    }

    public void useItemFromHotbar(int hotbarSlot) {
        InventorySlot hotbarSlotSlot = hotbar.get(hotbarSlot);
        InventoryItem item = hotbarSlotSlot.getItem();
        System.out.println("The item in this slot is " + (item == null ? "" : item.name()));

        if(item != null) {
            for (InventorySlot slot : inventory) {
                if(item.equals(slot.getItem())) {
                    useItem(inventory.indexOf(slot));
                    break;
                }
            }
        }
    }

    public boolean useItem(int selectedItem) {
        if (selectedItem < inventory.size()) {
            InventorySlot slot = inventory.get(selectedItem);
            int itemsLeft = slot.getItemCount() - 1;

            if (slot.getItem().isUsable()) {
                if (itemsLeft <= 0) {
                    removeItem(selectedItem,MAX_ITEM_STACK);
                    System.out.println("removing item as there is no more ");
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
            for (int i = 0; i < inventory.size(); i++) {
                InventorySlot inventorySlot = inventory.get(i);

                if (inventorySlot.getItem().equals(slot.getItem()) && (inventorySlot.getItemCount() + slot.getItemCount()) < inventorySlot.getItem().getMaxStackSize()) {
                    inventorySlot.setItemCount(inventorySlot.getItemCount() + slot.getItemCount());
                    inventoryWindow.setItemAtPosition(inventory.indexOf(inventorySlot), slot.getTexture(), inventorySlot.getItemCount());
                    System.out.println("Adding to existing slot");
                    return true;
                } else if (inventory.size() < INVENTORY_SIZE && i == inventory.size() - 1) {
                    System.out.println("Adding to new slot");
                    inventory.add(slot);
                    inventoryWindow.setItemAtPosition(inventory.size() - 1, slot.getTexture(), slot.getItemCount());
                    return true;
                }
            }
            //This should never happen. At least I hope so...

            return false;
        } else {
            inventory.add(slot);
            System.out.println("Adding to default slot");
            inventoryWindow.setItemAtPosition(0, slot.getTexture(), slot.getItemCount());
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
            inventoryWindow.setItemAtPosition(i, inventorySlot.getTexture(), inventorySlot.getItemCount());
        }

        for (int i = 0; i < hotbar.size(); i++) {
            InventorySlot inventorySlot = hotbar.get(i);

            if (inventorySlot.getItem() != null) {
                if(doesInventoryHaveItemOfType(inventorySlot.getItem())) {

                    inventoryWindow.setItemIntoHotbar(Math.abs(i-9), inventorySlot.getTexture(), inventorySlot.getItemCount());
                } else {
                    removeHotbarItemsCorrectly(List.of(i));
                }
            }
        }
    }

    public boolean isItemEquitable(int selectedItem) {
        if (selectedItem < inventory.size()) {
            return inventory.get(selectedItem).getItem().isHotbarSelectable();
        } else {
            return false;
        }
    }

    public boolean moveItemToQuickbar(int selectedItem, int quickbarSlot) {
        if (selectedItem < inventory.size()) {
            InventorySlot slot = inventory.get(selectedItem);

            if (slot.getItem().isHotbarSelectable()) {
                hotbar.set(quickbarSlot, slot);
                inventoryWindow.addToQuickbar(quickbarSlot, slot.getTexture());

                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void moneyChange(int change) {
        this.money += change;
    }

    public boolean doesInventoryHaveItemOfType(InventoryItem item) {
        for (InventorySlot slot : inventory) {
            if(slot.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int itemAmountOfType(InventoryItem item) {
        int amount = 0;
        for (InventorySlot slot : inventory) {
            if(slot.getItem().equals(item)) {
                amount += slot.getItemCount();
            }
        }
        return amount;
    }
}
