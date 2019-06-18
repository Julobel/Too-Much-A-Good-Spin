package com.tmags.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class DrunkenPOS extends Sprite {

    private Body body;

    public DrunkenPOS(World world){
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;


        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        fdef.restitution = 0.8f;
        fdef.friction = 0.05f;
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100,90);

        body = world.createBody(bdef);
        shape.setRadius(10);
        fdef.shape = shape;
        body.setUserData("Player");
        body.createFixture(fdef);

    }



    private void initDrunkenPOS(){

    }

}
