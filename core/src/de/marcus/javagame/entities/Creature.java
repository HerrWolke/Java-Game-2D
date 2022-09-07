package de.marcus.javagame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

/**
 * @author Marcus
 * <p>
 * Entity System Creature
 * <p>
 * Intended to be used for every living Entity (Animals, Enemys, Players)
 */

@Getter
@Setter
public class Creature extends Entity {
    LinkedList<StatusEffect> effects;

    private int health;
    private int maxHealth;

    private int hunger;
    private int maxHunger;

    private int armor;
    private int maxArmor;

    private int thirst;
    private int maxThirst;


    private float movementSpeed;
    private boolean isDead;

    //TODO: ADD SOMETHING FOR DROPS


    private boolean invincible;
    /**
     * These mean if the creature can loose hunger or thirst.
     */
    private boolean hungry;
    private boolean thirsty;

    public Creature(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed) {
        super(posX, posY, texture);
        this.maxHealth = maxHealth;
        this.maxHunger = maxHunger;
        this.maxArmor = maxArmor;
        this.maxThirst = maxThirst;
        this.movementSpeed = movementSpeed;
        effects = new LinkedList<StatusEffect>();
    }

    public void die(Weapon cause) {
        /*
         * TODO: Drop Loot
         */
    }

    public void move(float x, float y) {
        if (x != 0)
            System.out.println(position.x + (x * movementSpeed));
        position.set(position.x + (Gdx.graphics.getDeltaTime() * (x * movementSpeed)), position.y + (Gdx.graphics.getDeltaTime() * (y * movementSpeed)));
    }
}
