package de.marcus.javagame.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.datahandling.data.datahandling.SavedataHandler;
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.graphics.ui.windows.InventoryWindow;
import de.marcus.javagame.handlers.DialogHandler;
import de.marcus.javagame.misc.Util;

import java.util.HashMap;

public class InputManager implements InputProcessor {
    Player p;
    UI ui;

    enum CONTROLS {
        RUN_FORWARD,
        RUN_BACKWARD,
        RUN_LEFT,
        RUN_RIGHT,
        SETTINGS,
        ATTACK,
        BLOCK_SPEAK,
        OPEN_INVENTORY,
        CLOSE_DIALOG

    }

    HashMap<CONTROLS, Integer> settings = new HashMap<CONTROLS, Integer>();

    public InputManager(Player player, UI ui) {
        settings.put(CONTROLS.RUN_FORWARD, Input.Keys.W);
        settings.put(CONTROLS.RUN_BACKWARD, Input.Keys.S);
        settings.put(CONTROLS.RUN_LEFT, Input.Keys.A);
        settings.put(CONTROLS.RUN_RIGHT, Input.Keys.D);
        settings.put(CONTROLS.SETTINGS, Input.Keys.ESCAPE);
        settings.put(CONTROLS.ATTACK, Input.Buttons.LEFT);
        settings.put(CONTROLS.BLOCK_SPEAK, Input.Keys.RIGHT);
        settings.put(CONTROLS.OPEN_INVENTORY, Input.Keys.E);
        settings.put(CONTROLS.CLOSE_DIALOG, Input.Keys.BACKSPACE);
        p = player;
        this.ui = ui;
    }


    public void handleMovement() {
        Vector3 posCam = new Vector3(0, 0, 0);

        if (ui.isPlayerAllowedToMove()) {
            if (Gdx.input.isKeyPressed(settings.get(CONTROLS.RUN_LEFT)))
                posCam.x -= 1;

            if (Gdx.input.isKeyPressed(settings.get(CONTROLS.RUN_RIGHT)))
                posCam.x += 1;

            if (Gdx.input.isKeyPressed(settings.get(CONTROLS.RUN_FORWARD)))
                posCam.y += 1;

            if (Gdx.input.isKeyPressed(settings.get(CONTROLS.RUN_BACKWARD)))
                posCam.y -= 1;
        }


//        System.out.printf("x: %s, y: %s",posCam.x,posCam.y);

        p.move(posCam.x, posCam.y, true,null);
    }

    @Override
    public boolean keyDown(int keycode) {

        ui.handleUIInput(keycode);

        if (keycode == settings.get(CONTROLS.SETTINGS)) {
            //new screen

        }




        if (keycode == settings.get(CONTROLS.BLOCK_SPEAK)) {
            //wenn person mit der man interacten kann in range vor ihm
            p.interact();
            //sonst
            p.block();

        }

        if (keycode == Input.Keys.NUM_5) {
            p.setHealth(p.getHealth() - 1);
        }

        if (keycode == Input.Keys.NUM_7) {
            SavedataHandler.save(p.getInventory());
        }

        if (keycode == Input.Keys.NUM_6) {
            p.setHealth(p.getHealth() + 1);
        }

        if (keycode == Input.Keys.NUM_9) {
            ui.getDialogWindow().getDialogHandler().setCurrentDialog(DialogHandler.Dialogs.POTION_SHOP_DIALOG);
        }

        if (keycode == Input.Keys.NUMPAD_6) {
            ui.getShopWindow().generateShop(Shops.POTION_SHOP);
        }
        if (keycode == Input.Keys.NUMPAD_5) {
            TextField cheatInput = new TextField("",new TextField.TextFieldStyle(new BitmapFont(), Color.BLACK,null,null,null));
            Dialog dialog = new Dialog("", new Window.WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(new Texture("generic_dialog.png"))));

            Stage stage = ui.getStage();


            dialog.getContentTable().add(cheatInput);

            float screenHeight = Util.getScreenHeight(stage);
            float screenWidth = Util.getScreenWidth(stage);
            dialog.setSize(screenWidth / 5.0f, (screenHeight / 5.0f) * Util.getReversedAspectRatio(stage));
            dialog.setPosition((screenWidth / 2.0f) - dialog.getWidth() / 2, (screenHeight / 2.0f) - dialog.getHeight() / 2);

            stage.addActor(dialog);
        }


        if (ui.getInventoryWindow().isVisible()) {
            ui.getInventoryWindow().handleInput(keycode, ui);
        }

        if (ui.getShopWindow().isVisible() && InventoryWindow.InventoryControlKey.CLOSE_MENU.contains(keycode)) {
            ui.getShopWindow().setVisible(false);
        }

        if (ui.getDialogWindow().isVisible()) {
            if (ui.getDialogWindow().areDialogButtonsVisible()) {
                ui.getDialogWindow().handleInput(keycode);
            } else {
                if (!ui.getDialogWindow().getDialogHandler().isDialogActive()) {
                    ui.getDialogWindow().setVisible(false);
                }
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == settings.get(CONTROLS.SETTINGS)) {
            //new screen
            return false;
        }
        if (keycode == settings.get(CONTROLS.ATTACK)) {
            p.attackStop();
            return false;
        }
        if (keycode == settings.get(CONTROLS.BLOCK_SPEAK)) {
            //wenn person mit der man interacten kann in range vor ihm
            p.interactStop();
            //sonst
            p.blockStop();
            return false;
        }
        if (keycode == settings.get(CONTROLS.RUN_LEFT)) {

            return false;
        }
        if (keycode == settings.get(CONTROLS.RUN_RIGHT)) {

            return false;
        }
        if (keycode == settings.get(CONTROLS.RUN_FORWARD)) {

            return false;
        }
        if (keycode == settings.get(CONTROLS.RUN_BACKWARD)) {

            return false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == (settings.get(CONTROLS.ATTACK))) {
            p.attack();
            return false;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == settings.get(CONTROLS.ATTACK)) {
            p.attackStop();


            return false;
        }
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void setNewSetting(CONTROLS control, int keycode) {
        settings.put(control, keycode);
    }
}
