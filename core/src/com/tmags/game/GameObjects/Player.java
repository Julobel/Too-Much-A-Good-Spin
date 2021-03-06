/**
 * Created by Jules Aubel on 18/06/19.
 */

package com.tmags.game.GameObjects;

public class Player {

    public static float life;
    public static boolean isAlive;
    public int score;

    public Player() {
        this.life = 100f;
        this.isAlive = true;
        this.score = 0;
    }

    public static void losesLife(float value) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
