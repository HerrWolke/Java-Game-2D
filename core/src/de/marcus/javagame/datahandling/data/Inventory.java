package de.marcus.javagame.datahandling.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.marcus.javagame.datahandling.Loadable;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends Loadable {
    public static final int MAX_ITEM_STACK = 64;
    public static final int MAX_POTION_STACK = 6;
    public static final int MAX_WEAPON_STACK = 1;

    public static final int INVENTORY_SIZE = 30;
    public static final int HOTBAR_SIZE = 10;

    @JsonProperty("inventory_data")
    public List<InventorySlot> inventory;

    @JsonProperty("hotbar_data")
    public List<InventorySlot> hotbar;

    public Inventory(List<InventorySlot> inventory, List<InventorySlot> hotbar) {
        this.inventory = inventory;
        this.hotbar = hotbar;
    }

    public Inventory() {
        this.inventory = new ArrayList<>();
        this.hotbar = new ArrayList<>();
    }

    /**
     * @return If the item was added
     */
    public boolean addItem(InventorySlot slot) {

        if (!inventory.isEmpty()) {
            for (InventorySlot inventorySlot : inventory) {
                if (inventorySlot.getItem().equals(slot.getItem()) && (inventorySlot.getItemCount() + slot.getItemCount()) < inventorySlot.getItem().getMaxStackSize()) {
                    inventorySlot.setItemCount(inventorySlot.getItemCount() + slot.getItemCount());
                    return true;
                } else if (inventory.size() < INVENTORY_SIZE) {
                    inventory.add(slot);
                    return true;
                } else {
                    return false;
                }
            }
            //This should never happen
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
}
