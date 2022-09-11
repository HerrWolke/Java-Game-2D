package de.marcus.javagame.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Dialog extends Window {


    public Dialog() {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("dialog.png"))));
    }
}
