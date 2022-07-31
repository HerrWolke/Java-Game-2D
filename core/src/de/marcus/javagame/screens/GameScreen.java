package de.marcus.javagame.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.marcus.javagame.managers.GameScreenManager;

public class GameScreen extends AbstractScreen {
    Texture t;
    SpriteBatch batch;
    public GameScreen(LoadingScreen app) {
        super(app);
        app.dispose();
        batch = new SpriteBatch();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        //nur ein Test
        t = new Texture(Gdx.files.internal("items.png"));
        batch.begin();
        batch.draw(t, 0, 0);
        batch.end();
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
}
