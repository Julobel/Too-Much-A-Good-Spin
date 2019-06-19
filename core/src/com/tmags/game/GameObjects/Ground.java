package com.tmags.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Ground extends Sprite {

    private Body body;
    private String currentState;
    private Vector2 originalPos;
    private float currentYPos;

    public Ground(World world, OrthographicCamera gamecam){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set(gamecam.position.x,gamecam.viewportHeight + 10);
        body = world.createBody(bdef);
        originalPos = new Vector2(500 ,15);
        shape.setAsBox(originalPos.x, originalPos.y);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("GroundLimit");
    }


    public void moveGround(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            body.setLinearVelocity(new Vector2(0, 1000f));
            this.currentState = "BUMPING";
        }
        this.currentYPos = body.getPosition().y;
        if ( "BUMPING".equals(this.currentState) && this.currentYPos >= originalPos.y + 35){
            body.setLinearVelocity(new Vector2(0, -1000f));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            Float floorCurrentRotation = body.getTransform().getRotation();
            Float floorNewRotation = 0f;
            Float floorActionRation = 0.01f;
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                floorNewRotation =  floorCurrentRotation - floorActionRation;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                floorNewRotation =  floorCurrentRotation + floorActionRation;
            }

            if (floorNewRotation >= -0.3f && floorNewRotation <= 0.3f){
                body.setTransform(body.getPosition(),floorNewRotation);
            }
        }
    }
}
