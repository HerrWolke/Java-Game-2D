package de.marcus.javagame.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sun.org.apache.xpath.internal.operations.Or;

public class GameWorld {
    public static final float UNIT_SCALE = 1f / 96f;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;

    public GameWorld(OrthographicCamera camera) {
        tiledMap = new TmxMapLoader().load("word_tmx/Tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap,UNIT_SCALE);
        renderer.setView(camera);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
    }

    public void getTileAtCoords() {

    }

}
