package com.tmags.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;

public class Ground extends Sprite {

    private Body body;
    private Integer MAX_ROTATION = 3;
    private Float startDelta = null;
    private String currentState;
    private Vector2 originalPos;
    private float currentYPos;


    public Ground(World world, OrthographicCamera gamecam){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        Integer floorHeight = 30;
        bdef.position.set(gamecam.position.x,gamecam.viewportHeight + floorHeight);
        body = world.createBody(bdef);
        originalPos = new Vector2(150 ,10 );
        shape.setAsBox(originalPos.x, originalPos.y);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("BottomLimit");
    }


    public void moveGround(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            body.setLinearVelocity(new Vector2(0, 200f));
            this.currentState = "BUMPING";
        }
        this.currentYPos = body.getPosition().y;
        if ( "BUMPING".equals(this.currentState) && this.currentYPos >= originalPos.y + 35){
            body.setLinearVelocity(new Vector2(0, -200f));
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