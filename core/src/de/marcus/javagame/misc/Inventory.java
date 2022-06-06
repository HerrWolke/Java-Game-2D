package de.marcus.javagame.misc;

public class Inventory {
    public static final int MAX_ITEM_STACK = 64;

    private int size;
    private int slotsFilled;

    public Inventory(int size) {
        this.size = size;
    }
}
