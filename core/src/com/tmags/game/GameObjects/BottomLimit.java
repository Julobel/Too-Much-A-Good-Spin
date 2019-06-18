package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BottomLimit {
    private Body body;

    public BottomLimit(World world, OrthographicCamera gamecam){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        Integer floorHeight = 10;
        bdef.position.set(gamecam.position.x,gamecam.viewportHeight );
        body = world.createBody(bdef);
        shape.setAsBox(10, 10);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef);
        body.setUserData("BottomLimit");
        body.setGravityScale(0);
    }


}
