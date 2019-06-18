package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public class Death {
    private Body body;

    public Death(World world, OrthographicCamera gamecam){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(gamecam.position.x,gamecam.viewportHeight);
        body = world.createBody(bdef);
        shape.setAsBox(800, 10);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef);
        body.setUserData("DeathLimit");
        body.setGravityScale(0);
    }
}
