package de.marcus.javagame.testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Test extends Game {

    private Stage stage;
    private Table parent;

    private Label label;

    private Table topUI;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        parent = new Table();

        parent.pad(50);
        topUI = new Table();


        stage.addActor(parent);
        parent.add(topUI).grow();
        parent.setDebug(true);
        topUI.setDebug(true);
        parent.setFillParent(true);
        parent.pad(10f);


        label = new Label("FPS:" + Gdx.graphics.getFramesPerSecond(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        TextButton button = new TextButton("This is a great example text", new TextButton.TextButtonStyle(null, null, null, new BitmapFont()));
        button.setWidth(200);
        button.setHeight(50);


        parent.addActor(label);
        topUI.addActor(button);

        button.top().left();
    }

    @Override
    public void render() {
        super.render();
        topUI.top();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }
}
