package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import de.marcus.javagame.datahandling.data.Inventory;
import de.marcus.javagame.managers.TextureManager;
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
    SpriteBatch batchPlayer;
    SpriteBatch batchPlayerRunning;
    private Inventory inventory;
    private int currentHealth;
    Item currentItem;
    TextureRegion player = TextureManager.getTexture("player");
    int widthRunning = 0;
    int heightRunning = 0;
    int widthPlayer = 100;
    int heightPlayer = 100;
    TextureRegion playerRunning = TextureManager.getFinishedAnimation(true,0.5f,"player_running");
//sprite; wenn wasd flippen, wenn bewegt animation, sonst sprite
    public Player(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed) {
        super(posX, posY, texture, maxHealth, maxHunger, maxArmor, maxThirst, movementSpeed);
        inventory = new Inventory();
        batchPlayer.begin();
        batchPlayer.draw(player,getPosition().x,getPosition().y);
        batchPlayer.end();
        batchPlayerRunning.begin();
        batchPlayerRunning.draw(playerRunning,getPosition().x,getPosition().y,widthRunning,heightRunning);
        batchPlayerRunning.end();

    }




    public void runForwards() {

         move(0,2);
         widthPlayer = 0;
         heightPlayer = 0;
         widthRunning = 100;
         heightRunning = 100;
         //hide player, spiele animation
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


    public void runForwardsStop() {
        widthPlayer = 100;
        heightPlayer = 100;
        widthRunning = 0;
        heightRunning = 0;
    }

    public void runBackwardsStop() {
    }

    public void runRightStop() {
    }

    public void runLeftStop() {
    }

    public void attackStop() {
    }

    public void interactStop() {
    }

    public void blockStop() {
    }
}
