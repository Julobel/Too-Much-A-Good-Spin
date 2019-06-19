package com.tmags.game.Utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureB().getBody().getUserData().equals("BottomLimit") ){
            contact.getFixtureA().getBody().setLinearVelocity(0,0);
        }

        if (contact.getFixtureB().getBody().getUserData().equals("Enemy")) {
            /*contact.getFixtureB().getBody().applyLinearImpulse(
                    -100000000.0f,
                    0.0f,
                    contact.getFixtureA().getBody().getPosition().x,
                    contact.getFixtureA().getBody().getPosition().y,
                    false
            );*/
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