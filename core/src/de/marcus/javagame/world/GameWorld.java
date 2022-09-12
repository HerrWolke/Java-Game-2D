package de.marcus.javagame.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import lombok.Getter;

@Getter
public class GameWorld {
    public static final float UNIT_SCALE = 1f / 96f;
    TiledMap tiledMap;
    TiledMap dungeonEingang;
    TiledMap boss;
    TiledMap dungeonRechts;
    TiledMap dungeonLinks;
    int screen =0;
    OrthogonalTiledMapRenderer renderer;

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
    public void setMap(int i){
          if(i == 0){
             if(screen != i){
                 renderer.setMap(tiledMap);
             }
          }
          else if(i == 1){
              if(screen != i){
                  renderer.setMap(dungeonLinks);
              }
          }else if(i == 2){
              if(screen != i){
                  renderer.setMap(dungeonEingang);
              }
          }else if(i == 3){
              if(screen != i){
                  renderer.setMap(boss);
              }
          }else if(i == 4){
              if(screen != i){
                  renderer.setMap(dungeonRechts);
              }
          }else if( i == 5){
              if(screen != i){
                  renderer.setMap(dungeonLinks);
              }
          }
    }
    private void generateCollisions() {

    }


    public void dispose() {
        tiledMap.dispose();

    }

    public void getTileAtCoords() {

    }

}
