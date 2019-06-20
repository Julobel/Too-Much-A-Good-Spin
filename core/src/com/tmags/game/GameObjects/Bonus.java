/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.tmags.game.Utils.BonusDef;

import java.util.HashMap;
import java.util.Random;

public class Bonus extends Sprite{
    private static HashMap<String, Object> BonusDefs = new HashMap<String, Object>();
    private Body body;
    private Direction currentDirection;
    private BonusDef bonusDef;
    public Bonus(World world, BonusDef randomBonusDef) {

        super(new Texture(randomBonusDef.texturePath));

        bonusDef = randomBonusDef;

        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        fdef.filter.groupIndex = -1;

        // fdef.restitution = 0.8f;
        fdef.density = 0.1f;
        bdef.type = BodyDef.BodyType.DynamicBody;

        int xPosition = (int)(Math.random()*((200-800)+1))+800;

        bdef.position.set(xPosition,800);
        body = world.createBody(bdef);

        shape.setRadius(10);
        fdef.shape = shape;

        HashMap<String, Object> userData = new HashMap<String,Object>();
        userData.put("objectType","Bonus");
        userData.put("velocityX", Math.random() > 0.5 ? -1000000f : 1000000f);


        body.setUserData(userData);

        body.createFixture(fdef);
        body.setGravityScale(2);
        setBounds(0,0,randomBonusDef.textureWidth, randomBonusDef.textureHeight);
    }

    public static BonusDef getRandomBonus(){
        if (BonusDefs.isEmpty()){
            BonusDefs.put("beer", new BonusDef("beer_03.png", 55, 50));
            BonusDefs.put("clope", new BonusDef("clope.png", 60, 15));
        }

        Random generator = new Random();
        Object[] values = BonusDefs.values().toArray();
        Integer randomInt = generator.nextInt(values.length);
        return (BonusDef) values[randomInt];
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
        sb.draw(getTexture(), flip ? x + bonusDef.textureWidth : x, y, flip ? -bonusDef.textureWidth: bonusDef.textureWidth, bonusDef.textureHeight);
    }

    public enum Direction {RIGHT, LEFT}
}
