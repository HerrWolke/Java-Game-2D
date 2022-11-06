package de.marcus.javagame.z_testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    Texture img;
    private Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        stage = new Stage();

        Table header = new Table();
        header.add(new Image(img)).width(150f).height(150f).expandX().align(Align.left);
        header.add(new Image(img)).width(150f).height(150f).expandX().align(Align.center);
        header.add(new Image(img)).width(150f).height(150f).expandX().align(Align.right);

        Table main = new Table();
        main.add(header).fill();
        main.row();
        main.add(new Image(img)).expandX().fill();
        main.debug();
        stage.addActor(main);
        main.setFillParent(true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();

/*		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}