package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class DrunkenPOS extends Sprite {

    private Body body;
    public enum State {ALIVE,DEAD};
    public State currentPlayerState;
    public State previousPlayerState;
    private boolean playerDead;


    public DrunkenPOS(World world){
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        fdef.restitution = 0.8f;
        fdef.friction = 0.05f;
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(640,190);

        body = world.createBody(bdef);
        shape.setRadius(10);
        fdef.shape = shape;
        body.setUserData("Player");
        body.createFixture(fdef);


    }

    public State getPlayerState() {
        if (playerDead)
            return State.DEAD;
        else
            return State.ALIVE;
    }

    public void hit() {

    }
    private void initDrunkenPOS(){

    }
}
