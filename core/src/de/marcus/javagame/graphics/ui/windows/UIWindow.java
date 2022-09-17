package de.marcus.javagame.graphics.ui.windows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import de.marcus.javagame.misc.Util;


public class UIWindow extends Window {
    private float lastScreenWidth;
    private float lastScreenHeight;

    private float positionWidth;

    private float positionHeight;

    private float elapsedDelta;

    private Stage stage;

    public UIWindow(Skin skin, Stage stage) {
        super("", skin);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedDelta += delta;

        if (elapsedDelta >= 500) {
            float screenHeight = Util.getScreenHeight(stage);
            float screenWidth = Util.getScreenWidth(stage);
            if (screenHeight != lastScreenHeight || screenWidth != screenWidth) {
                this.setPosition(screenHeight / 2.0f, screenWidth / 2.0f);

            }
        }
    }
}
