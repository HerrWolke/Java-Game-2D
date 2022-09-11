package de.marcus.javagame.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.marcus.javagame.EffectType;
import de.marcus.javagame.datahandling.SavedataHandler;
import de.marcus.javagame.datahandling.data.Inventory;
import de.marcus.javagame.entities.StatusEffect;
import de.marcus.javagame.graphics.ui.UI;
import de.marcus.javagame.managers.ContactListenerExtern;
import de.marcus.javagame.managers.EntityManager;
import de.marcus.javagame.managers.InputManager;
import de.marcus.javagame.managers.SoundManager;
import de.marcus.javagame.world.GameWorld;

public class GameScreen extends AbstractScreen {


    InputManager inputManager;
    World world ;


    GameWorld gameWorld;
    Label label;
   //testen
   Box2DDebugRenderer debugRenderer;
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
        world  = new World(new Vector2(0, 0), true);
        FileHandle dirHandle;
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            dirHandle = Gdx.files.internal("some/directory");
        } else {
            // ApplicationType.Desktop ..
            dirHandle = Gdx.files.internal("sfx/");
        }
        FileHandle internal = Gdx.files.internal("sfx");
        System.out.println("es " + internal.exists());
        for (FileHandle entry: internal.list()) {
            System.out.println(entry.name());
        }

        entityManager = SavedataHandler.load(EntityManager.class);
        entityManager.getPlayer().setUI(stage);
        this.ui = entityManager.getPlayer().getUi();
//        Inventory inventory = SavedataHandler.load(Inventory.class);
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.render(world, entityManager.getPlayer().getCamera().combined);

//        System.out.println(inventory.toString());
        System.out.println(entityManager.toString());
//        entityManager.getPlayer().getEffects().add(new StatusEffect(EffectType.HEAL,1000));


        inputManager = new InputManager(entityManager.getPlayer(), ui);


        font = new BitmapFont();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(entityManager.getPlayer().getCamera().combined);

        gameWorld = new GameWorld(entityManager.getPlayer().getCamera());



        //TODO: Player body
        //setzt Body in Player
        entityManager.getPlayer().setPlayerBody(world.createBody(entityManager.getPlayer().getPlayerBodyDef())) ;
        //setzt die fixture
        entityManager.getPlayer().setPlayerFixture(entityManager.getPlayer().getPlayerBody().createFixture(entityManager.getPlayer().getPlayerFixtureDef()));
        world.setContactListener(new ContactListenerExtern());

    }

    @Override
    public void update(float delta) {
        //story spawns etc
        inputManager.handleMovement();
        ui.update(entityManager.getPlayer().getPosition().x,entityManager.getPlayer().getPosition().y);
        world.step(1/60f, 6, 2);
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




        batch.setProjectionMatrix(entityManager.getPlayer().getCamera().combined);
        gameWorld.render(entityManager.getPlayer().getCamera());
        entityManager.render(batch);


//        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);


//        label.setText("FPS: " + Gdx.graphics.getFramesPerSecond());

//        label.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-5f),Gdx.graphics.getHeight()-(0.1f * Gdx.graphics.getHeight()));
//        label.pack();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void show() {
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
