package de.marcus.javagame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.marcus.javagame.datahandling.SavedataHandler;
import de.marcus.javagame.datahandling.data.Inventory;
import de.marcus.javagame.datahandling.data.InventoryItem;
import de.marcus.javagame.graphics.InventoryWindow;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.managers.TextureManager;
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
    @JsonIgnore
    private OrthographicCamera camera;

    @JsonIgnore
    private UI ui;




    public Player(float posX, float posY) {
        super(posX, posY, null, 4, 4, 4, 4, 5.0f, Arrays.asList(
                TextureManager.getAnimation("standing_character", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f)
        ));

        inventory = SavedataHandler.load(Inventory.class);
        inventory.setPlayer(this);
        camera = initialiseCamera();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        ui.update(this.getPosition().x,this.getPosition().y);
    }

    @Override
    public void setHealth(int health) {
        System.out.println("set health to " + health);
        super.setHealth(health);
        ui.getHealthBar().setValue(health);
    }

    @JsonIgnore
    public void setUI(Stage stage) {
        this.ui = new UI(stage,this);
    }

    public void setInventoryWindow(InventoryWindow window) {
        inventory.setInventoryWindow(window);
    }

    /**
     * Creates a camera based on the screen aspect ratio
     * @return The cam
     */
    private OrthographicCamera initialiseCamera() {
        //for aspect ratio
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera = new OrthographicCamera(50, 50 * (h / w));
        camera.position.set(getPosition().x, getPosition().y, 0);
        camera.update();


        return camera;
    }

    @Override
    public void move(float x, float y) {
        //updates the player position which is then used to move the camera
        super.move(x, y);
        camera.position.set(position.x, position.y, 0);
        camera.update();
    }

    public void attack() {

    }

    public void block() {

    }

    public void interact() {

    }

    public void attackStop() {
    }

    public void interactStop() {
    }

    public void blockStop() {
    }

    public void useItem(InventoryItem item) {
        System.out.println("using the item");
        StatusEffect effect = null;
        try {
            effect = (StatusEffect) item.getEffect().clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        applyEffect(effect);
    }


}
