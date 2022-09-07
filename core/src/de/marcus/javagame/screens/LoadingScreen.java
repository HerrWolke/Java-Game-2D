package de.marcus.javagame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.marcus.javagame.entities.logging.LoggingSystem;
import de.marcus.javagame.managers.GameScreenManager;


public class LoadingScreen extends Game {

    GameScreenManager g;
    Animation<TextureRegion> loadingAnimation;
    TextureAtlas atlas;
    boolean loaded;
    public static LoggingSystem loggingSystem;

    @Override
    public void create() {

        long l = System.currentTimeMillis();
        System.out.println(l);
        loggingSystem = new LoggingSystem();


        g = new GameScreenManager(this);


        loaded = true;
        long l1 = (System.currentTimeMillis());
        System.out.println(l1);
    }

    @Override
    public void render() {

        super.render();
        //ScreenUtils.clear(1, 0, 0, 1);
        if (loaded) {

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            g.setScreen(GameScreenManager.SCREENS.START_MENU);
            loaded = false;
            dispose();
        }

        //nur ein test


    }

    @Override
    public void dispose() {


    }
}
