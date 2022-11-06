package de.marcus.javagame.ui.screens;

import de.marcus.javagame.ui.screens.*;

import java.util.HashMap;

public class GameScreenManager {
    private final LoadingScreen app;
    public HashMap<SCREENS, AbstractScreen> screens;

    public enum SCREENS {
        LOAD,
        START_MENU,
        MENU,
        SELECT_PROFILE,
        INVENTORY,
        GAME1,
        GAME2,
        GAME3,
        SETTINGS

    }

    public GameScreenManager(final LoadingScreen app) {
        this.app = app;
        initGameScreen();
        setScreen(SCREENS.LOAD);
    }

    //erster Screen wird gesetzt
    public void initGameScreen() {
        this.screens = new HashMap<>();
        this.screens.put(SCREENS.START_MENU, new StartMenuScreen(app));
        this.screens.put(SCREENS.MENU, new MenuScreen(app));
        this.screens.put(SCREENS.SELECT_PROFILE, new SelectProfileScreen(app));
        GameScreen gameScreen = new GameScreen(app, 1);
        this.screens.put(SCREENS.GAME1, gameScreen);
        this.screens.put(SCREENS.GAME2, gameScreen);
        this.screens.put(SCREENS.GAME3, gameScreen);
        this.screens.put(SCREENS.SETTINGS, new SettingScreen(app));
        //weitere screens

    }

    //fügen Screen hinzu
    public void setScreen(SCREENS screen) {
        app.setScreen(screens.get(screen));

    }

    public void dispose() {
        for (AbstractScreen s : screens.values()) {
            if (s != null) {
                s.dispose();
            }
        }
    }


}
