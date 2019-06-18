/**
 * Created by Jules Aubel on 17/06/19.
 */

package com.tmags.game.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tmags.game.Main;
import com.tmags.game.Managers.GameKeys;
import com.tmags.game.Managers.GameStateManager;
import com.tmags.game.Screens.PlayScreen;
import com.tmags.game.TooMuchAGoodSpin;

public class PlayState extends GameState {

    private ShapeRenderer sr;
    private TooMuchAGoodSpin game;
    public static int WIDTH;
    public static int HEIGHT;
    public static OrthographicCamera cam;
    public SpriteBatch batch;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH/2, HEIGHT/2);
        cam.update();

        batch = new SpriteBatch();
        game = new TooMuchAGoodSpin();
        game.create();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw() {
        game.render();
    }

    @Override
    public void handleInput() {

    }
}
