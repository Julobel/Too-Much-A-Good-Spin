package com.tmags.game.Utils;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tmags.game.GameObjects.DrunkenPOS;
import com.tmags.game.GameObjects.Player;
import com.tmags.game.Screens.PlayScreen;
import com.tmags.game.TooMuchAGoodSpin;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureB().getBody().getUserData().equals("BottomLimit") ){
            contact.getFixtureA().getBody().setLinearVelocity(0,0);
        }
        if("DeathLimit".equals(contact.getFixtureA().getBody().getUserData())) {
            System.out.println("l alcool tue");
        }
    }

    @Override
    public void endContact(Contact contact) {
        if (contact.getFixtureB().getBody().getUserData().equals("Player")){



        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

};