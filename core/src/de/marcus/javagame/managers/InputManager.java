package de.marcus.javagame.managers;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import de.marcus.javagame.entities.Player;

import java.util.HashMap;

public class InputManager implements InputProcessor {
    Player p;

    enum CONTROLS {
        RUN_FORWARD,
        RUN_BACKWARD,
        RUN_LEFT,
        RUN_RIGHT,
        SETTINGS,
        ATTACK,
        BLOCK_SPEAK,

    }

    HashMap<CONTROLS, Integer> settings = new HashMap<CONTROLS, Integer>();

    public InputManager(Player player) {
        settings.put(CONTROLS.RUN_FORWARD, Input.Keys.W);
        settings.put(CONTROLS.RUN_BACKWARD, Input.Keys.S);
        settings.put(CONTROLS.RUN_LEFT, Input.Keys.A);
        settings.put(CONTROLS.RUN_RIGHT, Input.Keys.D);
        settings.put(CONTROLS.SETTINGS, Input.Keys.ESCAPE);
        settings.put(CONTROLS.ATTACK, Input.Keys.LEFT);
        settings.put(CONTROLS.BLOCK_SPEAK, Input.Keys.RIGHT);
        p = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == settings.get(CONTROLS.RUN_FORWARD)) {
            p.runForwards();
            return false;
        } else if (keycode == settings.get(CONTROLS.RUN_BACKWARD)) {
            p.runBackwards();
            return false;
        } else if (keycode == settings.get(CONTROLS.RUN_RIGHT)) {
            p.runRight();
            return false;
        } else if (keycode == settings.get(CONTROLS.RUN_LEFT)) {
            p.runLeft();
            return false;
        } else if (keycode == settings.get(CONTROLS.SETTINGS)) {
            //new screen
            return false;
        } else if (keycode == settings.get(CONTROLS.ATTACK)) {
            p.attack();
            return false;
        } else if (keycode == settings.get(CONTROLS.BLOCK_SPEAK)) {
            //wenn person mit der man interacten kann in range vor ihm
            p.interact();
            //sonst
            p.block();
            return false;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
