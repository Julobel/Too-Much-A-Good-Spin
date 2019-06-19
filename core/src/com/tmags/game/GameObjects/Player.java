/**
 * Created by Jules Aubel on 18/06/19.
 */

package com.tmags.game.GameObjects;

public class Player {

    public float life;
    public boolean isAlive;

    public Player() {
        this.life = 100f;
        this.isAlive = true;
    }

    public void losesLife(float value) {
        life -= value;
        if (life <= 0) {
            isAlive = false;
        }
    }

    public void gainLife(float value) {
        life += value;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
