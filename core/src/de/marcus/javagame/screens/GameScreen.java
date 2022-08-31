package de.marcus.javagame.screens;

import de.marcus.javagame.entities.Player;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.InputManager;

public class GameScreen extends AbstractScreen {
    Player mainCharacter;
    //Map map;
    InputManager inputManager;

    // StoryHandler sthandler;
    //LoadWorld loader;
    //Entities entities;


    public GameScreen(LoadingScreen app, int profile) {
        super(app);
        //app.dispose();

        //mainCharacter = new Player();
        inputManager = new InputManager(mainCharacter);
    }

    @Override
    public void update(float delta) {
        //story spawns etc
        //inputmanager.keyDown()
    }

    @Override
    public void show() {
        //nur ein Test
        //t = new Texture(Gdx.files.internal("items.png"));
        //  batch.begin();
        //   batch.draw(t, 0, 0);
        //  batch.end();
        super.app.g.setScreen(GameScreenManager.SCREENS.INVENTORY);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void load(int profile) {

    }
}
