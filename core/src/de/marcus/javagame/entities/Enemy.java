package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Creature {
    private int damage;
    private double attackSpeed;
    private float range;

    public Enemy(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed, int damage, double attackSpeed, float range) {
        super(posX, posY, texture, maxHealth, maxHunger, maxArmor, maxThirst, movementSpeed);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
    }
}
