package de.marcus.javagame.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import de.marcus.javagame.datahandling.data.collisions.BodyData;
import de.marcus.javagame.entities.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

//Eingang, npc , gegenstände auf map, mob
@Getter
public class GameWorld {
    public static final float UNIT_SCALE = 1f / 96f;
    private final float TILE_SIZE;
    public LinkedHashMap<Body,Eingang> eingang;
    public boolean destroy = false;
    World world;
    TiledMap innenRaum1,innenRaum2,innenRaum3,tiledMap,dungeonEingang,boss,dungeonRechts,dungeonLinks,mine1,mine2;
    int screen = 0;
    OrthogonalTiledMapRenderer renderer;

    public boolean load2 = false;

    public GameWorld(OrthographicCamera camera) {

        world = new World(new Vector2(0, 0), true);
        eingang = new LinkedHashMap<>();
        AssetManager assetManager = new AssetManager();
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("word_tmx/Tilemap.tmx");
        dungeonEingang = tmxMapLoader.load("word_tmx/EingangDungeon.tmx");
        boss = tmxMapLoader.load("word_tmx/Boss.tmx");
        dungeonRechts = tmxMapLoader.load("word_tmx/Boss.tmx");
        dungeonLinks = tmxMapLoader.load("word_tmx/linksDungeon.tmx");
        innenRaum1 =tmxMapLoader.load("word_tmx/Innenraum1.tmx");;
        innenRaum2 =tmxMapLoader.load("word_tmx/Innenraum2.tmx");;
        innenRaum3 =tmxMapLoader.load("word_tmx/Innenraum3.tmx");;
        mine1=tmxMapLoader.load("word_tmx/mine1.tmx");;
        mine2=tmxMapLoader.load("word_tmx/mine2.tmx");;

        //mine = tmxMapLoader.load("word_tmx/Innenraum1.tmx");
        renderer = new OrthogonalTiledMapRenderer(dungeonRechts, UNIT_SCALE);
        renderer.setView(camera);
        //TODO: Kommentar wieder entfernen
       // renderer.setMap(tiledMap);
        /* getForms("nicht betretbar",tiledMap,0);
         getForms("Dach",tiledMap,0);
           getForms("Eingang",tiledMap,0);*/
        TILE_SIZE =  Float.valueOf(dungeonLinks.getProperties().get("tilewidth",Integer.class));;

    }

    public void getForms(String layer,TiledMap map,int mapt) {
        MapLayer collisionObjectLayer = map.getLayers().get(layer);
        MapObjects objects = collisionObjectLayer.getObjects();
        for(MapObject mapobject : objects){
            Shape shape;
            Vector2 v;
            if(mapobject instanceof PolylineMapObject){
                ArrayList<Object> r = createPolyline((PolylineMapObject) mapobject);
                shape = (ChainShape) r.get(0);
                v = (Vector2) r.get(1);
            }
            else{
                continue;
            }
            Body body;
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = StaticBody;
            body = world.createBody(bodyDef);
            body.createFixture(shape,1.0f);
            body.setUserData(new BodyData(true,false));
            if(layer.equals("Eingang")){
                    eingang.put(body,new Eingang(v.x,v.y,mapt));
            }
            shape.dispose();
        }

    }
    private void markDeleteableForDestruction() {
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            BodyData userData = (BodyData) body.getUserData();
            if(userData.isCanBeDestroyed()) {
                userData.setMarkedForDestruction(true);
            }
        }
    }

    private ArrayList<Object> createPolyline(PolylineMapObject polyline) {
        float[] verticies = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[verticies.length/2];
        for(int i =0; i <worldVertices.length; i++){
            worldVertices[i] = new Vector2(verticies[i*2] / 96, verticies[i*2+1] / 96);
        }
        ArrayList<Object> l = new ArrayList<>();
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        l.add(cs);
        l.add(worldVertices[0]);
        return l;
    }

    private BodyDef getBodyDef(float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        return bodyDef;
    }
    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void setMap(int i,Player p) {
        if (i == 0) {
            if (screen != i) {
                renderer.setMap(tiledMap);


            }
        } else if (i == 1) {
            if (screen != i) {
                markDeleteableForDestruction();
                renderer.setMap(dungeonLinks);
                getForms("Wand", dungeonLinks, 1);
                getForms("Eingang", dungeonLinks, 1);

            }

        } else if (i == 2) {
            if (screen != i) {

                renderer.setMap(dungeonEingang);

                getForms("Wände", dungeonEingang, 2);
                getForms("Dach", dungeonEingang, 2);
                getForms("Eingang", dungeonEingang, 2);

            }
        } else if (i == 3) {
            if (screen != i) {
                renderer.setMap(boss);
                getForms("Wand",boss,3);
                getForms("Dach",boss,3);
                getForms("Kisten",boss,3);
            }
        } else if (i == 4) {
            if (screen != i) {
                renderer.setMap(dungeonRechts);
                getForms("nicht betretbar",dungeonRechts,4);
                getForms("Eingang",dungeonRechts,4);
                getForms("Kisten",dungeonRechts,4);

            }
        } else if (i == 5) {
            if (screen != i) {
                renderer.setMap(mine1);
                getForms("nicht betretbar",mine1,5);
                getForms("Eingang",mine1,5);
            }
        } else if (i == 6) {
            if (screen != i) {
                renderer.setMap(mine2);
                getForms("nicht betretbar",mine2,6);
                getForms("Eingang",mine2,6);
            }
        }else if (i == 7) {
            if (screen != i) {
                renderer.setMap(innenRaum1);
                getForms("nicht betretbar",innenRaum1,7);
                getForms("Eingang",innenRaum1,7);
            }
        }else if (i == 8) {
            if (screen != i) {
                renderer.setMap(innenRaum2);
                getForms("nicht betretbar",innenRaum2,8);
                getForms("Eingang",innenRaum2,8);
            }
        }else if (i == 9) {
            if (screen != i) {
                renderer.setMap(innenRaum3);
                getForms("nicht betretbar",innenRaum3,9);
                getForms("Eingang",innenRaum3,9);
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
