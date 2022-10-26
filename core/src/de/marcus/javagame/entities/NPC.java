package de.marcus.javagame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.marcus.javagame.handlers.DialogHandler;
import de.marcus.javagame.managers.TextureManager;

import java.util.List;

public class NPC extends Creature{
    public Fixture npcFixture;
    public BodyDef npcBodyDef;
    public FixtureDef npcFixtureDef;
    public Body body;
    float x;
    float y;
    public NPC(float x, float y,DialogHandler.Dialogs dialog, Texture t) {
        super(x,  y, t,  1,  1,  0,  0, 0f , null);
        this.x = x;
        this.y = y;
        createCollisionNpc();
    }

    public void callDialog() {
        System.out.println("CALLLL");
          //TODO: Call schreiben
    }
    public void createCollisionNpc() {
        npcBodyDef = new BodyDef();

        npcFixtureDef = new FixtureDef();
        npcBodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1);



        //TODO: position anpassen
        npcBodyDef.position.set(x + 1.3f, y + 1f);

        npcFixtureDef.shape = shape;
        npcFixtureDef.density = 0f;
        npcFixtureDef.friction = 0.0f;


    }
}
