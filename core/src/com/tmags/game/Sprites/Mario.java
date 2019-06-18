package com.tmags.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Mario extends Sprite {
    public World world;
    public Body b2body;

    public Mario(World world) {
        this.world = world;
        defineMario();
    }

    private void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(120,120);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(25);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
