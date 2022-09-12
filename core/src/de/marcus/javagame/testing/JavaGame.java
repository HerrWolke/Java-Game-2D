package de.marcus.javagame.testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.graphics.screens.LoadingScreen;
import org.w3c.dom.Text;

public class JavaGame extends Game {
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Texture texture;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    public static final float UNIT_SCALE = 1f / 96f;
    @Override
    public void create() {
        viewport = new ScreenViewport();
        camera = new OrthographicCamera(50,50);
        batch = new SpriteBatch();

        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        map = tmxMapLoader.load("word_tmx/Boss.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);
        renderer.setView(camera);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        camera.update();


        camera.position.set(new Vector3(43,71,0));
        renderer.setView(camera);
        renderer.render();
        batch.begin();





        batch.end();

    }

    public void update() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);

    }

    @Override
    public void dispose() {
       map.dispose();
    }

}
