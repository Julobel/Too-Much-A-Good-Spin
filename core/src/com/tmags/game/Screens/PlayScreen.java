package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.GameObjects.*;
import com.tmags.game.Scenes.Hud;
import com.tmags.game.TooMuchAGoodSpin;
import com.tmags.game.Utils.BonusDef;
import com.tmags.game.Utils.EnemyDef;
import com.tmags.game.Utils.WorldContactListener;

import java.util.ArrayList;
import java.util.Random;

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
    private float timeCountB;
    private Enemy enemy;
    private ArrayList<Enemy> enemyStack;
    private ArrayList<Bonus> bonusStack;
    private Bonus bonus;

    private TextureAtlas textureAtlas;
    private Sound sound;
    private Long soundId;

    public PlayScreen(TooMuchAGoodSpin game){
        this.enemyStack = new ArrayList<Enemy>();
        this.bonusStack = new ArrayList<Bonus>();
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
        if (!enemyStack.isEmpty()) for (Enemy enemy : enemyStack) {
            enemy.update();
        }
        if (!bonusStack.isEmpty()) for (Bonus bonus : bonusStack) {
            bonus.update();
        }

        timeCount += dt;
        timeCountB += dt;

        if(timeCount >= 3){
            EnemyDef randomEnemy = Enemy.getRandomEnemy();
            enemy = new Enemy(world,randomEnemy);
            enemyStack.add(enemy);
            timeCount = 0f;
            if (randomEnemy.label.equals(Enemy.BIKER)) {
                getRandomBikeSoundEffect(randomEnemy);
            } else if (randomEnemy.label.equals(Enemy.CAR)) {
                getRandomCarSoundEffect(randomEnemy);
            }
            sound = Gdx.audio.newSound(Gdx.files.internal(randomEnemy.soundEffectPath));
            soundId = sound.play(1.0f);
        }
        if (timeCountB >= 5) {
                BonusDef randomBonus = Bonus.getRandomBonus();
                bonus = new Bonus(world, randomBonus);
                bonusStack.add(bonus);
                timeCountB = 0f;
            }


        if(currentPlayer.life <= 0) {
            sound.stop(soundId);
            game.setScreen(new GameOverScreen(game, 0, currentPlayer));
        }

    }

    private void getRandomBikeSoundEffect(EnemyDef randomEnemy) {
        int index = new Random().nextInt(3 - 1 + 1) + 1;
        String bikeSoundEffectPath = "sounds/effects/bike-" + index + ".mp3";
        randomEnemy.setSoundEffectPath(bikeSoundEffectPath);
    }


    private void getRandomCarSoundEffect(EnemyDef randomEnemy) {
        int index = new Random().nextInt(4 - 1 + 1) + 1;
        String bikeSoundEffectPath = "sounds/effects/car-" + index + ".mp3";
        randomEnemy.setSoundEffectPath(bikeSoundEffectPath);
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
        if (!bonusStack.isEmpty()){
            for (Bonus bonus : bonusStack){
                bonus.draw(sb);
            }
        }

        sb.end();
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
