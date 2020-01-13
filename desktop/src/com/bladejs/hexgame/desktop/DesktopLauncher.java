package com.bladejs.hexgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bladejs.hexgame.HexGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "HEX";
		config.width = 600;
		config.height = 600;
		new LwjglApplication(new HexGame(), config);
	}
}
