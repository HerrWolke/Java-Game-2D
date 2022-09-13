package de.marcus.javagame.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.jetbrains.annotations.Nullable;

public class Util {
    public static float getAspectRatio(Stage stage) {
        Camera camera = stage.getCamera();
        return camera.viewportWidth / camera.viewportHeight;
    }

    public static Vector2 getScreenCenter(Stage stage) {
        Camera camera = stage.getCamera();
        return new Vector2(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f);
    }

    /**
     * Generates a font for the screen size passed
     *
     * @param stage      The stage to get the screen size from
     * @param parameters This can be null. Will use default variables
     * @return The font
     */
    public static BitmapFont getFontForScreenSize(Stage stage, @Nullable FreeTypeFontGenerator.FreeTypeFontParameter parameters) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        if (parameters == null) {
            parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameters.size = (int) (15 * 1920 / getScreenWidth(stage));
            parameters.borderColor = Color.BLACK;
            parameters.borderWidth = 1;
        }
        parameters.size = (int) (parameters.size * 1920 / getScreenWidth(stage));
        return generator.generateFont(parameters);
    }

    public static BitmapFont getFontForScreenSize(Stage stage, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = (int) (size * 1920 / getScreenWidth(stage));
        parameters.borderColor = Color.BLACK;
        parameters.borderWidth = 1;

        return generator.generateFont(parameters);
    }

    public static float getScreenWidth(Stage stage) {
        return stage.getCamera().viewportWidth;
    }

    public static float getScreenHeight(Stage stage) {
        return stage.getCamera().viewportHeight;
    }
}
