package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.GameObjects.*;
import com.tmags.game.Scenes.Hud;
import com.tmags.game.TooMuchAGoodSpin;
import com.tmags.game.Utils.WorldContactListener;

import java.util.ArrayList;

public class PlayScreen implements Screen {
    private final SpriteBatch sb;
    private final Texture bg;
    public TooMuchAGoodSpin game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Ground ground;
    private DrunkenPOS drunkenPOS;
    private BottomLimit bottomLimit;
    private Player currentPlayer;
    private float timeCount;
    private Enemy enemy;
    private ArrayList<Enemy> enemyStack;

    private TextureAtlas textureAtlas;

    public PlayScreen(TooMuchAGoodSpin game){
        this.enemyStack = new ArrayList<Enemy>();
        timeCount = 0f;
        textureAtlas = new TextureAtlas("perso.txt");
        this.game = game;
        sb = new SpriteBatch();
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(TooMuchAGoodSpin.WIDTH, TooMuchAGoodSpin.HEIGHT, gamecam);
        currentPlayer = new Player();
        hud = new Hud(game.batch, currentPlayer);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,- 120), true);
        world = new World(new Vector2(0,- 100), true);
        world.setContactListener(new WorldContactListener());
        drunkenPOS = new DrunkenPOS(world, this);
        ground = new Ground(world, gamecam);
        bottomLimit = new BottomLimit(world, gamecam);
        b2dr = new Box2DDebugRenderer();

        bg = new Texture("bg.jpg");
    }

    public TextureAtlas getAtlas(){
        return this.textureAtlas;
    }

    @Override
    public void show() {

    }

    public void update (float dt) {
        hud.update(dt);
        world.step(1/60f, 6,2);
        ground.moveGround();
        drunkenPOS.update();
        gamecam.update();
        if (!enemyStack.isEmpty()){
            for (Enemy enemy : enemyStack){
                enemy.update();
            }
        }

        timeCount += dt;
        if(timeCount >= 3){
            enemy = new Enemy(world);
            enemyStack.add(enemy);
            timeCount = 0f;
        }
        if(currentPlayer.life <= 0) {
            game.setScreen(new GameOverScreen(game, 0, currentPlayer));
        }

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1/60f, 6,2);
        gamecam.update();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        sb.begin();
        sb.draw(bg, 0,0);
        ground.draw(sb);
        drunkenPOS.draw(sb);

        if (!enemyStack.isEmpty()){
            for (Enemy enemy : enemyStack){
                enemy.draw(sb);
            }
        }

        sb.end();
        hud.stage.draw();
        b2dr.render(world, gamecam.combined);

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
