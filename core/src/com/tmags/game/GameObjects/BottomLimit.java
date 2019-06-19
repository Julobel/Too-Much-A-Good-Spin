package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public class BottomLimit {
    private Body body;

    public BottomLimit(World world, OrthographicCamera gamecam){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(gamecam.position.x,-105);
        body = world.createBody(bdef);
        shape.setAsBox(30, 100);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef);
        body.setUserData("BottomLimit");
        body.setGravityScale(0);
    }
}
