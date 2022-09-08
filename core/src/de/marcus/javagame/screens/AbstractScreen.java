package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class AbstractScreen implements Screen {
    protected final LoadingScreen app;
    Stage stage;

    public AbstractScreen(LoadingScreen app) {
        this.app = app;
        this.stage = new Stage(new ScreenViewport());
    }

    public abstract void update(float delta);

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        System.out.println("update viewp");
    }

    @Override
    public void dispose() {

        this.stage.dispose();
    }

}
