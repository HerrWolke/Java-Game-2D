package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Marcus
 *
 * Entity System: Mage
 *
 * Enemy Mage
 */
public class Mage extends Enemy {
    private DamageType damageType;

    public Mage(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed, int damage, double attackSpeed, float range, DamageType damageType) {
        super(posX, posY, texture, maxHealth, maxHunger, maxArmor, maxThirst, movementSpeed, damage, attackSpeed, range);
        this.damageType = damageType;
    }

    public enum DamageType {
        FIRE, WATER, EARTH, WIND
    }
}
