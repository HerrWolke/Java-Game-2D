package de.marcus.javagame.graphics.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import de.marcus.javagame.datahandling.data.dialog.Dialog;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.handlers.DialogHandler;
import de.marcus.javagame.misc.Util;
import lombok.Getter;

import java.util.List;

@Getter
public class DialogWindow extends Window {

    Group postion;
    Label label;
    TypingLabel dialog;

    Group otherElements;

    int currentSelectedOption;

    DialogHandler dialogHandler;

    UI ui;

    public DialogWindow(Stage stage, UI ui) {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("dialog.png"))));
        this.ui = ui;
        postion = new Group();
        otherElements = new Group();
        dialogHandler = new DialogHandler(this);

        float screenWidth = Util.getScreenWidth(stage);
        float screenHeight = Util.getScreenHeight(stage);
        float width = screenWidth * 0.5f;
        float height = screenHeight * 0.45f;

        //The label has to be set to a text then reset to no text so the header is correctly positioned
        label = new Label("t", new Label.LabelStyle(Util.getFontForScreenSize(stage, 35), Color.WHITE));
        label.setHeight(label.getHeight());
        label.setText("");
        label.setWidth(width * 0.33f);

        label.setAlignment(Align.left);
        dialog = new TypingLabel("", new Label.LabelStyle(Util.getFontForScreenSize(stage, 20), Color.WHITE));
        dialog.setWrap(true);
        dialog.setPosition((width * 0.9f) / 5f, -height / 2.25f);
        dialog.setWidth((width * 0.8f));

        dialog.setTypingListener(new TypingAdapter() {
            public void event(String event) {
                System.out.println("Received text event: " + event);
            }

            public void end() {
                for (int i = 0; i < postion.getChildren().size; i++) {
                    ImageTextButton imageTextButton = (ImageTextButton) postion.getChild(i);
                    if (!String.valueOf(imageTextButton.getText()).equalsIgnoreCase("")) {
                        imageTextButton.setVisible(true);
                        imageTextButton.setChecked(i == 0);
                    }
                }

                if (dialogHandler.isDialogFinished())
                    ui.displayNotification(3000, "Drücke eine beliebige Taste um fortzufahren...");
            }
        });

        TextureRegionDrawable itemOption = new TextureRegionDrawable(new Texture("dialog_option.png"));
        TextureRegionDrawable itemOptionSelected = new TextureRegionDrawable(new Texture("dialog_option_selected.png"));


        ImageTextButton dialogOption = new ImageTextButton("", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, Util.getFontForScreenSize(stage, 25)));
        ImageTextButton dialogOption2 = new ImageTextButton("", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, Util.getFontForScreenSize(stage, 25)));
        ImageTextButton dialogOption3 = new ImageTextButton("", new ImageTextButton.ImageTextButtonStyle(itemOption, itemOption, itemOptionSelected, Util.getFontForScreenSize(stage, 25)));
        dialogOption.setVisible(false);
        dialogOption2.setVisible(false);
        dialogOption3.setVisible(false);

        dialogOption.setChecked(true);

        dialogOption.setWidth((width * 0.8f));
        dialogOption.setHeight(height * 0.12f);

        dialogOption2.setWidth((width * 0.8f));
        dialogOption2.setHeight(height * 0.12f);

        dialogOption3.setWidth((width * 0.8f));
        dialogOption3.setHeight(height * 0.12f);

        label.setPosition(label.getWidth() * 1 / 7f, -label.getHeight() * 1.8f);
        dialogOption.setPosition((width * 0.9f) / 2f - dialogOption.getWidth() / 2.75f, -height / 1.5f);
        dialogOption2.setPosition((width * 0.9f) / 2f - dialogOption.getWidth() / 2.75f, -height / 1.5f - dialogOption.getHeight() * 1.1f);
        dialogOption3.setPosition((width * 0.9f) / 2f - dialogOption.getWidth() / 2.75f, -height / 1.5f - dialogOption.getHeight() * 2.2f);


        otherElements.addActor(label);
        postion.addActor(dialogOption);
        postion.addActor(dialogOption2);
        postion.addActor(dialogOption3);
        otherElements.addActor(dialog);

        postion.setDebug(true);
        getTitleTable().setDebug(true);
        getTitleTable().addActor(postion);
        getTitleTable().addActor(otherElements);

        this.setModal(false);
        this.setVisible(false);
        this.setMovable(false);
        this.setDebug(true);


        this.setPosition(
                screenWidth / 4.0f,
                screenHeight / 4.0f
        );


        this.setSize(width, height);


    }

    public void handleInput(int keycode) {

        if (InventoryWindow.InventoryControlKey.NAV_KEYS.contains(keycode)) {
            int moveY = 0;
            if (InventoryWindow.InventoryControlKey.NAV_UP.contains(keycode)) {
                moveY -= 1;
            } else if (InventoryWindow.InventoryControlKey.NAV_DOWN.contains(keycode)) {
                moveY += 1;
            }

            moveSelector(moveY);
        } else if (InventoryWindow.InventoryControlKey.CHOOSE_OPTION.contains(keycode)) {
            Dialog retrievedDialog = dialogHandler.dialogButtonPressed(currentSelectedOption);
            if (retrievedDialog != null) {
                setDialogMenuOptions(retrievedDialog);
            }
        }
    }

    private void moveSelector(int moveY) {
        int nextSelection = currentSelectedOption + moveY;
        if (nextSelection > -1 && nextSelection < postion.getChildren().size) {
            ImageTextButton old = (ImageTextButton) postion.getChild(currentSelectedOption);
            old.setChecked(false);

            ImageTextButton newButton = (ImageTextButton) postion.getChild(nextSelection);
            newButton.setChecked(true);

            currentSelectedOption = nextSelection;
        }
    }

    public boolean setDialogMenuOptions(String menuTitle, String dialogText, String... texts) {
        if (menuTitle.length() < 18 && dialogText.length() < 280 && texts.length == 3) {
            label.setText(menuTitle);

            dialog.setText("{SLOWER}{EASE=1;1;true}" + dialogText);
            for (int i = 0; i < postion.getChildren().size; i++) {
                ImageTextButton imageTextButton = (ImageTextButton) postion.getChild(i);
                imageTextButton.setText(texts[i]);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean setDialogMenuOptions(Dialog arg) {
        for (int i = 0; i < postion.getChildren().size; i++) {
            ImageTextButton imageTextButton = (ImageTextButton) postion.getChild(i);
            imageTextButton.setVisible(false);
        }
        String menuTitle = arg.getDialogTitle();
        String dialogText = arg.getDialogText();
        List<String> texts = arg.getButtonTexts();

        if (menuTitle.length() < 18 && dialogText.length() < 280) {
            System.out.println("menu title " + menuTitle);
            label.setText(menuTitle);
            dialog.setText("");
            dialog.setText("{SLOWER}{EASE=1;1;true}" + dialogText);
            for (int i = 0; i < postion.getChildren().size; i++) {
                ImageTextButton imageTextButton = (ImageTextButton) postion.getChild(i);
                imageTextButton.setText(texts.get(i));
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean areDialogButtonsVisible() {
        return postion.getChild(0).isVisible();
    }
}
