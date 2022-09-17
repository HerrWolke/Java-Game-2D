package de.marcus.javagame.datahandling.data.shop;

import de.marcus.javagame.datahandling.data.inventory.InventoryItem;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Shops {

    POTION_SHOP(ShopItems.HEAL_POTION);

    Shops(ShopItems... items) {
        this.items = Arrays.asList(items);
    }

    private final List<ShopItems> items;

    @Getter
    public enum ShopItems {
        HEAL_POTION(InventoryItem.HEAL_POTION, 5), STRENGTH_POTION(InventoryItem.STRENGTH_POTION, 10);

        ShopItems(InventoryItem inventoryItem, int price) {
            this.inventoryItem = inventoryItem;
            this.price = price;
        }


        private final InventoryItem inventoryItem;
        private final int price;
    }
}
