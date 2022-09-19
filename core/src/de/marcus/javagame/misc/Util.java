package de.marcus.javagame.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import org.jetbrains.annotations.Nullable;

public class Util {
    public static float getAspectRatio(Stage stage) {
        Camera camera = stage.getCamera();
        return camera.viewportWidth / camera.viewportHeight;
    }

    public static float getReversedAspectRatio(Stage stage) {
        Camera camera = stage.getCamera();
        return camera.viewportHeight / camera.viewportWidth;
    }

    public static Vector2 getScreenCenter(Stage stage) {
        Camera camera = stage.getCamera();
        return new Vector2(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f);
    }

    /**
     * Generates a tiled drawable. A tiled drawable will repeat a TextureRegion to fill the space it is passed into
     *
     * @param region The region that the drawable should repeat
     * @return The TiledDrawable
     */
    public static TiledDrawable generateTiledDrawable(TextureRegion region) {
        return new TiledDrawable(region);
    }

    /**
     * Generates a tiled drawable. A tiled drawable will repeat a texture to fill the space it is passed into
     *
     * @param texture The texture that the drawable should repeat
     * @return The TiledDrawable
     */
    public static TiledDrawable generateTiledDrawable(Texture texture) {
        return new TiledDrawable(new TextureRegion(texture));
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
            parameters.size = (int) (15 * getScreenWidth(stage) / 1920);
            parameters.borderColor = Color.BLACK;
            parameters.borderWidth = 1;
        }
        parameters.size = (int) (parameters.size * getScreenWidth(stage) / 1920);
        return generator.generateFont(parameters);
    }

    public static BitmapFont getFontForScreenSize(Stage stage, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = (int) (size * getScreenWidth(stage) / 1920);
        parameters.borderColor = Color.BLACK;
        parameters.borderWidth = 1;

        return generator.generateFont(parameters);
    }

    public static BitmapFont getFontForScreenSize(Stage stage, int size, String fontName) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontName + ".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = (int) (size * getScreenWidth(stage) / 1920);
        parameters.borderColor = Color.BLACK;
        parameters.borderWidth = 1;

        return generator.generateFont(parameters);
    }

    public static BitmapFont getFontForScreenSize(Stage stage, int size, int padLeft) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = (int) (size * getScreenWidth(stage) / 1920);
        parameters.padLeft = (int) (padLeft * getScreenWidth(stage) / 1920);
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
