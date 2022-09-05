package de.marcus.javagame.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sun.org.apache.xpath.internal.operations.Or;

public class GameWorld {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;

    public GameWorld() {
        tiledMap = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap,10);
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
