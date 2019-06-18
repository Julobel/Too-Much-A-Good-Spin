package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.GameObjects.BottomLimit;
import com.tmags.game.GameObjects.Death;
import com.tmags.game.GameObjects.DrunkenPOS;
import com.tmags.game.GameObjects.Ground;
import com.tmags.game.Scenes.Hud;
import com.tmags.game.TooMuchAGoodSpin;
import com.tmags.game.Utils.WorldContactListener;

public class PlayScreen implements Screen {
    private TooMuchAGoodSpin game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Ground ground;
    private DrunkenPOS drunkenPOS;
    private BottomLimit bottomLimit;
    private Death deathLimit;

    public PlayScreen(TooMuchAGoodSpin game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(TooMuchAGoodSpin.WIDTH, TooMuchAGoodSpin.HEIGHT, gamecam);
        hud = new Hud(game.batch);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,- 50), true);
        world.setContactListener(new WorldContactListener());
        drunkenPOS = new DrunkenPOS(world);
        ground = new Ground(world, gamecam);
        bottomLimit = new BottomLimit(world, gamecam);
        deathLimit = new Death(world,gamecam);
        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    public void update (float dt) {
        hud.update(dt);
        world.step(1/60f, 6,2);
        ground.moveGround();
        gamecam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1/60f, 6,2);
        gamecam.update();
        b2dr.render(world, gamecam.combined);
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
