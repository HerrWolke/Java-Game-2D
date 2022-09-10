package de.marcus.javagame.datahandling.data;

import de.marcus.javagame.EffectType;
import de.marcus.javagame.entities.StatusEffect;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum InventoryItem {

    COIN(Inventory.MAX_ITEM_STACK,false,false),
    MAP(Inventory.MAX_WEAPON_STACK,false,true),
    STARTER_SWORD(Inventory.MAX_WEAPON_STACK,false,false),
    HEALTH_POTION(Inventory.MAX_POTION_STACK,true,true,new StatusEffect(EffectType.HEAL,1));

    InventoryItem(int maxStackSize, boolean deletable, boolean usable) {
        this.maxStackSize = maxStackSize;
        this.deletable = deletable;
        this.usable = usable;
        this.effect = null;
    }

    InventoryItem(int maxStackSize, boolean deletable, boolean usable, StatusEffect effect) {
        this.maxStackSize = maxStackSize;
        this.deletable = deletable;
        this.usable = usable;
        this.effect = effect;
    }

    private final int maxStackSize;
    private final boolean deletable;

    private final boolean usable;

    private final StatusEffect effect;


    public enum Attributes {
        USABLE, POTION, WEAPON_MELEE, WEAPON_RANGED
    }


    @Override
    public String toString() {
        return "InventoryItem{" +
                "maxStackSize=" + maxStackSize +
                '}';
    }
}
