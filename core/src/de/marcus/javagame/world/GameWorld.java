package de.marcus.javagame.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
    private final float TILE_SIZE;
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
        //TODO: Kommentar wieder entfernen
        renderer.setMap(tiledMap);
         getForms("nicht betretbar",tiledMap);
         getForms("Dach",tiledMap);
           getForms("Eingang",tiledMap);
        TILE_SIZE =  Float.valueOf(dungeonLinks.getProperties().get("tilewidth",Integer.class));;

    }
    public void getForms(String layer,TiledMap map) {
        MapLayer collisionObjectLayer = map.getLayers().get(layer);
        MapObjects objects = collisionObjectLayer.getObjects();
        System.out.println("objects: " + objects.getByType(PolygonMapObject.class).size);
        int ind = 0;
        for (PolygonMapObject mapobject : objects.getByType(PolygonMapObject.class)) {
            System.out.println("vor error");
            if(mapobject.getPolygon().getTransformedVertices().length > 8){
                System.out.println("error");
            }
            Polygon p = mapobject.getPolygon(); //
            PolygonShape gb = new PolygonShape();
            BodyDef bodydef = new BodyDef();
            bodydef.type = StaticBody;
            Body bod = world.createBody(bodydef);










            float[] vertices = p.getTransformedVertices();

            float[] worldVertices = new float[vertices.length];
            System.out.println("x: " + p.getX() + " y: " + p.getY());
            for (int i = 0; i < vertices.length; ++i) {
                System.out.println(vertices[i]);
                worldVertices[i] = vertices[i] / 128;
            }
            System.out.println("finished " + layer);
            if(vertices.length <=8) {


                gb.set(worldVertices);

                bod.createFixture(gb, 100.0f);
            }else{
                System.out.println("-----------------------------------------Error------------------------------------------");
                for (int i = 0; i < vertices.length; ++i) {
                    System.out.println(vertices[i]);

                }
                System.out.println("-----------------------------------------------------------------------------------------");
            }
            System.out.println(ind);
            ind++;

        }



        for (RectangleMapObject mapobject : objects.getByType(RectangleMapObject.class)) {
            Rectangle p = mapobject.getRectangle(); //
            PolygonShape gb = new PolygonShape();
            BodyDef bodydef = new BodyDef();
            bodydef.type = StaticBody;

           // bodydef.position.set(p.width*0.5F/ TILE_SIZE,p.height*0.5F/ TILE_SIZE);


            System.out.println("Box information: " + p.width/2 + " , " + p.height/2);

            gb.setAsBox(p.width*0.5F/ TILE_SIZE,p.height*0.5F/ TILE_SIZE);
            Body bod = world.createBody(bodydef);
            bod.createFixture(gb, 100.0f);
            System.out.println("Pos x: " + (bod.getPosition().x - p.width/2) + " ,y: " + (bod.getPosition().y - p.height/2));

            System.out.println("-----------------");
            bod.setTransform(getTransformedCenterForRectangle(p),0);
        }
    }
    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void setMap(int i) {
        if (i == 0) {
            if (screen != i) {
                renderer.setMap(tiledMap);


            }
        } else if (i == 1) {
            if (screen != i) {
                renderer.setMap(dungeonLinks);
                getForms("Wand",dungeonLinks);
                getForms("Eingang",dungeonLinks);
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

    public  Vector2 getTransformedCenterForRectangle(Rectangle rectangle) {
        Vector2 center = new Vector2();
        rectangle.getCenter(center);
        return center.scl(1 / TILE_SIZE);
    }
//91bfc08
    public void dispose() {
        tiledMap.dispose();
        dungeonEingang.dispose();
        dungeonLinks.dispose();
        dungeonRechts.dispose();
        boss.dispose();
    }

    public void getTileAtCoords() {

    }

    public void setCollisionInMap(String name) {

    }

}
