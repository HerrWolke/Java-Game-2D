package de.marcus.javagame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import de.marcus.javagame.graphics.screens.GameScreen;


import java.util.ArrayList;

public class ContactListenerExtern implements ContactListener {
    GameScreen g;

    public ContactListenerExtern(GameScreen g) {
        this.g = g;
    }

    @Override
    public void beginContact(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();
        if (body1 == g.entityManager.getPlayer().getPlayerBody() && g.gameWorld.eingang.containsKey(body2) || body2 == g.entityManager.getPlayer().getPlayerBody() && g.gameWorld.eingang.containsKey(body1)) {

            g.gameWorld.setMap(g.gameWorld.eingang.get(body2 == g.entityManager.getPlayer().getPlayerBody() ? body1 : body2).mapType,g.entityManager.getPlayer());

            //TODO: Vllt world clearen, koordinaten setzen
        }
        if ((body1 == g.entityManager.getPlayer().getPlayerBody() && g.getNpcs().containsKey(body2) || body2 == g.entityManager.getPlayer().getPlayerBody() && g.getNpcs().containsKey(body1)) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

              g.getNpcs().get(body2 == g.entityManager.getPlayer().getPlayerBody() ? body1 : body2).callDialog();

            //TODO: Vllt world clearen, koordinaten setzen
        }
        if ((body1 == g.entityManager.getPlayer().getPlayerBody() && g.getItems().containsKey(body2) || body2 == g.entityManager.getPlayer().getPlayerBody() && g.getItems().containsKey(body1)) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (g.getCurrentItem().get(0).body == body1 || g.getCurrentItem().get(0).body == body2)) {

            g.itemFound();

            //TODO: Vllt world clearen, koordinaten setzen
        }
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
