package com.tmags.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.GameObjects.Player;
import com.tmags.game.TooMuchAGoodSpin;
import com.badlogic.gdx.graphics.Pixmap.Format;

import static com.tmags.game.GameObjects.Player.isAlive;
import static com.tmags.game.GameObjects.Player.life;

public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Label gamerLabel;
    private Label countUpLabel;
    private Label timeLabel;
    private Label scoreLabel;
    private Label levelLabel;
    private Label tmpLabel;
    public ProgressBar healthBar;
    private Player currentPlayer;

    public Hud (SpriteBatch sb, Player currenPlayer) {

        worldTimer = 0;
        timeCount = 0f;
        score = 0;
        viewport = new FitViewport(TooMuchAGoodSpin.WIDTH, TooMuchAGoodSpin.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        currentPlayer = currenPlayer;

        /**
         * Gestion bar de vie
         */
        Pixmap pixmap = new Pixmap(100, 15, Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = drawable;

        pixmap = new Pixmap(0, 15, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knob = drawable;

        pixmap = new Pixmap(40, 15, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable;

        stage = new Stage();

        healthBar = new ProgressBar(0f, 100f, 0.1f, false, progressBarStyle);
        healthBar.setValue(currenPlayer.life);
       /* healthBar.setAnimateDuration(0.5f);*/
        healthBar.setBounds(370, 600, 550, 15);

        stage.addActor(healthBar);

        stage.draw();
        stage.act();

        // Création de la table, permet de positionner des éléments enfants
        Table table = new Table();
        table.top();
        // La taille du table sera prendra la taille du stage
        table.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/nervous/Nervous.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 35;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        final Label.LabelStyle style = new Label.LabelStyle();
        style.font = generator.generateFont(parameter);
        countUpLabel = new Label(String.format("%03d", worldTimer), style);
        scoreLabel = new Label(String.format("%06d", score), style);
        timeLabel = new Label("TIME", style);
        levelLabel = new Label("1-1", style);
        // Todo récupérer le nom du joueur enregistrer au menu ?
        gamerLabel = new Label("Joueur 1", style);
        tmpLabel = new Label("", style);

        /**
         * Positionnement des éléments
         */
        table.add(gamerLabel).padLeft(10).padTop(10).uniform();
        table.add(levelLabel).expandX().padTop(10).uniform();
        table.add(timeLabel).padRight(10).padTop(10).uniform();
        table.row();
        table.add(scoreLabel).padLeft(10).uniform();
        table.add(tmpLabel).expandX().uniform();
        table.add(countUpLabel).padRight(10).uniform();

        stage.addActor(table);
    }

    public void update (float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (!isAlive) {
                currentPlayer.life = life;
                score +=0;
            } else {
                currentPlayer.life -= 3f;
                score += 10 * (Math.round(worldTimer));
            }
            healthBar.setValue(currentPlayer.life);
            stage.draw();
            stage.act();
            worldTimer++;
            countUpLabel.setText(String.format("%03d", worldTimer));
            scoreLabel.setText(String.format("%06d", score));
            timeCount = 0f;
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
