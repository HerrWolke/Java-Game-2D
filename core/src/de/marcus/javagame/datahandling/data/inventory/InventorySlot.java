package de.marcus.javagame.datahandling.data.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class InventorySlot {
    private int itemCount;
    private InventoryItem item;

    @JsonIgnore
    private Texture texture;

    public InventorySlot(InventoryItem item, int itemCount) {
        this.itemCount = itemCount;
        this.item = item;
    }

    public InventorySlot() {

    }

    public void createTexture() {
        System.out.println("items/"+item.name().toLowerCase());
        texture = new Texture("items/"+item.name().toLowerCase()+".png");
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
