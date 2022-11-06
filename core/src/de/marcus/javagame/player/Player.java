package de.marcus.javagame.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.marcus.javagame.entities.base.Creature;
import de.marcus.javagame.entities.data.StatusEffect;
import de.marcus.javagame.framework.data.SavedataHandler;
import de.marcus.javagame.player.inventory.Inventory;
import de.marcus.javagame.player.inventory.InventoryItem;
import de.marcus.javagame.player.inventory.InventorySlot;
import de.marcus.javagame.framework.shop.Shops;
import de.marcus.javagame.ui.ui.UI;
import de.marcus.javagame.framework.sound.SoundManager;
import de.marcus.javagame.framework.TextureManager;
import de.marcus.javagame.player.inventory.InventoryWindow;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

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
    InventoryItem currentItem;
    //Collisions
    BodyDef swordBodyDef;
    Body swordBody;
    CircleShape swordShape;
    FixtureDef swordFixtureDef;
    Fixture swordFixture;
    boolean first = true;
    BodyDef playerBodyDef;
    Body playerBody;
    CircleShape circle;
    FixtureDef playerFixtureDef;
    Fixture playerFixture;
    @JsonIgnore
    private OrthographicCamera camera;
    boolean attack;
    @JsonIgnore
    private UI ui;


    public Player(float posX, float posY) {
        super(posX, posY, null, 4, 4, 4, 4, 8.0f, Arrays.asList(
                TextureManager.getCharacterAnimation("standingback", true, 0.2f),
                TextureManager.getCharacterAnimation("walkback", true, 0.2f),
                TextureManager.getCharacterAnimation("walkfront", true, 0.2f),
                TextureManager.getCharacterAnimation("walkright", true, 0.2f)
        ));

        inventory = SavedataHandler.load(Inventory.class);
        System.out.println("Inventory content " +
                inventory.getHotbar() + " inv " + inventory.getHotbar());
        inventory.setPlayer(this);
        camera = initialiseCamera();


    }

    public void tp(float x, float y) {
        camera.position.set(position.x, position.y, 0);
        camera.update();
        playerBody.setTransform(x,y,0);
        this.position.set(x, y);
    }

    public void createCollisionPlayer() {
        playerBodyDef = new BodyDef();
        circle = new CircleShape();
        playerFixtureDef = new FixtureDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1);


        //TODO: Radius ist noch nicht richtig
        circle.setRadius(1f);
        //TODO: wahrscheinlich in Render
        playerBodyDef.position.set(position.x + 1.3f, position.y + 1f);

        playerFixtureDef.shape = shape;
        playerFixtureDef.density = 0f;
        playerFixtureDef.friction = 0.0f;

        swordShape = new CircleShape();
        swordBodyDef = new BodyDef();
        swordFixtureDef = new FixtureDef();
        swordBodyDef.type = BodyDef.BodyType.DynamicBody;
        setSwordPosition();
        swordShape.setRadius(0.5f);

        swordFixtureDef.shape = swordShape;
        swordFixtureDef.density = 0f;
        swordFixtureDef.friction = 0.2f;
    }

    public void setSwordPosition() {
        if (super.getActiveAnimation() == 0 || super.getActiveAnimation() == 4) {
            swordBodyDef.position.set(position.x + 6f, position.y);
        } else if (super.getActiveAnimation() == 3 || super.getActiveAnimation() == 5) {
            swordBodyDef.position.set(position.x - 6f, position.y);
        } else if (super.getActiveAnimation() == 1 || super.getActiveAnimation() == 6) {
            swordBodyDef.position.set(position.x, position.y + 6f);
        } else {
            swordBodyDef.position.set(position.x, position.y - 6f);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        ui.update(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void move(float x, float y, boolean attack1, Body body) {
        playerBody.setLinearVelocity(new Vector2(x * getMovementSpeed(), y * getMovementSpeed()));
        super.move(x, y, attack, playerBody);

        camera.position.set(position.x, position.y, 0);
        camera.update();
    }


    @Override
    public void setHealth(int health) {
        System.out.println("set health to " + health);
        super.setHealth(health);
        ui.getHealthBar().setValue(health);
    }

    @Override
    public void setArmor(int armor) {
        System.out.println("set armor to " + armor);
        super.setArmor(armor);
        ui.getArmorBar().setValue(armor);
    }

    @JsonIgnore
    public void setUI(Stage stage) {
        this.ui = new UI(stage, this);
    }

    public void setInventoryWindow(InventoryWindow window) {
        inventory.setInventoryWindow(window);
    }

    /**
     * Creates a camera based on the screen aspect ratio
     *
     * @return The cam
     */
    private OrthographicCamera initialiseCamera() {
        //for aspect ratio
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera = new OrthographicCamera(50, 50 * (h/w));
        camera.position.set(getPosition().x, getPosition().y, 0);
        camera.update();


        return camera;
    }


    public void attack() {
        attack = true;

    }

    public void block() {

    }

    public void interact() {

    }

    public void attackStop() {
        attack = false;

    }

    public void interactStop() {
    }

    public void blockStop() {
    }

    public void useItem(InventoryItem item) {
        System.out.println("using the item");

        if (item.isUsable()) {
            if (item.getEffect() != null) {
                StatusEffect effect;
                try {
                    effect = (StatusEffect) item.getEffect().clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

                applyEffect(effect);
            } else if (item.name().equals("ARMOR")) {
                setArmor(Math.min(armor + 1, maxArmor));
                SoundManager.playSoundEffect(SoundManager.SoundEffects.EQUIP_ARMOR,false);
            }
        }
    }

    public void respwawn() {
        camera.position.set(position.x, position.y, 0);
        camera.update();
        setPosition(new Vector2(154,113));
        playerBody.setTransform(154,113,0);
        inventory.moneyChange((int) (inventory.money * -0.25));
    }


    public boolean canAfford(String item) {
        Shops.ShopItems itemToBuy = Shops.ShopItems.valueOf(item);
        if (itemToBuy.getPrice() <= inventory.getMoney()) {
            return true;
        } else {
            ui.displayNotification(2000, "Du kannst dir dieses Item nicht leisten!");
            return false;
        }
    }

    public void buyItem(String item) {
        Shops.ShopItems itemToBuy = Shops.ShopItems.valueOf(item);
        boolean b = inventory.addItem(new InventorySlot(itemToBuy.getInventoryItem(), 1));
        inventory.moneyChange(-itemToBuy.getPrice());
        if (!b) {
            ui.displayNotification(2000, "Dein Inventar ist voll!");
        } else {
            System.out.println("playing sound");
            SoundManager.playSoundEffect(SoundManager.SoundEffects.BUY, false, 0.25f);
        }
    }
}
