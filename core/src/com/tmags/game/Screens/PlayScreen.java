package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.Scenes.Hud;
import com.tmags.game.TooMuchAGoodSpin;

public class PlayScreen implements Screen {
    private TooMuchAGoodSpin game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;

    public PlayScreen(TooMuchAGoodSpin game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(TooMuchAGoodSpin.V_WIDTH, TooMuchAGoodSpin.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    public void update (float dt) {

        hud.update(dt);
        gamecam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
