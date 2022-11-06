package de.marcus.javagame.entities.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * @author Marcus
 * <p>
 * Entity System: Item
 * <p>
 * Extended Entity for items. On creation, a despawn timer is created (using long / ms) that will delete that
 * item when the timer hits zero
 */
public class Item {
    public Body body;
    public Fixture itemFixture;
    public BodyDef itemBodyDef = new BodyDef();
    public  FixtureDef itemFixtureDef = new FixtureDef();
    float x;
    float y;
    public Item(Vector2 position) {
       this.x = position.x;
       this.y = position.y;
    }

    public void createCollisionItem() {




        itemBodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);



        //TODO: position anpassen
        itemBodyDef.position.set(x + 1.3f, y + 1.3f);

        itemFixtureDef.shape = shape;
        itemFixtureDef.density = 0f;
        itemFixtureDef.friction = 0.0f;


    }






}
