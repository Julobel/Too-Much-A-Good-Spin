package com.tmags.game.Utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.HashMap;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {


        HashMap<String, Object> userData = (HashMap<String, Object>) contact.getFixtureB().getBody().getUserData();

        if (userData.get("objectType").equals("BottomLimit") ){
            contact.getFixtureA().getBody().setLinearVelocity(0,0);
        }

        if ( userData.get("objectType").equals("Enemy")) {
            contact.getFixtureB().getBody().applyLinearImpulse(
                    (Float) userData.get("velocityX"),
                    600000.0f,
                    contact.getFixtureA().getBody().getPosition().x,
                    contact.getFixtureA().getBody().getPosition().y,
                    false
            );
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