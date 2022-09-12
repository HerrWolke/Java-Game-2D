package de.marcus.javagame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
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
    InventoryItem currentItem;
    //Collisions
    BodyDef swordBodyDef;
    Body swordBody;
    CircleShape swordShape;
    FixtureDef swordFixtureDef;
    Fixture swordFixture;

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
        super(posX, posY, null, 4, 4, 4, 4, 5.0f, Arrays.asList(
                TextureManager.getAnimation("standing_character", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f),
                TextureManager.getAnimation("running", true, 0.25f)
        ));
        playerBodyDef = new BodyDef();
        circle = new CircleShape();
        playerFixtureDef = new FixtureDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;

        //TODO: Radius ist noch nicht richtig
        circle.setRadius(6f);
        //TODO: wahrscheinlich in Render
        playerBodyDef.position.set(posX,posY);

        playerFixtureDef.shape = circle;
        playerFixtureDef.density = 100f;
        playerFixtureDef.friction = 0.4f;

        swordShape = new CircleShape();
        swordBodyDef = new BodyDef();
        swordFixtureDef = new FixtureDef();
        swordBodyDef.type = BodyDef.BodyType.DynamicBody;
        setSwordPosition();
        swordShape.setRadius(6f);

        swordFixtureDef.shape = swordShape;
        swordFixtureDef.density = 2f;
        swordFixtureDef.friction = 0.2f;
        inventory = SavedataHandler.load(Inventory.class);
        inventory.setPlayer(this);
        camera = initialiseCamera();


    }
    public void setSwordPosition(){
              if(super.getActiveAnimation() == 0 || super.getActiveAnimation() == 4){
                  swordBodyDef.position.set(position.x +6f, position.y );
              }else if(super.getActiveAnimation() == 3 || super.getActiveAnimation() == 5){
                  swordBodyDef.position.set(position.x -6f, position.y );
              }else if(super.getActiveAnimation() == 1 || super.getActiveAnimation() == 6){
                  swordBodyDef.position.set(position.x , position.y +6f);
              }else{
                  swordBodyDef.position.set(position.x , position.y -6f);
              }
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


    public void move(float x, float y) {
        //updates the player position which is then used to move the camera
        super.move(x, y,attack);
        camera.position.set(position.x, position.y, 0);
        camera.update();
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
        StatusEffect effect = null;
        try {
            effect = (StatusEffect) item.getEffect().clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        applyEffect(effect);
    }


}
