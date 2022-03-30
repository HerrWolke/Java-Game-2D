package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import de.marcus.javagame.misc.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Creature{
    private Inventory hotbar;
    private Inventory inventory;


    public Player(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed) {
        super(posX, posY, texture, maxHealth, maxHunger, maxArmor, maxThirst, movementSpeed);
        hotbar = new Inventory(5);
        inventory = new Inventory(15);
    }
}