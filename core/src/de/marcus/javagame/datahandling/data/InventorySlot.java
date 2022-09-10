package de.marcus.javagame.datahandling.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class InventorySlot {
    private int itemCount;
    private InventoryItem item;

    public InventorySlot(InventoryItem item, int itemCount) {
        this.itemCount = itemCount;
        this.item = item;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }
}
