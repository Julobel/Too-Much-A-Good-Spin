package com.tmags.game.Utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        if ("BottomLimit".equals(contact.getFixtureB().getBody().getUserData())){
            contact.getFixtureA().getBody().setLinearVelocity(0,0);
        }

        if("DeathLimit".equals(contact.getFixtureA().getBody().getUserData())) {
            System.out.println("l alcool tue");

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

};