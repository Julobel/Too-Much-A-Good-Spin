package com.tmags.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tmags.game.Managers.GameInputProcessor;
import com.tmags.game.Managers.GameKeys;
import com.tmags.game.Managers.GameStateManager;

/**
 * Created by Jules Aubel on 17/06/19.
 */
public class Main implements ApplicationListener {

    public static int WIDTH;
    public static int HEIGHT;
    public static OrthographicCamera cam;
    private GameStateManager gsm;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(WIDTH, HEIGHT);
        cam.translate(WIDTH/2, HEIGHT/2);
        cam.update();

        Gdx.input.setInputProcessor(new GameInputProcessor());
        gsm = new GameStateManager();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GameKeys.update();
        this.gsm.update(Gdx.graphics.getDeltaTime());
        this.gsm.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
