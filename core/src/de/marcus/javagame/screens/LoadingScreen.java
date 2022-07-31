package de.marcus.javagame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.marcus.javagame.managers.GameScreenManager;

public class LoadingScreen extends Game {
    SpriteBatch batch;
    Texture img;
    GameScreenManager g;
    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        g = new GameScreenManager(this);

    }

    @Override
    public void render () {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
        //nur ein test
        g.setScreen(GameScreenManager.SCREENS.GAME);

    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
