package de.marcus.javagame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.marcus.javagame.screens.LoadingScreen;
import de.marcus.javagame.testing.MoreUITests;
import de.marcus.javagame.testing.MyGdxGame;
import de.marcus.javagame.testing.Test;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = false;
		config.forceExit = true;
		new LwjglApplication(new LoadingScreen(), config);
	}


}
