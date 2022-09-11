package de.marcus.javagame.datahandling.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.marcus.javagame.datahandling.Loadable;
import de.marcus.javagame.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class TestInventoryDoNotTouch extends Loadable {
    @JsonProperty("inventory_data")
    public List<InventorySlot> inventory;


    @JsonProperty("hotbar_data")
    public List<InventorySlot> hotbar;

    @JsonIgnore
    Player p;

    public TestInventoryDoNotTouch() {
        this.inventory = new ArrayList<>();
        this.hotbar = new ArrayList<>();
    }

    @JsonIgnore
    public void setPlayer(Player player) {
        this.p = player;
    }


    public TestInventoryDoNotTouch(List<InventorySlot> inventory, List<InventorySlot> hotbar) {
        this.inventory = inventory;
        this.hotbar = hotbar;
    }

    @Override
    public String toString() {
        return "TestInventoryDoNotTouch{" +
                "inventory=" + inventory +
                ", hotbar=" + hotbar +
                ", p=" + p +
                '}';
    }
}
