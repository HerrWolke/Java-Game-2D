package de.marcus.javagame.datahandling.data.inventory;

import de.marcus.javagame.EffectType;
import de.marcus.javagame.entities.StatusEffect;
import lombok.Getter;

/**
 * When creating an item with an effect, the duration is set in ms not seconds (seconds * 1000)
 * <p>
 * <b>Items have to have same name as texture (Texture has to be .png)</b>
 */
@Getter
public enum InventoryItem {

    COIN(Inventory.INFINITE_STACK, false, false),
    MAP(Inventory.MAX_WEAPON_STACK, false, false),
    STARTER_SWORD(Inventory.MAX_WEAPON_STACK, false, true),
    HEAL_POTION(Inventory.MAX_POTION_STACK, true, true, new StatusEffect(EffectType.HEAL, 1000)),
    STRENGTH_POTION(Inventory.MAX_POTION_STACK, true, true, new StatusEffect(EffectType.STRENGTH, 1000)),
    SPEED_POTION(Inventory.MAX_POTION_STACK,true,true,new StatusEffect(EffectType.HEAL,1000));

    InventoryItem(int maxStackSize, boolean deletable, boolean hotbarSelectable) {
        this.maxStackSize = maxStackSize;
        this.deletable = deletable;
        this.effect = null;
        this.usable = false;
        this.hotbarSelectable = hotbarSelectable;
    }

    InventoryItem(int maxStackSize, boolean deletable, boolean hotbarSelectable, StatusEffect effect) {
        this.maxStackSize = maxStackSize;
        this.deletable = deletable;
        this.usable = true;
        this.effect = effect;
        this.hotbarSelectable = hotbarSelectable;
    }

    private final int maxStackSize;

    private final boolean hotbarSelectable;
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
