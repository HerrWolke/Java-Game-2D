package de.marcus.javagame.z_testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JavaGame extends Game {
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Texture texture;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    Box2DDebugRenderer debugRenderer;
    public static final float UNIT_SCALE = 1f / 96f;
    World world;

    Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Table table = new Table();
        Label label = new Label("sdfasdfafasfasdfasdfasdfadsf", new Label.LabelStyle(new BitmapFont(), null));
        Label label2 = new Label("asdfasdfadfadfasdfasdfasdfadsfadsfasdfasdfadfasdfasdfasdfasdf1111111", new Label.LabelStyle(new BitmapFont(), null));
        Label label3 = new Label("text", new Label.LabelStyle(new BitmapFont(), null));
        Label label4 = new Label("text", new Label.LabelStyle(new BitmapFont(), null));
        Label label5 = new Label("text", new Label.LabelStyle(new BitmapFont(), null));

        List list = new List(new List.ListStyle(new BitmapFont(), Color.WHITE, Color.BLACK, new TextureRegionDrawable(new Texture("dialog_option.png"))));

        TextureRegionDrawable itemOption = new TextureRegionDrawable(new Texture("item_option.png"));
        TextureRegionDrawable itemOptionSelected = new TextureRegionDrawable(new Texture("item_option_selected.png"));
        list.setItems(new Texture("badlogic.jpg"));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture("shop.png"));
        ScrollPane pane = new ScrollPane(list, new ScrollPane.ScrollPaneStyle(drawable, null, null, null, null));
        Table leftTopTable = new Table();

        leftTopTable.add(label2);
        leftTopTable.row();
        leftTopTable.add(label);
        leftTopTable.pack();
        leftTopTable.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Align.right);

        stage.setScrollFocus(pane);
        pane.setSize(100, 100);
        pane.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        table.setFillParent(true);

        table.row();
        table.add(label).right().expandX().pad(30).right();
        table.row();
        table.add(label4);
        table.add(label5);
        table.add(pane).height(100);
        table.addActor(leftTopTable);
        Gdx.input.setInputProcessor(stage);


        stage.addActor(table);

//        table.addActor(label);

        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        viewport = new ScreenViewport();
        camera = new OrthographicCamera(50, 50);
        batch = new SpriteBatch();

        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        map = tmxMapLoader.load("word_tmx/Boss.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);
        renderer.setView(camera);

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(43, 71);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
// Set its world position
        groundBodyDef.position.set(new Vector2(43, 30));

// Create a body from the definition and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

// Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
// Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
// Clean up after ourselves
        groundBox.dispose();

        camera.position.set(new Vector3(43, 71, 0));
        camera.update();
    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        debugRenderer.render(world, camera.combined);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
//        batch.begin();
//
//
//        batch.end();


    }

    public void update() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        map.dispose();
    }

}
