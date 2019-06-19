/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy extends Sprite{
    private Body body;

    public Enemy(World world) {
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        // fdef.restitution = 0.8f;
        fdef.friction = 0.1f;
        bdef.type = BodyDef.BodyType.DynamicBody;

        int xPosition = (int)(Math.random()*((200-800)+1))+800;

        bdef.position.set(xPosition,800);
        body = world.createBody(bdef);
        shape.setRadius(10);
        fdef.shape = shape;
        body.setUserData("Enemy");
        body.createFixture(fdef);
        body.setGravityScale(100);
    }
}
