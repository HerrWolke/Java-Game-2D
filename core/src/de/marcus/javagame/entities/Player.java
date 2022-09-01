package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import de.marcus.javagame.datahandling.data.Inventory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcus
 * <p>
 * Entity System Creature
 * <p>
 * Only intended to be used for the player; Only intended to be used ONCE! as this is a singleplayer game (currently)
 */
@Getter
@Setter
public class Player extends Creature {
    private Inventory inventory;
    private int currentHealth;
    Item currentItem;


    public Player(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed) {
        super(posX, posY, texture, maxHealth, maxHunger, maxArmor, maxThirst, movementSpeed);
        inventory = new Inventory();
    }




    public void runForwards() {

    }

    public void runBackwards() {

    }

    public void runLeft() {

    }

    public void runRight() {

    }

    public void attack() {

    }

    public void block() {

    }

    public void interact() {

    }


}
