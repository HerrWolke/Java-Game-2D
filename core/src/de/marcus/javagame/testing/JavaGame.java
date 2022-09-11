package de.marcus.javagame.testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.screens.LoadingScreen;

public class JavaGame extends Game {
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
    OrthogonalTiledMapRenderer tiledMapRenderer;

    Stage stage;


    @Override
    public void create() {
        this.stage = new Stage(new ScreenViewport());


        gsm = new GameScreenManager(new LoadingScreen());
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(50, 50);
        viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        TmxMapLoader load = new TmxMapLoader();
        TiledMap load1 = load.load("word_tmx/Tilemap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(load1, 0.01f);

        TextureAtlas atlas = new TextureAtlas("file.atlas");
        runningAnimation =
                new Animation<TextureRegion>(0.033f, atlas.findRegions("running/running"), Animation.PlayMode.LOOP);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Label nameLabel = new Label("Name:", skin);


        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Table table = new Table();
        table.setFillParent(true);

        stage.addActor(table);
        table.setDebug(true);

        table.add(nameLabel);
        table.add(nameText).width(100);
        table.row();
        table.add(addressLabel);
        table.add(addressText).expandX().bottom();


    }

    @Override
    public void render() {
        super.render();


        camera.update();
        tiledMapRenderer.setView(camera);
//        batch.setProjectionMatrix(camera.combined);


        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

//        tr.render();

        animTime += Gdx.graphics.getDeltaTime();

//        TextureRegion frame = runningAnimation.getKeyFrame(animTime, true);
//        batch.begin();
//        batch.draw(frame, 25, 25, 10, 10);
//        batch.end();

        tiledMapRenderer.render();
        stage.act();
        stage.draw();
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
