package de.marcus.javagame.graphics.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import de.marcus.javagame.datahandling.data.dialog.Dialog;
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.handlers.DialogHandler;
import de.marcus.javagame.misc.Util;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DialogWindow extends Window {

    Group dialogOptionsGroup;
    Label label;
    TypingLabel dialog;

    Group otherElements;

    int currentSelectedOption;

    DialogHandler dialogHandler;

    UI ui;

    public DialogWindow(Stage stage, UI ui) {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new Texture("dialog.png"))));
        this.ui = ui;
        dialogOptionsGroup = new Group();
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
                if(event.equalsIgnoreCase("OpenShop")) {
                    ui.getShopWindow().generateShop(Shops.POTION_SHOP);
                    setVisible(false);
                }
            }

            public void end() {
                for (int i = 0; i < dialogOptionsGroup.getChildren().size; i++) {
                    ImageTextButton imageTextButton = (ImageTextButton) dialogOptionsGroup.getChild(i);
                    if (!String.valueOf(imageTextButton.getText()).equalsIgnoreCase("")) {
                        imageTextButton.setVisible(true);
                        imageTextButton.setChecked(i == 0);
                    }
                }

                if (dialogHandler.isDialogFinished() && isVisible()) {
                    ui.displayNotification(3000, "Drücke eine beliebige Taste um fortzufahren...");
                }
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
        dialogOptionsGroup.addActor(dialogOption);
        dialogOptionsGroup.addActor(dialogOption2);
        dialogOptionsGroup.addActor(dialogOption3);
        otherElements.addActor(dialog);

        dialogOptionsGroup.setDebug(true);
        getTitleTable().setDebug(true);
        getTitleTable().addActor(dialogOptionsGroup);
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
        if (nextSelection > -1 && nextSelection < getVisibleChildren(dialogOptionsGroup)) {

            ImageTextButton old = (ImageTextButton) dialogOptionsGroup.getChild(currentSelectedOption);
            old.setChecked(false);

            ImageTextButton newButton = (ImageTextButton) dialogOptionsGroup.getChild(nextSelection);
            newButton.setChecked(true);

            currentSelectedOption = nextSelection;
        }
    }

    public boolean setDialogMenuOptions(String menuTitle, String dialogText, String... texts) {
        if (menuTitle.length() < 18 && dialogText.length() < 280 && texts.length == 3) {
            label.setText(menuTitle);

            dialog.setText("{SLOWER}{EASE=1;1;true}" + dialogText);
            for (int i = 0; i < dialogOptionsGroup.getChildren().size; i++) {
                ImageTextButton imageTextButton = (ImageTextButton) dialogOptionsGroup.getChild(i);
                imageTextButton.setText(texts[i]);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean setDialogMenuOptions(Dialog arg) {
        List<String> texts = new ArrayList<>();
        List<String> addToEnd = new ArrayList<>();
        boolean fitsOnScreen = true;

        for (String text : arg.getButtonTexts()) {
            if(text.length() > 52) {
                fitsOnScreen = false;
                break;
            }

            if(!text.equals("") && !(arg.getNextDialog(arg.getButtonTexts().indexOf(text)).isDisableOnOnceFinished() && dialogHandler.hasCurrentDialogBeenCompletedBefore())) {
                texts.add(text);
            } else {
                addToEnd.add("");
            }
        }

        //Add texts to end so no spaces are between buttons
        texts.addAll(addToEnd);


        for (int i = 0; i < dialogOptionsGroup.getChildren().size; i++) {
            ImageTextButton imageTextButton = (ImageTextButton) dialogOptionsGroup.getChild(i);
            imageTextButton.setVisible(false);
        }
        currentSelectedOption = 0;
        String menuTitle = arg.getDialogTitle();
        String dialogText = dialogHandler.hasCurrentDialogBeenCompletedBefore() && !arg.getDialogTextOnceFinished().equals("") ? arg.getDialogTextOnceFinished() : arg.getDialogText();




        if (menuTitle.length() < 18 && dialogText.length() < 280 && fitsOnScreen) {
            label.setText(menuTitle);
            dialog.setText("");
            StringBuilder dialogTextTimingBuilder = new StringBuilder();

            /*
            This builds the pauses after punctuation
            Regex one matches every character except comma and the second regex every comma. The pause on a comma should be shorter
            */
            for (char currentChar : dialogText.toCharArray()) {
                if (String.valueOf(currentChar).matches("[^,]\\p{Punct}")) {
                    dialogTextTimingBuilder.append(currentChar).append("{WAIT}");
                } else if(String.valueOf(currentChar).matches("\\p{Punct}")){
                    dialogTextTimingBuilder.append(currentChar).append("{WAIT=0.5}");
                } else {
                    dialogTextTimingBuilder.append(currentChar);
                }
            }

            dialog.setText("{SLOWER}{EASE=1;1;true}" + dialogTextTimingBuilder);
            for (int i = 0; i < texts.size(); i++) {
                ImageTextButton imageTextButton = (ImageTextButton) dialogOptionsGroup.getChild(i);
                imageTextButton.setText(texts.get(i));
            }
            return true;
        } else {
            System.err.println("Fatal Error. " +
                    "Dialog System encountered a Problem: " +
                    "\nDialog Problem is in " + (menuTitle.length() > 18 ? "the title & " : "")
                    + (dialogText.length() > 280 ? "the text & " : "")
                    + (!fitsOnScreen ? "the buttons" : "")
                    + "\nThe problem dialog is \"" +
                    arg.getDialogTitle() + "\"");
            System.exit(-1);
            return false;
        }
    }

    public boolean areDialogButtonsVisible() {
        return dialogOptionsGroup.getChild(0).isVisible();
    }

    /**
     *
     * @return The amount of children, that are visible within a group
     * This is for dialogs, which have a disabled option after being used once, so you can not click that option anymore (Hope that makes sense)
     */
    private int getVisibleChildren(Group group) {
        SnapshotArray<Actor> children = group.getChildren();

        int visible = 0;
        for (Actor actor : children) {
            if(actor.isVisible()) visible++;
        }

        return visible;
    }
}
