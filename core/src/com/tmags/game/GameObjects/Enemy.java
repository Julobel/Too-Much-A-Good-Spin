/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.tmags.game.Utils.EnemyDef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Enemy extends Sprite{
    private Body body;
    private Direction currentDirection;
    public enum Direction {RIGHT, LEFT};
    private static HashMap<String, Object> EnemyDefs = new HashMap<String, Object>();
    private EnemyDef enemyDef;
    private Float rotation;

    public Enemy(World world,EnemyDef randomEnemyDef) {
        super(new Texture(randomEnemyDef.texturePath));
        enemyDef = randomEnemyDef;

        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        FixtureDef fdef = new FixtureDef();

        fdef.filter.groupIndex = -1;

        if (randomEnemyDef.texturePath == "tram.png"){
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(randomEnemyDef.textureWidth / 2, randomEnemyDef.textureHeight / 2);
            fdef.shape = shape;
            fdef.friction = 0;
            fdef.density = 2000;
            setOrigin(getWidth() / 2, getHeight()  / 2);
        }else{
            CircleShape shape = new CircleShape();
            shape.setRadius(enemyDef.hitBoxRadius);
            fdef.shape = shape;
            fdef.density = 0.1f;

        }

        bdef.type = BodyDef.BodyType.DynamicBody;
        int xPosition = (int)(Math.random()*((200-800)+1))+800;
        bdef.position.set(xPosition,800);
        body = world.createBody(bdef);
        HashMap<String, Object> userData = new HashMap<String,Object>();
        userData.put("objectType","Enemy");
        userData.put("velocityX", Math.random() > 0.5 ? -1000000f : 1000000f);
        body.setUserData(userData);
        body.createFixture(fdef);
        if (isTram()){
            body.setGravityScale(100);
        }else{
            body.setGravityScale(2);
        }


        setBounds(0,0,randomEnemyDef.textureWidth, randomEnemyDef.textureHeight);
    }

    public static EnemyDef getRandomEnemy(){
        if (EnemyDefs.isEmpty()){
            EnemyDefs.put("biker", new EnemyDef("livreur.png", 105, 100, 50));
            EnemyDefs.put("car", new EnemyDef("car.png", 252, 80, 30));
            EnemyDefs.put("tram", new EnemyDef("tram.png", 500, 130, 100));
        }

        Random generator = new Random();
        Object[] values = EnemyDefs.values().toArray();
        Integer randomInt = generator.nextInt(values.length);
        return (EnemyDef) values[randomInt];
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

    public Boolean isTram(){
        return enemyDef.texturePath == "tram.png";
    }

    public void draw(SpriteBatch sb){
        boolean flip = (currentDirection == Direction.LEFT);
        Float x = body.getPosition().x - getWidth() / 2;
        Float y = body.getPosition().y - getHeight() / 2;
        sb.draw(getTexture(), flip ? x + enemyDef.textureWidth : x, y, flip ? -enemyDef.textureWidth: enemyDef.textureWidth, enemyDef.textureHeight);
    }
}
