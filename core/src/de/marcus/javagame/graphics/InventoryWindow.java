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
import de.marcus.javagame.datahandling.data.Inventory;
import de.marcus.javagame.graphics.ui.UI;

public class InventoryWindow extends Window {

    private static final String DELETE_BUTTON_TEXT = "Löschen";
    private static final String QUICKBAR_BUTTON_TEXT = "Schnellauswahl";
    private static final String USE_BUTTON_TEXT = "Benutzen";


    Image picker;
    float width;
    float height;


    Group group;

    Group placeholders;

    Group hotbar;
    int selectedItemOption;

    int selectedItem;

    Inventory inventory;


    //TODO: Make inventory picker moveable and not static and replace absolute with relative values

    public InventoryWindow(Inventory inventory) {

        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("inventory3.png"))));
        this.inventory = inventory;
        selectedItem = 0;
        width = Gdx.graphics.getWidth() * 0.5f;
        height = Gdx.graphics.getHeight() * 0.45f;
        selectedItemOption = 2;

        TextureRegionDrawable itemOption = new TextureRegionDrawable(new Texture("item_option.png"));
        itemOption.setMinHeight(height / 8f);
        itemOption.setMinWidth(width / 9f);
        TextureRegionDrawable itemOptionSelected = new TextureRegionDrawable(new Texture("item_option_selected.png"));
        itemOptionSelected.setMinHeight(height / 8f);
        itemOptionSelected.setMinWidth(width / 9f);

        ImageTextButton button = new ImageTextButton(USE_BUTTON_TEXT, new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));
        ImageTextButton button2 = new ImageTextButton(DELETE_BUTTON_TEXT, new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));
        ImageTextButton button3 = new ImageTextButton(QUICKBAR_BUTTON_TEXT, new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, new BitmapFont()));


        button.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() * 3 - height * 0.26f);
        button2.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() * 2 - height * 0.26f);
        button3.setPosition(width - itemOption.getMinWidth() / 4f, -itemOption.getMinHeight() - height * 0.26f);


        button3.setChecked(true);

        group = new Group();
        group.addActor(button);
        group.addActor(button2);
        group.addActor(button3);
        group.setVisible(false);

        hotbar = new Group();

        TextureRegionDrawable placeholder = new TextureRegionDrawable(new Texture("placeholder.png"));
        placeholder.setMinHeight((Gdx.graphics.getWidth() * 0.5f) / 11);
        placeholder.setMinWidth((Gdx.graphics.getWidth() * 0.5f) / 11);
        placeholders = new Group();


        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 11; i++) {
                Image image = new Image(placeholder);
                image.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * x - height * (0.015f) * x);
                placeholders.addActor(image);
            }
        }

        for (int i = 1; i<11; i++) {
            Image image = new Image(placeholder);
            image.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * 3.5f - height * (0.015f) * 4.5f);
            hotbar.addActor(image);
        }



        group.setDebug(true);


        this.setPosition(
                Gdx.graphics.getWidth() / 4.0f,
                Gdx.graphics.getHeight() / 4.0f
        );


        this.setSize(width, height);
        picker = new Image(new Texture("inventory_picker.png"));
        picker.setName("mover");


        getTitleTable().addActor(placeholders);
        getTitleTable().addActor(hotbar);
        getTitleTable().addActor(group);
        getTitleTable().add(picker)
                .width((Gdx.graphics.getWidth() * 0.63f) / 10)
                .height((Gdx.graphics.getWidth() * 0.55f) / 10).padRight((width * 0.02f)).padTop(height * 0.3f);


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

        //if the item actions group is visible, do not move cursor in inventory, but on action group
        if (!group.isVisible()) {
            Cell<Image> cell = getTitleTable().getCell(picker);
            float topPedding = cell.getPadTop();
            float sidePadding = cell.getPadRight();
            boolean moveX = false;
            boolean moveY = false;


            if (x != 0) {
                //very specific hand check values. Do not change
                sidePadding += (width * 0.093f * x);
                moveX = true;
            }
            if (y != 0) {
                topPedding += (height * 0.38f * y);
                moveY = true;
            }

            //to keep picker inside of inventory
            if (moveY && topPedding > 0 && topPedding < (height * 0.38f * 3)) {
                cell.padTop(topPedding);
                //move the action menu with cursor to keep it at correct position underneath cursor
                group.moveBy(0, -(Gdx.graphics.getWidth() * 0.48f) / 10 * y);
                /*
                Inventory slot starts at top right (0) underneath is the 10 slot etc.
                 */
                selectedItem += 10 * y;
                System.out.println("top " + -(height * 0.38f * y));
            }

            //same as above
            if (moveX && sidePadding < (width * 0.093f * 10) && sidePadding > 0) {
                cell.padRight(sidePadding);
                group.moveBy(-(width * 0.093f * x), 0);
                selectedItem += x;
                System.out.println("side " + (width * 0.093f * -x));
            }

            System.out.println(selectedItem);
            //you need to invalidate table positions otherwise cursor won't move
            getTitleTable().invalidate();
            getTitleTable().pack();
        } else {
            int size = group.getChildren().size;


            //don't move the cursor out of bounds of group elements
            if (size != selectedItemOption - y && selectedItemOption - y > -1) {

                ImageTextButton currentSelected = (ImageTextButton) group.getChild(selectedItemOption);
                currentSelected.setChecked(false);

                //uses the y argument, as it's just 1 or -1
                selectedItemOption -= y;
                ImageTextButton child = (ImageTextButton) group.getChild(selectedItemOption);
                child.setChecked(true);
            }

        }
    }


    public void toggleItemsOptionMenu(boolean toggleState) {
        group.setVisible(toggleState);
    }

    public boolean isItemOptionOpen() {
        return group.isVisible();
    }

    public void triggerItemAction(UI ui) {
        ImageTextButton currentSelected = (ImageTextButton) group.getChild(selectedItemOption);
        CharSequence text = currentSelected.getText();

        switch (String.valueOf(text)) {
            case DELETE_BUTTON_TEXT:
                boolean b = inventory.removeItem(selectedItem, Inventory.MAX_ITEM_STACK);
                if (!b)
                    ui.displayNotification(2000, "This item can not be deleted!");
                group.setVisible(false);
                selectedItemOption = group.getChildren().size - 1;
                break;
            case USE_BUTTON_TEXT:
                boolean b1 = inventory.useItem(selectedItem);
                if (!b1)
                    ui.displayNotification(2000, "This item can not be used!");
                group.setVisible(false);
                selectedItemOption = group.getChildren().size - 1;
                break;
            case QUICKBAR_BUTTON_TEXT:
                inventory.moveItemToQuickbar(selectedItem, 0);
                group.setVisible(false);
                selectedItemOption = group.getChildren().size - 1;
                break;

        }
    }

    public void addToQuickbar(int quickbarSlot, Texture texture) {
        Image currentSelected = (Image) hotbar.getChild(Math.abs(quickbarSlot - (hotbar.getChildren().size - 1)));
        currentSelected.setDrawable(new TextureRegionDrawable(texture));
    }

    public void setItemAtPosition(int i, Texture texture) {
        Image child = (Image) placeholders.getChild(i);
        child.setDrawable(new TextureRegionDrawable(texture));
    }
}
