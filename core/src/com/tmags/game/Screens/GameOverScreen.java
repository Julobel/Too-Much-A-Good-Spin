/**
 * Created by Jules Aubel on 19/06/19.
 */

package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.tmags.game.TooMuchAGoodSpin;

public class GameOverScreen implements Screen {

    private static final int BANNER_WIDTH = 350;
    private static final int BANNER_HEIGHT = 100;

    TooMuchAGoodSpin game;
    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    public GameOverScreen (TooMuchAGoodSpin game, int score) {
        this.game = game;
        this.score = score;

        // Recupération du highscore de puis le fichier de sauvegarde
        Preferences prefs = Gdx.app.getPreferences("alcoholicgame");
        this.highscore = prefs.getInteger("highscore", 0);

        // Sauvegarde du nouveau highscore
        if (score > highscore) {
            // Ouverture et écriture du nouveau score dans le fichier
            prefs.putInteger("highscore", score);
            // Sauvegarde
            prefs.flush();
        }

        gameOverBanner = new Texture("game_over.png");
        // TODO ERROR NOT FOUND FILE ???
        // scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // On clear l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(
            gameOverBanner,
            Gdx.graphics.getWidth() / 2 - BANNER_WIDTH / 2,
            Gdx.graphics.getHeight() - BANNER_HEIGHT - 100,
            BANNER_WIDTH,
            BANNER_HEIGHT
        );

        // TODO
        // GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score : \n" + score, Color.WHITE, 0, Align.left, false);
        // GlyphLayout highScoreLayout = new GlyphLayout(scoreFont, "Highscore : \n" + highscore, Color.WHITE, 0, Align.left, false);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
