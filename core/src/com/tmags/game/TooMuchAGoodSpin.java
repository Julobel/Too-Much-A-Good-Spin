package com.tmags.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tmags.game.Managers.GameKeys;
import com.tmags.game.Screens.PlayScreen;

public class TooMuchAGoodSpin extends Game {
	public static int WIDTH;
	public static int HEIGHT;
	public static OrthographicCamera cam;
	public SpriteBatch batch;

	@Override
	public void create () {

	    System.out.println("OUILLE V2");

	    WIDTH = Gdx.graphics.getWidth();
	    HEIGHT = Gdx.graphics.getHeight();

	    cam = new OrthographicCamera(WIDTH, HEIGHT);
	    cam.translate(WIDTH/2, HEIGHT/2);
	    cam.update();

		batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GameKeys.update();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
