package de.marcus.javagame.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import de.marcus.javagame.framework.data.SavedataHandler;
import de.marcus.javagame.framework.shop.Shops;
import de.marcus.javagame.ui.screens.GameScreen;
import de.marcus.javagame.ui.ui.UI;
import de.marcus.javagame.framework.dialog.DialogHandler;
import de.marcus.javagame.framework.Util;

import java.util.HashMap;

public class InputManager implements InputProcessor {
    Player p;
    UI ui;
    GameScreen screen;
    int i = 0;

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

    public InputManager(Player player, UI ui, GameScreen screen) {
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
        this.screen = screen;
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

        if((keycode > 6 && keycode < 17) && !ui.getInventoryWindow().isVisible()) {
            int keycodeTranslated = keycode == 7 ? 9 : keycode-8;
            System.out.println("This say its code " + keycodeTranslated);
            ui.getPlayer().getInventory().useItemFromHotbar(keycodeTranslated);
        }

//        if (keycode == Input.Keys.NUMPAD_5) {
//            System.out.println(i);
//            boolean b = screen.getGameWorld().setMap(i);
//            System.out.println("changing map");
//            if(!b) {
//                i = 0;
//                System.out.println("reset");
//            }else {
//                i++;
//            }
//        }

        if (keycode == Input.Keys.NUMPAD_7) {
            SavedataHandler.save(p.getInventory());
        }

        if (keycode == Input.Keys.PAGE_UP) {
            p.setHealth(p.getHealth() - 1);
        }

        if (keycode == Input.Keys.NUMPAD_6) {
            p.setHealth(p.getHealth() + 1);
        }

        if (keycode == Input.Keys.NUMPAD_9) {
            ui.getDialogWindow().getDialogHandler().setCurrentDialog(DialogHandler.Dialogs.POTION_SHOP_DIALOG);
        }

        if (keycode == Input.Keys.NUMPAD_8) {
            ui.getPlayer().respwawn();
            System.out.println("respawn");
        }

        if (keycode == Input.Keys.NUMPAD_6) {
            ui.getShopWindow().generateShop(Shops.POTION_SHOP);
            Gdx.input.setCursorCatched(false);
            Gdx.input.setCursorPosition((int) (Util.getScreenWidth(ui.getStage())/2), (int) (Util.getScreenHeight(ui.getStage())/2));
        }
//        if (keycode == Input.Keys.NUMPAD_5) {
//            TextField cheatInput = new TextField("",new TextField.TextFieldStyle(new BitmapFont(), Color.BLACK,null,null,null));
//            Dialog dialog = new Dialog("", new Window.WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(TextureManager.getTexture("generic_dialog"))));
//
//            Stage stage = ui.getStage();
//
//
//            dialog.getContentTable().add(cheatInput);
//
//            float screenHeight = Util.getScreenHeight(stage);
//            float screenWidth = Util.getScreenWidth(stage);
//            dialog.setSize(screenWidth / 5.0f, (screenHeight / 5.0f) * Util.getReversedAspectRatio(stage));
//            dialog.setPosition((screenWidth / 2.0f) - dialog.getWidth() / 2, (screenHeight / 2.0f) - dialog.getHeight() / 2);
//
//            stage.addActor(dialog);
//        }


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
