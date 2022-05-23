package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import lombok.*;
import org.graalvm.compiler.lir.CompositeValue;

/**
 * @author Marcus
 *
 * Entity System Creature
 *
 * Intended to be used for every living Entity (Animals, Enemys, Players)
 *
 *
 */

@Getter
@Setter
public class Creature extends Entity{
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
    }
}
