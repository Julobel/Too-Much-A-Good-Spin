package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.tmags.game.Screens.PlayScreen;

import java.util.HashMap;

public class DrunkenPOS extends Sprite {

    private Body body;
    private TextureRegion playerFall;
    private final TextureRegion playerStand;
    public Direction currentDirection = Direction.RIGHT;
    private TextureRegion currentRegion;

    private enum State {FALLING, JUMPING, STANDING}
    private State currentState = State.STANDING;
    public State previousState = State.STANDING;
    public enum Direction {RIGHT, LEFT};

    public DrunkenPOS(World world, PlayScreen playscreen){
        super(playscreen.getAtlas().findRegion("walk-1").getTexture());
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        fdef.restitution = 0.8f;
        fdef.friction = 0.5f;

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(640,190);


        body = world.createBody(bdef);
        shape.setRadius(10);
        fdef.shape = shape;
        HashMap<String, Object> userData = new HashMap<String,Object>();
        userData.put("objectType","Player");
        body.setUserData(userData);
        body.createFixture(fdef);

        playerFall = new TextureRegion(getTexture(), 0,0, 50, 119);
        playerStand = new TextureRegion(getTexture(), 0,119, 50, 119);
        currentRegion = playerStand;
        setBounds(0,0, 50, 119);
        setRegion(playerFall);
        body.setGravityScale(0.4f);
    }


    public State getState(){
        if (body.getLinearVelocity().x > 0) {
            currentDirection = Direction.RIGHT;
        }

        if (body.getLinearVelocity().x < 0) {
            currentDirection = Direction.LEFT;
        }

        if (body.getLinearVelocity().y > 70){
            return State.JUMPING;
        }

        if (body.getLinearVelocity().y < -80  || Math.abs(body.getLinearVelocity().x)  > 50){
            return State.FALLING;
        }
        return State.STANDING;
    }


    public void update(){
        previousState = currentState;
        currentState = getState();

        switch (currentState){
            case JUMPING:
                currentRegion = playerFall;
            break;
            case FALLING:
                currentRegion = playerFall;
            break;
            case STANDING:
                currentRegion = playerStand;
            break;
        }

    }

    public void draw(SpriteBatch sb){
        boolean flip = (currentDirection == Direction.LEFT);
        Float x = body.getPosition().x - getWidth() / 2;
        Float y = body.getPosition().y - 10;
        sb.draw(currentRegion, flip ? x + 50 : x, y, flip ? -50 : 50, 119);
    }


}
