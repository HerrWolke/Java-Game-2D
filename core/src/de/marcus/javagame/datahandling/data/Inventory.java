package de.marcus.javagame.datahandling.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Inventory {
    public static final int MAX_ITEM_STACK = 64;
    public static final int INVENTORY_SIZE = 24;

    @JsonProperty("inventory_data")
    public List<InventorySlot> inventory;

    public Inventory(List<InventorySlot> inventory) {
        this.inventory = inventory;
    }


}
