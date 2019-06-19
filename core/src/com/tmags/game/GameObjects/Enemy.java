/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class Enemy extends Sprite{
    private Body body;
    private Direction currentDirection;
    public enum Direction {RIGHT, LEFT};

    public Enemy(World world) {
        super(new Texture("livreur.png"));

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
        body.setGravityScale(10);
        setBounds(0,0,100, 105);
    }

    public void update(){
        setDirection();
    }

    public void setDirection(){
        if (body.getLinearVelocity().x > 0) {
            currentDirection = Direction.RIGHT;
        }

        if (body.getLinearVelocity().x < 0) {
            currentDirection = Direction.LEFT;
        }
    }

    public void draw(SpriteBatch sb){

        boolean flip = (currentDirection == Direction.LEFT);
        Float x = body.getPosition().x - getWidth() / 2;
        Float y = body.getPosition().y;
        sb.draw(getTexture(), flip ? x + 100 : x, y, flip ? -100 : 100, 105);

    }
}
