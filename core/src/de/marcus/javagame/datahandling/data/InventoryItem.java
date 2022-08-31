package de.marcus.javagame.datahandling.data;


public enum InventoryItem {

    COIN(Inventory.MAX_ITEM_STACK);

    InventoryItem(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    private int maxStackSize;


    public enum Attributes {
        USABLE, POTION, WEAPON_MELEE, WEAPON_RANGED
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "maxStackSize=" + maxStackSize +
                '}';
    }
}
