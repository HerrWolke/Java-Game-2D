package de.marcus.javagame.player.inventory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.ui.ui.UI;
import de.marcus.javagame.ui.ui.GenericGameWindow;
import de.marcus.javagame.framework.TextureManager;
import de.marcus.javagame.framework.Util;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryWindow extends GenericGameWindow {


    private static final String DELETE_BUTTON_TEXT = "Löschen";
    private static final String QUICKBAR_BUTTON_TEXT = "Schnellauswahl";
    private static final String USE_BUTTON_TEXT = "Benutzen";

    private boolean waitingForSlotSelection;

    private Stage stage;
    Image picker;
    float width;
    float height;


    Group group;

    Group placeholders;
    Group placeholderNumbers;

    Group hotbar;
    Group hotbarNumbers;
    int selectedItemOption;

    int selectedItem;

    Inventory inventory;


    //TODO: Make inventory picker moveable and not static and replace absolute with relative values

    public InventoryWindow(Inventory inventory, Stage stage) {

        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(TextureManager.getTexture("inventory3"))));
        this.stage = stage;
        this.inventory = inventory;
        selectedItem = 0;
        float screenWidth = Util.getScreenWidth(stage);
        float screenHeight = Util.getScreenHeight(stage);
        width = screenWidth * 0.5f;
        height = screenHeight * 0.45f;
        selectedItemOption = 2;
        this.setResizable(true);

        TextureRegionDrawable itemOption = new TextureRegionDrawable(TextureManager.getTexture("item_option"));
        itemOption.setMinHeight(height / 8f);
        itemOption.setMinWidth(width / 9f);
        TextureRegionDrawable itemOptionSelected = new TextureRegionDrawable(TextureManager.getTexture("item_option_selected"));
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
        hotbarNumbers = new Group();

        TextureRegionDrawable placeholder = new TextureRegionDrawable(TextureManager.getTexture("placeholder"));
        placeholder.setMinHeight((screenWidth * 0.5f) / 11);
        placeholder.setMinWidth((screenWidth * 0.5f) / 11);
        placeholders = new Group();
        placeholderNumbers = new Group();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.padLeft = 40;
        BitmapFont font12 = generator.generateFont(parameter);

        for (int x = 0; x < 3; x++) {
            for (int i = 1; i < 11; i++) {
                Image image = new Image(placeholder);
                image.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * x - height * (0.015f) * x);
                Label label = new Label("", new Label.LabelStyle(font12, null));
                label.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * x - height * (0.015f) * x);

                placeholders.addActor(image);
                placeholderNumbers.addActor(label);
            }
        }

        for (int i = 1; i < 11; i++) {
            Image image = new Image(placeholder);
            image.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * 3.5f - height * (0.015f) * 4.5f);
            Label label = new Label("" + Math.abs(i - 11), new Label.LabelStyle(font12, null));
            label.setPosition(width - placeholder.getMinWidth() * i - width * (0.031f + 0.00325f * (i - 1)), -height * 0.24f - placeholder.getMinHeight() * 3.5f - height * (0.015f) * 4.5f);
            System.out.println("Size of hotbar " + hotbar.getChildren().size);

            hotbar.addActor(image);
            hotbarNumbers.addActor(label);
        }


        group.setDebug(true);


        this.setPosition(
                screenWidth / 4.0f,
                screenHeight / 4.0f
        );


        this.setSize(width, height);
        picker = new Image(new Texture("inventory_picker.png"));
        picker.setName("mover");


        getTitleTable().addActor(placeholders);
        getTitleTable().addActor(placeholderNumbers);
        getTitleTable().addActor(hotbar);
        getTitleTable().addActor(hotbarNumbers);
        getTitleTable().addActor(group);
        getTitleTable().add(picker)
                .width((screenWidth * 0.63f) / 10)
                .height((screenWidth * 0.55f) / 10).padRight((width * 0.02f)).padTop(height * 0.3f);


        this.setModal(false);
        this.setVisible(false);
        this.setMovable(true);
        this.setDebug(true);


    }


    public void handleInput(int keycode, UI ui) {
        if (InventoryControlKey.NAV_KEYS.contains(keycode)) {
            int moveX = 0;
            int moveY = 0;
            if (InventoryControlKey.NAV_UP.contains(keycode)) {
                moveY -= 1;
            } else if (InventoryControlKey.NAV_LEFT.contains(keycode)) {
                moveX += 1;
            } else if (InventoryControlKey.NAV_RIGHT.contains(keycode)) {
                moveX -= 1;
            } else {
                moveY += 1;
            }

            moveSelector(moveX, moveY);
        } else if (InventoryControlKey.CLOSE_MENU.contains(keycode)) {
            if (isItemOptionOpen()) {
                waitingForSlotSelection = false;
                resetItemOptionGroup();
            } else {
                waitingForSlotSelection = false;
                setVisible(false);
            }
        } else if (InventoryControlKey.CHOOSE_OPTION.contains(keycode)) {
            chooseOption(ui);
        } else if (Input.Keys.NUM_0 <= keycode && keycode <= Input.Keys.NUM_9 && waitingForSlotSelection) {
            waitingForSlotSelection = false;
            if (keycode != Input.Keys.NUM_0)
                inventory.moveItemToQuickbar(selectedItem, Math.abs(keycode - 8));
            else
                inventory.moveItemToQuickbar(selectedItem, 9);

        }
    }

    private void chooseOption(UI ui) {
        if (!isItemOptionOpen())
            toggleItemsOptionMenu(true);
        else
            triggerItemAction(ui);
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
                group.moveBy(0, -(Util.getScreenWidth(stage) * 0.48f) / 10 * y);
                /*
                Inventory slot starts at top right (0) underneath is the 10 slot etc.
                 */
                selectedItem += 10 * y;
            }

            //same as above
            if (moveX && sidePadding < (width * 0.093f * 10) && sidePadding > 0) {
                cell.padRight(sidePadding);
                group.moveBy(-(width * 0.093f * x), 0);
                selectedItem += x;
            }

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


    @Override
    public void closeWindow() {
        if (isItemOptionOpen()) {
            waitingForSlotSelection = false;
            resetItemOptionGroup();
        } else {
            waitingForSlotSelection = false;
            setVisible(false);
        }
        super.closeWindow();
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
                    ui.displayNotification(2000, "Du kannst dies nicht löschen!");
                resetItemOptionGroup();
                break;
            case USE_BUTTON_TEXT:
                boolean b1 = inventory.useItem(selectedItem);
                if (!b1)
                    ui.displayNotification(2000, "Du kannst das nicht benutzen!");
                resetItemOptionGroup();
                break;
            case QUICKBAR_BUTTON_TEXT:
                if (inventory.isItemEquitable(selectedItem)) {
                    waitingForSlotSelection = true;
                    ui.displayNotification(8000, "Drücke eine Zahl zwischen 1 - 0 (10)");
                } else {
                    ui.displayNotification(2000, "Du kannst das nicht ausrüsten!");
                }
                resetItemOptionGroup();
                break;

        }
    }

    public void resetItemOptionGroup() {
        group.setVisible(false);
        for (int i = 0; i < group.getChildren().size; i++) {
            ImageTextButton button = (ImageTextButton) group.getChild(i);
            button.setChecked(false);
        }

        selectedItemOption = group.getChildren().size - 1;
        ImageTextButton startButton = (ImageTextButton) group.getChild(selectedItemOption);
        startButton.setChecked(true);
    }

    public void addToQuickbar(int quickbarSlot, TextureRegion texture) {
        Image currentSelected = (Image) hotbar.getChild(Math.abs(quickbarSlot - (hotbar.getChildren().size - 1)));
        currentSelected.setDrawable(new TextureRegionDrawable(texture));
    }

    public void setItemAtPosition(int i, TextureRegion texture, int itemCount) {
        Image child = (Image) placeholders.getChild(i);
        Label label = (Label) placeholderNumbers.getChild(i);
        if (texture != null) {
            child.setDrawable(new TextureRegionDrawable(texture));
            label.setText(itemCount);
        } else {
            child.setDrawable(new TextureRegionDrawable(TextureManager.getTexture("placeholder")));
            label.setText("");
        }
    }

    public void setItemIntoHotbar(int itemPosition, TextureRegion texture, int itemCount) {
        Image child = (Image) hotbar.getChild(itemPosition);
        if (texture != null) {
            child.setDrawable(new TextureRegionDrawable(texture));
        } else {
            child.setDrawable(new TextureRegionDrawable(TextureManager.getTexture("placeholder")));
        }
    }

    @Getter
    public enum InventoryControlKey {
        CLOSE_MENU(Input.Keys.BACKSPACE, Input.Keys.ESCAPE),

        CLOSE_INVENTORY(Input.Keys.E),
        CHOOSE_OPTION(Input.Keys.ENTER, Input.Keys.SPACE),

        NAV_LEFT(Input.Keys.LEFT),
        NAV_RIGHT(Input.Keys.RIGHT),
        NAV_UP(Input.Keys.UP),
        NAV_DOWN(Input.Keys.DOWN),
        NAV_KEYS(NAV_LEFT, NAV_RIGHT, NAV_DOWN, NAV_UP);


        InventoryControlKey(Integer... ints) {
            this.controls = Arrays.asList(ints);
        }

        InventoryControlKey(InventoryControlKey... key) {
            this.controls = new ArrayList<>();
            Arrays.stream(key).forEach(inventoryControlKey -> controls.addAll(inventoryControlKey.getControls()));
        }

        public boolean contains(int keycode) {
            return controls.contains(keycode);
        }

        private final List<Integer> controls;

    }
}
