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
import com.tmags.game.TooMuchAGoodSpin;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class Hud implements Disposable{
    //private final Label.LabelStyle style;
    public Stage stage;
    private Viewport viewport;
    private Skin skin;
    private ProgressBar progressBar;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Label gamerLabel;
    private Label countUpLabel;
    private Label timeLabel;
    private static Label scoreLabel;
    private Label levelLabel;
    private Label tmpLabel;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private Texture textureBar;
    private ProgressBar.ProgressBarStyle barStyle;
    private Drawable drawable;

    public Hud (SpriteBatch sb) {
        worldTimer = 0;
        timeCount = 0f;
        score = 0;

        viewport = new FitViewport(TooMuchAGoodSpin.WIDTH, TooMuchAGoodSpin.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        skin = new Skin();


        // TODO Refactor ProgressBar
        /*skin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        Pixmap pixmapv2 = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmapv2.setColor(Color.RED);
        pixmapv2.fill();

        drawable = new Drawable(new Pixmap(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), new Format.Alpha), 1);

        // textureBar = new Texture(Gdx.files.internal("./assets/baglogic.jpg"));
        barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), pixmapv2);
        barStyle.knobBefore = barStyle.knob;
        progressBar = new ProgressBar(0f,10f,1f,false, barStyle);
        progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBar.setStyle(progressBarStyle);*/

        // Création de la table, permet de positionner des éléments enfants
        Table table = new Table();
        table.top();
        // La taille du table sera prendra la taille du stage
        table.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/blockheads/Blockheads.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        final Label.LabelStyle style = new Label.LabelStyle();
        style.font = generator.generateFont(parameter);
        //style = new Label.LabelStyle(generator.generateFont(parameter));
        countUpLabel = new Label(String.format("%03d", worldTimer), style);
        scoreLabel = new Label(String.format("%06d", score), style);
        timeLabel = new Label("TIME", style);
        levelLabel = new Label("1-1", style);
        // Todo récupérer le nom du joueur enregistrer au menu ?
        gamerLabel = new Label("Joueur 1", style);
        tmpLabel = new Label("", style);

        table.add(gamerLabel).padLeft(10).padTop(10).uniform();
        table.add(levelLabel).expandX().padTop(10).uniform();
        table.add(timeLabel).padRight(10).padTop(10).uniform();
        table.row();
        table.add(scoreLabel).padLeft(10).uniform();
        // table.add(progressBar).expandX().uniform();
        table.add(tmpLabel).expandX().uniform();
        table.add(countUpLabel).padRight(10).uniform();

        stage.addActor(table);
    }

    public void update (float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTimer++;
            countUpLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0f;
        }
    }

    public static void addScore (int value){
        // TODO MAJ SCORE
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
