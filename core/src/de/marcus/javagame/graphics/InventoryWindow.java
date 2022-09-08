package de.marcus.javagame.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class InventoryWindow extends Window {

    //TODO: Replace testing code with real code etc.
    //TODO: Add close button blah blah
    public InventoryWindow() {
        super("This is this windows title ", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));
        getTitleTable().add(new Image(new Texture("inventory_picker.png")))
                .width((Gdx.graphics.getWidth() * 0.6f) / 10)
                .height((Gdx.graphics.getWidth() * 0.55f) / 10).padRight(16).padTop(145);

        this.setModal(false);
        this.setVisible(true);
        this.setMovable(true);



    }
}
