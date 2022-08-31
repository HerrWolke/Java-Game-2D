package de.marcus.javagame.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InventoryWindow extends Window {

    //TODO: Replace testing code with real code etc.
    //TODO: Add close button blah blah
    public InventoryWindow() {
        super("title", new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("inventory.png"))));
        this.setSize(new Texture("inventory.png").getWidth(), new Texture("inventory.png").getHeight());
        this.setModal(true);
        this.setVisible(true);
        this.setMovable(true);
        this.setPosition(
                (Gdx.graphics.getWidth() / 2f) - (this.getWidth() / 2),
                (Gdx.graphics.getHeight() / 2f) - (this.getHeight() / 2)
        );
    }
}
