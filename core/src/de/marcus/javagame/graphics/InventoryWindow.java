package de.marcus.javagame.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class InventoryWindow extends Window {

    Image picker;

    //TODO: Make inventory picker moveable and not static and replace absolute with relative values
    public InventoryWindow() {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));



        this.setPosition(
                Gdx.graphics.getWidth() / 4.0f,
                Gdx.graphics.getHeight() / 4.0f);


        float width = Gdx.graphics.getWidth() * 0.5f;
        float height = Gdx.graphics.getHeight() * 0.45f;
        this.setSize(width, height);
        picker = new Image(new Texture("inventory_picker.png"));
        picker.setName("mover");
        getTitleTable().add(picker)
                .width((Gdx.graphics.getWidth() * 0.63f) / 10)
                .height((Gdx.graphics.getWidth() * 0.55f) / 10).padRight((width * 0.02f)+(width*0.093f)*1).padTop(height * 0.3f);

        getTitleTable().getCell(picker);


        this.setModal(false);
        this.setVisible(false);
        this.setMovable(true);
        this.setDebug(false);

        this.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.RIGHT) {
                    Cell<Image> cell = getTitleTable().getCell(picker);
                    cell.padRight(cell.getPadRight()+(width*0.093f));
                }
                return false;
            }
        });




    }
}
