package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.Or;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.InputManager;

public class GameScreen extends AbstractScreen {
    Player mainCharacter;
    //Map map;
    InputManager inputManager;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    OrthographicCamera camera;



    private BitmapFont font;
    private SpriteBatch batch;

    // StoryHandler sthandler;
    //LoadWorld loader;
    //Entities entities;


    public GameScreen(LoadingScreen app, int profile) {
        super(app);
        //app.dispose();

        mainCharacter = new Player(0,0,new Texture("badlogic.jpg"),10,10,10,10,1f);

        inputManager = new InputManager(mainCharacter);

        TmxMapLoader load = new TmxMapLoader();
        TiledMap load1 = load.load("word_tmx/Tilemap.tmx");
        float unitScale = 1f / 512f;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(load1,unitScale);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        font = new BitmapFont();
        batch = new SpriteBatch();

        camera = new OrthographicCamera(50, 50 * (h / w));
        camera.setToOrtho(false, (w / h) * 10, 10);


//        viewport = new StretchViewport(1920,1080,camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        tiledMapRenderer.setView(camera);



    }

    @Override
    public void update(float delta) {
        //story spawns etc
        //inputmanager.keyDown()

        System.out.println("update");
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        Vector3 posCam = new Vector3(0,0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) posCam.x -= (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) posCam.x += (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) posCam.y += (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) posCam.y -= (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;



        camera.translate(posCam);
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }

    @Override
    public void show() {
        //nur ein Test
        //t = new Texture(Gdx.files.internal("items.png"));
        //  batch.begin();
        //   batch.draw(t, 0, 0);
        //  batch.end();
//        super.app.g.setScreen(GameScreenManager.SCREENS.INVENTORY);
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

    public void load(int profile) {

    }
}
