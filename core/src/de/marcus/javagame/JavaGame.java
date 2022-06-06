package de.marcus.javagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JavaGame extends ApplicationAdapter {
    Batch batch;
    OrthographicCamera camera;

    Viewport viewport;
    ShapeRenderer shapeRenderer;
    Color color = Color.WHITE;

    @Override
    public void create() {


        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(50, 50);
        viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

    }

    @Override
    public void render() {
        super.render();


        camera.update();


        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);


    }

    public void update() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {

    }

}
