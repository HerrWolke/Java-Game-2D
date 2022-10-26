package de.marcus.javagame.managers;

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
        System.out.println("ze good contact part1");
        if (body1 == g.entityManager.getPlayer().getPlayerBody() && g.gameWorld.eingang.containsKey(body2) || body2 == g.entityManager.getPlayer().getPlayerBody() && g.gameWorld.eingang.containsKey(body1)) {
            System.out.println("ze good contact2");
            g.gameWorld.setMap(g.gameWorld.eingang.get(body2 == g.entityManager.getPlayer().getPlayerBody() ? body1 : body2).mapType,g.entityManager.getPlayer());
            System.out.println("ze good contact3");
            //TODO: Vllt world clearen, koordinaten setzen
        }
        System.out.println("ze good contact");


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
