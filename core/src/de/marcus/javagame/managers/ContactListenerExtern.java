package de.marcus.javagame.managers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import de.marcus.javagame.graphics.screens.GameScreen;

public class ContactListenerExtern implements ContactListener {
    GameScreen g;
    public ContactListenerExtern(GameScreen g){
        this.g = g;
    }
    @Override
    public void beginContact(Contact contact) {
        //if(contact.getFixtureA().getBody() == g.entityManager.getPlayer().getPlayerBody() && contact.getFixtureB().getBody() == g.)
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
