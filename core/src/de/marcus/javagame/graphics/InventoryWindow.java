package de.marcus.javagame.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InventoryWindow extends Window {

    Image picker;
    float width;
    float height;

    Group group;

    int selectedItemOption;


    //TODO: Make inventory picker moveable and not static and replace absolute with relative values

    public InventoryWindow() {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));
        width = Gdx.graphics.getWidth() * 0.5f;
        height = Gdx.graphics.getHeight() * 0.45f;
        selectedItemOption = 2;

        TextureRegionDrawable itemOption = new TextureRegionDrawable(new Texture("item_option.png"));
        itemOption.setMinHeight(height / 8f);
        itemOption.setMinWidth(width / 9f);
        TextureRegionDrawable itemOptionSelected = new TextureRegionDrawable(new Texture("item_option_selected.png"));
        itemOptionSelected.setMinHeight(height / 8f);
        itemOptionSelected.setMinWidth(width / 9f);
        ImageTextButton button = new ImageTextButton("Use", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));
        ImageTextButton button2 = new ImageTextButton("Delete", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));
        ImageTextButton button3 = new ImageTextButton("Quickbar", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));

        button.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() * 3 - height * 0.26f);
        button2.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() * 2 - height * 0.26f);
        button3.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() - height * 0.26f);


        button3.setChecked(true);

        group = new Group();
        group.addActor(button);
        group.addActor(button2);
        group.addActor(button3);
        group.setVisible(false);


        group.setDebug(true);


        this.setPosition(
                Gdx.graphics.getWidth() / 4.0f,
                Gdx.graphics.getHeight() / 4.0f);


        this.setSize(width, height);
        picker = new Image(new Texture("inventory_picker.png"));
        picker.setName("mover");
        getTitleTable().add(picker)
                .width((Gdx.graphics.getWidth() * 0.63f) / 10)
                .height((Gdx.graphics.getWidth() * 0.55f) / 10).padRight((width * 0.02f)).padTop(height * 0.3f);


        getTitleTable().getCell(picker);
        getTitleTable().addActor(group);


        this.setModal(false);
        this.setVisible(false);
        this.setMovable(true);
        this.setDebug(true);

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
     * @param x 0 = dont move, 1 = right, -1 = left
     * @param y 0 = dont move, 1 = up, -1 = down
     */
    public void moveSelector(int x, int y) {

        if (!group.isVisible()) {
            Cell<Image> cell = getTitleTable().getCell(picker);
            float topPedding = cell.getPadTop();
            float sidePadding = cell.getPadRight();
            boolean moveX = false;
            boolean moveY = false;


            if (x != 0) {
                sidePadding += (width * 0.093f * x);
                moveX = true;
            }
            if (y != 0) {
                topPedding += (height * 0.38f * y);
                moveY = true;
            }


            if (moveY && topPedding > 0 && topPedding < (height * 0.38f * 3)) {
                cell.padTop(topPedding);
                group.moveBy(0, -(Gdx.graphics.getWidth() * 0.48f) / 10 * y);
                System.out.println("top " + -(height * 0.38f * y));
            }

            if (moveX && sidePadding < (width * 0.093f * 10) && sidePadding > 0) {
                cell.padRight(sidePadding);
                group.moveBy(-(width * 0.093f * x), 0);
                System.out.println("side " + (width * 0.093f * -x));
            }

            getTitleTable().invalidate();
            getTitleTable().pack();
        } else {
            int size = group.getChildren().size;



            if (size != selectedItemOption - y && selectedItemOption - y > -1) {

                ImageTextButton currentSelected = (ImageTextButton) group.getChild(selectedItemOption);
                currentSelected.setChecked(false);

                selectedItemOption-=y;
                ImageTextButton child = (ImageTextButton) group.getChild(selectedItemOption);
                child.setChecked(true);
            }

        }
    }

    public void toggleItemsOptionMenu() {
        group.setVisible(!group.isVisible());
    }
}
