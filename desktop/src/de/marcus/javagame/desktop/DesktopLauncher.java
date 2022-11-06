package de.marcus.javagame.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.marcus.javagame.ui.screens.LoadingScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = true;
		config.forceExit = true;
		config.title = "Rising Mage";
		config.addIcon("items/starter_sword.png", Files.FileType.Internal);

		new LwjglApplication(new LoadingScreen(), config);
	}


}
