package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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

        mainCharacter = new Player(0,0,new Texture("badlogic.jpg"),10,10,10,10,1f);

        inputManager = new InputManager(mainCharacter);

//        TmxMapLoader load = new TmxMapLoader();
//        TiledMap load1 = load.load("map.tmx");
//        OrthogonalTiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(load1);

    }

    @Override
    public void update(float delta) {
        //story spawns etc
        //inputmanager.keyDown()


    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void show() {
        //nur ein Test
        //t = new Texture(Gdx.files.internal("items.png"));
        //  batch.begin();
        //   batch.draw(t, 0, 0);
        //  batch.end();
//        super.app.g.setScreen(GameScreenManager.SCREENS.INVENTORY);
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
