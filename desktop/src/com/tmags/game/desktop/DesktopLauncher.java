package com.tmags.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tmags.game.Main;
import com.tmags.game.TooMuchAGoodSpin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Too much a good spin";
		config.width = 1280;
		config.height = 720;
		config.useGL30 = true;
		config.resizable = false;

		new LwjglApplication(new Main(), config);
	}
}
