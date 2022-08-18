package de.marcus.javagame.managers;

import de.marcus.javagame.screens.*;

import java.util.HashMap;

public class GameScreenManager {
    private final LoadingScreen app;
    HashMap<SCREENS, AbstractScreen> screens;

    public enum SCREENS{
        LOAD,
        START_MENU,
        MENU,
        SELECT_PROFILE,
        INVENTORY,
        GAME
    }
    public GameScreenManager(final LoadingScreen app) {
        this.app = app;
        initGameScreen();
        setScreen(SCREENS.LOAD);
    }
    //erster Screen wird gesetzt
    public void initGameScreen(){
        this.screens = new HashMap<>();
        this.screens.put(SCREENS.START_MENU, new StartMenuScreen(app));
        this.screens.put(SCREENS.MENU, new MenuScreen(app));
        this.screens.put(SCREENS.SELECT_PROFILE, new SelectProfileScreen(app));
        this.screens.put(SCREENS.INVENTORY, new InventoryScreen(app));
        this.screens.put(SCREENS.GAME, new GameScreen(app));
        //weitere screens

    }
    //fügen Screen hinzu
    public void setScreen(SCREENS screen){
        app.setScreen(screens.get(screen));
    }

    public void dispose(){
        for(AbstractScreen s : screens.values()){
            if( s != null){
                s.dispose();
            }
        }
    }
}
