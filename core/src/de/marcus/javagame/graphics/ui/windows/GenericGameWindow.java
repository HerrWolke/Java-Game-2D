package de.marcus.javagame.graphics.ui.windows;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class GenericGameWindow extends Window {


    public GenericGameWindow(String title, Skin skin) {
        super(title, skin);
    }

    public GenericGameWindow(String title, WindowStyle style) {
        super(title, style);
    }

    public void closeWindow() {
        this.setVisible(false);
    }
}
