package com.tmags.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private float floorNewRotation;

    public Ground(World world, OrthographicCamera gamecam){
        super(new Texture("ground.png"));
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        Integer floorHeight = 120;
        bdef.position.set(gamecam.position.x, 70);
        body = world.createBody(bdef);
        originalPos = new Vector2(1000 ,70);
        shape.setAsBox(originalPos.x, originalPos.y);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("GroundLimit");
        setBounds(0,0, 1000, 70);
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
            floorNewRotation = 0f;
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

    public void draw(SpriteBatch sb){

        System.out.println(floorNewRotation);
        sb.draw(new TextureRegion(getTexture(), 0,0,1000,280),body.getPosition().x  - getWidth() , (body.getPosition().y - getHeight() * 7 ) + 80,1000,(body.getPosition().y + getHeight() / 2) + 350,2000,560,1f,1f,floorNewRotation * 55f);
    }

}
