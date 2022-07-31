package de.marcus.javagame.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen {
    protected final LoadingScreen app;
    Stage stage;
    public AbstractScreen(LoadingScreen app) {
        this.app = app;
        this.stage = new Stage();
    }
    public abstract void update(float delta);
    @Override
    public void render(float delta) {
        update(delta);
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
    }
    @Override
    public void dispose(){
        this.stage.dispose();
    }

}
