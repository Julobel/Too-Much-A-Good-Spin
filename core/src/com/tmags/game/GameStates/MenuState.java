/**
 * Created by Jules Aubel on 17/06/19.
 */

package com.tmags.game.GameStates;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.tmags.game.GameObjects.Player;
import com.tmags.game.Main;
import com.tmags.game.Managers.GameStateManager;

public class MenuState extends GameState{

    private SpriteBatch sb;
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "Too much a good spin";
    private int currentItem;
    private String[] menuItems;
    private static GlyphLayout glyphLayout = new GlyphLayout();
    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        sb = new SpriteBatch();
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/nervous/Nervous.ttf")
        );

        FreeTypeFontGenerator.FreeTypeFontParameter parameterTitleFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterTitleFont.size = 56;
        parameterTitleFont.magFilter = Texture.TextureFilter.Linear;
        parameterTitleFont.minFilter = Texture.TextureFilter.Linear;
        parameterTitleFont.color = new com.badlogic.gdx.graphics.Color(com.badlogic.gdx.graphics.Color.WHITE);

        titleFont = gen.generateFont(parameterTitleFont);

        FreeTypeFontGenerator.FreeTypeFontParameter parameterFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterFont.size = 40;
        parameterFont.magFilter = Texture.TextureFilter.Linear;
        parameterFont.minFilter = Texture.TextureFilter.Linear;
        parameterFont.color = new com.badlogic.gdx.graphics.Color(com.badlogic.gdx.graphics.Color.WHITE);

        font = gen.generateFont(parameterFont);

        glyphLayout.setText(font, title);

        menuItems = new String[] {
                "Jouer",
                "Scores",
                "Quitter"
        };
    }

    @Override
    public void update(float dt) {
        this.handleInput();
    }

    /**
     * Affichage menu
     */
    @Override
    public void draw() {
        sb.setProjectionMatrix(Main.cam.combined);
        sb.begin();

        // Affichage titre
        titleFont.draw(
                sb,
                title,
                300,
                550
        );

        // Affichage menu
        for (int i = 0; i < menuItems.length; i++) {
            font.setColor(currentItem == i ? Color.RED : Color.WHITE);
            font.draw(
                    sb,
                    menuItems[i],
                    560,
                    370 - 80 * i
            );
        }

        sb.end();

    }

    /**
     * Gestion déplacement menu
     */
    @Override
    public void handleInput() {
        System.out.println(currentItem);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println("UP");
            if(currentItem > 0) {
                currentItem--;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            System.out.println("DOWN");
            if(currentItem < menuItems.length - 1) {
                currentItem++;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            System.out.println("ENTER");
            select();
        }
    }

    /**
     * Déclanchement action menu
     */
    private void select() {
        switch (currentItem) {
            case 0:
                gsm.setGameState(GameStateManager.PLAY);
                Player.isAlive = true;
                break;
            case 1:
                // MENU SCORE
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
    }


    @Override
    public void dispose() {

    }
}
