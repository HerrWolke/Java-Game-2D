package de.marcus.javagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.screens.GameScreen;
import de.marcus.javagame.screens.LoadingScreen;

public class JavaGame extends ApplicationAdapter {
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;
    ShapeRenderer shapeRenderer;
    Color color = Color.WHITE;
    TiledMap map;
    TiledMapRenderer tr;
    public Animation<TextureRegion> runningAnimation;
    float animTime = 0f;
    GameScreenManager gsm;


    @Override
    public void create() {

        gsm = new GameScreenManager(new LoadingScreen());
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(50, 50);
        viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
//        map = new TmxMapLoader().load(Gdx.files.internal("map.tmx").file().getAbsolutePath());
//        tr = new OrthogonalTiledMapRenderer(map);
//        tr.setView(camera);

//        TexturePacker.process("testimage","testimage/", "file");


        TextureAtlas atlas = new TextureAtlas("file.atlas");
        runningAnimation =
                new Animation<TextureRegion>(0.033f, atlas.findRegions("running/running"), Animation.PlayMode.LOOP);

    }

    @Override
    public void render() {
        super.render();


        camera.update();
        batch.setProjectionMatrix(camera.combined);


        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

//        tr.render();

        animTime += Gdx.graphics.getDeltaTime();

        TextureRegion frame = runningAnimation.getKeyFrame(animTime, true);
        batch.begin();
        batch.draw(frame, 25, 25, 10,10);
        batch.end();
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
