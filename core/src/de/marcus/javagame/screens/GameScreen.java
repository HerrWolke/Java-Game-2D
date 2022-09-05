package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.managers.InputManager;
import de.marcus.javagame.managers.TextureManager;
import de.marcus.javagame.world.GameWorld;

public class GameScreen extends AbstractScreen {
    Player mainCharacter;
    //Map map;
    InputManager inputManager;


    GameWorld gameWorld;
    Label label;


    private final BitmapFont font;
    private final SpriteBatch batch;

    // StoryHandler sthandler;
    //LoadWorld loader;
    //Entities entities;


    public GameScreen(LoadingScreen app, int profile) {
        super(app);
        //app.dispose();


        mainCharacter = new Player(0, 0, new Texture("badlogic.jpg"), 10, 10, 10, 10, 1f);

        inputManager = new InputManager(mainCharacter);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        font = new BitmapFont();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(mainCharacter.getCamera().combined);
        Table table = new Table();
        stage.addActor(table);
        table.pad(50f);
//        table.setBackground(new TextureRegionDrawable(TextureManager.getTexture("background")));

        table.setDebug(true);
        table.setFillParent(true);
        label = new Label("FPS this is a test for some ui code which is great: " + Gdx.graphics.getFramesPerSecond(),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        label.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-5f),Gdx.graphics.getHeight()-(0.1f * Gdx.graphics.getHeight()));

        table.add(label);


        gameWorld = new GameWorld(mainCharacter.getCamera());

    }

    @Override
    public void update(float delta) {
        //story spawns etc
        //inputmanager.keyDown()


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch.setProjectionMatrix(mainCharacter.getCamera().combined);




        Vector3 posCam = new Vector3(0, 0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            posCam.x -= (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            posCam.x += (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            posCam.y += (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            posCam.y -= (float) Math.round(5 * Gdx.graphics.getDeltaTime() * 50) / 50f;


        mainCharacter.move(posCam.x, posCam.y);

        gameWorld.render(mainCharacter.getCamera());

        batch.begin();
        mainCharacter.render(batch,1,1);
//        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();


//        label.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        label.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-5f),Gdx.graphics.getHeight()-(0.1f * Gdx.graphics.getHeight()));

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {
        //nur ein Test
        //t = new Texture(Gdx.files.internal("items.png"));
        //  batch.begin();
        //   batch.draw(t, 0, 0);
        //  batch.end();
//        super.app.g.setScreen(GameScreenManager.SCREENS.INVENTORY);

        System.out.println("draw");
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
