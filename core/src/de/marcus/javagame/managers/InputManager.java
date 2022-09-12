package de.marcus.javagame.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import de.marcus.javagame.datahandling.data.datahandling.SavedataHandler;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.handlers.DialogHandler;

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

        p.move(posCam.x, posCam.y);
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == settings.get(CONTROLS.SETTINGS)) {
            //new screen

        }


        if (keycode == settings.get(CONTROLS.OPEN_INVENTORY)) {
            if(ui.isPlayerAllowedToMove()) {
                ui.changeInventoryShowState();
            }
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

        if (keycode == Input.Keys.NUMPAD_9) {
            System.out.println("hit the key");
           ui.getDialog().getDialogHandler().setCurrentDialog(DialogHandler.Dialogs.TEST_DIALOG);
        }

        if (ui.getInventory().isVisible()) {
            ui.getInventory().handleInput(keycode, ui);
        }

        if(ui.getDialog().isVisible() && ui.getDialog().areDialogButtonsVisible()) {
            ui.getDialog().handleInput(keycode);
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
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == settings.get(CONTROLS.ATTACK)) {
            p.attackStop();
            return true;
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
