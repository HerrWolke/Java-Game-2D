package de.marcus.javagame.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import lombok.Getter;


@Getter
public class GameWorld {
    public static final float UNIT_SCALE = 1f / 96f;
    private static final float TILE_SIZE = 128;
    TiledMap tiledMap;
    TiledMap dungeonEingang;
    TiledMap boss;
    TiledMap dungeonRechts;
    TiledMap dungeonLinks;
    int screen = 0;
    OrthogonalTiledMapRenderer renderer;

    public boolean load2 = false;

    public GameWorld(OrthographicCamera camera) {

        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("word_tmx/Tilemap.tmx");
        dungeonEingang = tmxMapLoader.load("word_tmx/EingangDungeon.tmx");
        boss = tmxMapLoader.load("word_tmx/Boss.tmx");
        dungeonRechts = tmxMapLoader.load("word_tmx/rechtsDungeon.tmx");
        dungeonLinks = tmxMapLoader.load("word_tmx/linksDungeon.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, UNIT_SCALE);
        renderer.setView(camera);
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

}
