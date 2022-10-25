package de.marcus.javagame.datahandling.data.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.marcus.javagame.managers.TextureManager;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class InventorySlot {


    private int itemCount;

    private String uuid;

    private InventoryItem item;


    public InventorySlot(InventoryItem item, int itemCount) {
        this.itemCount = itemCount;
        this.item = item;
        this.uuid = UUID.randomUUID().toString();

    }

    public InventorySlot() {

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

    @JsonIgnore
    public TextureRegion getTexture() {
        if(item != null)
            return TextureManager.getTexture(item.name().toLowerCase());
        else
            return TextureManager.getTexture("placeholder");
    }

    public String getUuid() {
        return uuid == null ? "" : uuid;
    }
}


