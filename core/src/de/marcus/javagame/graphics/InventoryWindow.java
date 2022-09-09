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
    float width;
    float height;


    //TODO: Make inventory picker moveable and not static and replace absolute with relative values
    //TODO: KNOWN BUG: RESIZING MAKES IT IMPOSSIBLE TO CLOSE WINDOW!
    public InventoryWindow() {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));



        this.setPosition(
                Gdx.graphics.getWidth() / 4.0f,
                Gdx.graphics.getHeight() / 4.0f);


         width = Gdx.graphics.getWidth() * 0.5f;
         height = Gdx.graphics.getHeight() * 0.45f;
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

//        this.addListener(new InputListener() {
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == Input.Keys.LEFT) {
//                    Cell<Image> cell = getTitleTable().getCell(picker);
//                    float padding = cell.getPadRight() + (width * 0.093f);
//
//                    if(padding < (width * 0.093f * 10)) {
//                        cell.padRight(padding);
//                        getTitleTable().invalidate();
//                        getTitleTable().pack();
//                    }
//                } else if(keycode == Input.Keys.RIGHT) {
//                    Cell<Image> cell = getTitleTable().getCell(picker);
//                    float padding = cell.getPadRight() - (width * 0.093f);
//
//
//                    if(padding > 0) {
//                        cell.padRight(padding);
//                        getTitleTable().invalidate();
//                        getTitleTable().pack();
//                    }
//
//                } else if(keycode == Input.Keys.DOWN) {
//                    Cell<Image> cell = getTitleTable().getCell(picker);
//                    float padding = cell.getPadTop() + (height * 0.38f);
//
//                    if(padding < (height * 0.38f *3)) {
//                        cell.padTop(padding);
//                        getTitleTable().invalidate();
//                        getTitleTable().pack();
//                    }
//                } else if(keycode == Input.Keys.UP) {
//                    Cell<Image> cell = getTitleTable().getCell(picker);
//
//                    float padding = cell.getPadTop() - (height * 0.38f);
//                    if(padding > 0) {
//                        cell.padTop(padding);
//                        getTitleTable().invalidate();
//                        getTitleTable().pack();
//                    }
//
//                }
//                return false;
//            }
//        });




    }

    /**
     *
     * @param x 0 = dont move, 1 = right, -1 = left
     * @param y 0 = dont move, 1 = up, -1 = down
     */
    public void moveSelector(int x,int y) {
        Cell<Image> cell = getTitleTable().getCell(picker);
        float topPedding = cell.getPadTop();
        float sidePadding = cell.getPadRight();

        if (x != 0) {
            sidePadding += (width * 0.093f * x);
            System.out.println(sidePadding);
        }
        if(y != 0) {
            topPedding += (height * 0.38f * y);
            System.out.println(topPedding);
        }


        if(topPedding > 0 && topPedding < (height * 0.38f *3)) {
            cell.padTop(topPedding);
        }

        if(sidePadding < (width * 0.093f * 10) && sidePadding > 0) {
            cell.padRight(sidePadding);
        }

        getTitleTable().invalidate();
        getTitleTable().pack();
    }
}
