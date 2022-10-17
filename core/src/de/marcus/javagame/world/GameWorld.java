package de.marcus.javagame.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import lombok.Getter;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;


@Getter
public class GameWorld {
    public static final float UNIT_SCALE = 1f / 96f;
    private static final float TILE_SIZE = 128;
    World world;
    TiledMap tiledMap;
    TiledMap dungeonEingang;
    TiledMap boss;
    TiledMap dungeonRechts;
    TiledMap dungeonLinks;
    TiledMap mine;
    int screen = 0;
    OrthogonalTiledMapRenderer renderer;

    public boolean load2 = false;

    public GameWorld(OrthographicCamera camera) {

        world = new World(new Vector2(0, 0), true);
        AssetManager assetManager = new AssetManager();
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
      tiledMap = tmxMapLoader.load("word_tmx/Tilemap.tmx");
        dungeonEingang = tmxMapLoader.load("word_tmx/EingangDungeon.tmx");
        boss = tmxMapLoader.load("word_tmx/Boss.tmx");
        dungeonRechts = tmxMapLoader.load("word_tmx/Boss.tmx");
        dungeonLinks = tmxMapLoader.load("word_tmx/linksDungeon.tmx");
        //mine = tmxMapLoader.load("word_tmx/Innenraum1.tmx");
        renderer = new OrthogonalTiledMapRenderer(dungeonRechts, UNIT_SCALE);
        renderer.setView(camera);
        renderer.setMap(tiledMap);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void setMap(int i) {
        if (i == 0) {
            if (screen != i) {
                renderer.setMap(tiledMap);
                TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Nicht Betretbar");
                MapObjects objects = collisionObjectLayer.getObjects();
                for(PolygonMapObject mapobject : objects.getByType(PolygonMapObject.class)){
                    Polygon p = mapobject.getPolygon(); //
                    PolygonShape gb = new PolygonShape();
                    BodyDef bodydef = new BodyDef();
                    bodydef.type = StaticBody;
                    Body bod = world.createBody(bodydef);
                    bodydef.position.set(new Vector2(0, 10));
                    float[] vertices = p.getVertices();

                    for (int id = 0; id < vertices.length; id += 2) {
                        vertices[id]   = (p.getX() + vertices[id])   * 0.01f;
                        vertices[id+1] = (p.getY() + vertices[id+1]) * 0.01f;
                    }

                    gb.set(vertices);

                }

            }
        } else if (i == 1) {
            if (screen != i) {
                renderer.setMap(dungeonLinks);
            }
        } else if (i == 2) {
            if (screen != i) {
                renderer.setMap(dungeonEingang);
                load2 = true;
            }

        } else if (i == 3) {
            if (screen != i) {
                renderer.setMap(boss);
            }
        } else if (i == 4) {
            if (screen != i) {
                renderer.setMap(dungeonRechts);
            }
        } else if (i == 5) {
            if (screen != i) {
                renderer.setMap(dungeonLinks);
            }
        } else if (i == 6) {
            if (screen != i) {
                renderer.setMap(mine);
            }
        }
    }

    private void generateCollisions() {

    }

    public Shape getShapeFromRectangle(Rectangle rectangle) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(rectangle.width * 0.5F / TILE_SIZE, rectangle.height * 0.5F / TILE_SIZE);
        return polygonShape;
    }

    public static Vector2 getTransformedCenterForRectangle(Rectangle rectangle) {
        Vector2 center = new Vector2();
        rectangle.getCenter(center);
        return center.scl(1 / TILE_SIZE);
    }

    public void dispose() {
        tiledMap.dispose();
        dungeonEingang.dispose();
        dungeonLinks.dispose();
        dungeonRechts.dispose();
        boss.dispose();
    }

    public void getTileAtCoords() {

    }
    public void setCollisionInMap(String name){

    }

}
