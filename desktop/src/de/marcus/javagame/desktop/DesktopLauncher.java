package de.marcus.javagame.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.marcus.javagame.graphics.screens.GameScreen;
import de.marcus.javagame.graphics.screens.LoadingScreen;
import de.marcus.javagame.testing.JavaGame;
import de.marcus.javagame.testing.LibgGDXUITests;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = false;
		config.title = "Rising Mage";
		config.addIcon("items/starter_sword.png", Files.FileType.Internal);
		config.forceExit = true;
		new LwjglApplication(new LoadingScreen(), config);
	}


}
