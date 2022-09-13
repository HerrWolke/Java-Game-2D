package de.marcus.javagame.graphics.ui.windows;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class ShopWindow extends Window {
    public ShopWindow() {
        super("", new com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));

    }
}
