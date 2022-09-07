package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.managers.EntityManager;
import de.marcus.javagame.managers.InputManager;
import de.marcus.javagame.world.GameWorld;

public class GameScreen extends AbstractScreen {

    //Map map;
    InputManager inputManager;


    GameWorld gameWorld;
    Label label;


    private final BitmapFont font;
    private final SpriteBatch batch;

    EntityManager entityManager;

    UI ui;

    boolean yes = true;


    // StoryHandler sthandler;
    //LoadWorld loader;
    //Entities entities;


    public GameScreen(LoadingScreen app, int profile) {
        super(app);
        //app.dispose();
        ui = new UI();

        entityManager = new EntityManager();


        inputManager = new InputManager(entityManager.getPlayer());

        font = new BitmapFont();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(entityManager.getPlayer().getCamera().combined);


        Table table = new Table();
        stage.addActor(table);


        table.setDebug(true);
        table.setFillParent(true);
        label = new Label("FPS this is a test for some ui code which is great: " + Gdx.graphics.getFramesPerSecond(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        label.setAlignment(Align.center);


//        System.out.println(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.05f));
        label.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 0.05f), Gdx.graphics.getHeight() - (0.05f * Gdx.graphics.getHeight()));

        table.addActor(label);
        table.addActor(ui.getUiContainer());


        gameWorld = new GameWorld(entityManager.getPlayer().getCamera());

    }

    @Override
    public void update(float delta) {
        //story spawns etc
        inputManager.handleMovement();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (yes) {
            ui.getHealthBar().setValue(3.0f);
            yes = false;
        }


        batch.setProjectionMatrix(entityManager.getPlayer().getCamera().combined);
        gameWorld.render(entityManager.getPlayer().getCamera());


//        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);


        label.setText("FPS: " + Gdx.graphics.getFramesPerSecond());

//        label.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-5f),Gdx.graphics.getHeight()-(0.1f * Gdx.graphics.getHeight()));
//        label.pack();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {
        System.out.println("draw");
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Gdx.input.setInputProcessor(inputManager);
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
