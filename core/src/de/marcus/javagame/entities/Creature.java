package de.marcus.javagame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Marcus
 * <p>
 * Entity System Creature
 * <p>
 * Intended to be used for every living Entity (Animals, Enemys, Players)
 */

@Getter
@Setter
@NoArgsConstructor
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


    @JsonIgnore
    List<Animation<TextureRegion>> animations;
    /**
     * Animations
     * 0 = idle
     * 1 = forward
     * 2 = backward
     * 3 = x movement
     * Rest doesn't matter
     */

    @JsonIgnore
    private int activeAnimation;

    @JsonIgnore
    private boolean mirrorAnimations;


    private float movementSpeed;

    @JsonIgnore
    private boolean isDead;

    //TODO: ADD SOMETHING FOR DROPS


    private boolean invincible;
    /**
     * These mean if the creature can loose hunger or thirst.
     */
    private boolean hungry;
    private boolean thirsty;

    boolean test = true;


    public Creature(float posX, float posY, Texture texture, int maxHealth, int maxHunger, int maxArmor, int maxThirst, float movementSpeed, List<Animation<TextureRegion>> animations) {
        super(posX, posY, texture);
        this.maxHealth = maxHealth;
        this.maxHunger = maxHunger;
        this.maxArmor = maxArmor;
        this.maxThirst = maxThirst;
        health = maxHealth;
        hunger = maxHunger;
        armor = maxArmor;
        thirst = maxThirst;
        this.movementSpeed = movementSpeed;
        effects = new LinkedList<>();
        this.animations = animations;
    }

    public void die(Weapon cause) {
        /*
         * TODO: Drop Loot
         */
    }

    public void update(float delta) {

        List<StatusEffect> toRemove = new LinkedList<>();
        for (StatusEffect statusEffect : effects) {
            boolean b = statusEffect.decrementTimer(Math.round(delta * 1000), this);
            if(b)
            {
                toRemove.add(statusEffect);
            }
        }

        effects.removeAll(toRemove);
    }


    public void render(SpriteBatch batch, float passedAnimationTime, float width, float height) {
        update(Gdx.graphics.getDeltaTime());
        Animation<TextureRegion> textureRegionAnimation = animations.get(activeAnimation);
        TextureRegion keyFrame = textureRegionAnimation.getKeyFrame(passedAnimationTime, true);

        //I don't know either
        if (mirrorAnimations && !keyFrame.isFlipX()) {
//                System.out.println("flip!");
            keyFrame.flip(true, false);
        } else if (!mirrorAnimations && keyFrame.isFlipX()) {
            keyFrame.flip(true, false);
//                System.out.println("unflip");
        }

//            System.out.println("flip " + keyFrame.isFlipX() + " cause " + mirrorAnimations);
        batch.draw(keyFrame, position.x, position.y, width, height);
    }

    public void applyEffect(StatusEffect effect) {
        effects.add(effect);
    }

    public void move(float x, float y,boolean attack) {

        //sets the current animation depending on the players direction
        // first check tries to find out if player is moving in y direction, but not in x direction
        // if he is moving in x, it takes the x animation
        // the second one checks if the player is moving in positiv or negative y direction
        if(!attack) {


            activeAnimation = (y == 0 ?
                    (x == 0 ? 0 : 3)
                    : y > 0 ? 1 : 2);

            //Mirrors animation if the active animation is x movement and player is moving left
            mirrorAnimations = activeAnimation == 3 && (!(x > 0));
//        System.out.println(mirrorAnimations);
        }else{
            activeAnimation = (y == 0 ?
                    (x == 0 ? 4 : 5)
                    : y > 0 ? 6 : 7);
            mirrorAnimations = activeAnimation == 5 && (!(x > 0));
        }

        position.set(position.x + (Gdx.graphics.getDeltaTime() * (x * movementSpeed)), position.y + (Gdx.graphics.getDeltaTime() * (y * movementSpeed)));
    }

}
