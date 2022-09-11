package de.marcus.javagame.misc;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Util {
    public static float getAspectRatio(Stage stage) {
        Camera camera = stage.getCamera();
        return camera.viewportWidth / camera.viewportHeight;
    }

    public static Vector2 getScreenCenter(Stage stage) {
        Camera camera = stage.getCamera();
        return new Vector2(camera.viewportWidth / 2.0f,camera.viewportHeight / 2.0f);
    }

    public static float getScreenWidth(Stage stage) {
        return stage.getCamera().viewportWidth;
    }

    public static float getScreenHeight(Stage stage) {
        return stage.getCamera().viewportHeight;
    }
}
