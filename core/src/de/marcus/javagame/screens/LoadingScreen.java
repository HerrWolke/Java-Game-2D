package de.marcus.javagame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import de.marcus.javagame.managers.GameScreenManager;



public class LoadingScreen extends Game {
    SpriteBatch batch;
    GameScreenManager g;
    Animation<TextureRegion> loadingAnimation;
    TextureAtlas atlas;
    boolean loaded;

    @Override
    public void create () {
        batch = new SpriteBatch();
        g = new GameScreenManager(this);
        atlas = new TextureAtlas("running.atlas");
        loadingAnimation =
                new Animation<TextureRegion>(0.033f, atlas.findRegions("running"), Animation.PlayMode.LOOP);
        loaded = true;

    }

    @Override
    public void render () {

        TextureRegion frame = loadingAnimation.getKeyFrame(0.5f, true);
        //ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(frame, 0, 0, 500,500);

        batch.end();

        //nur ein test
        if(loaded){

            Gdx.gl.glClearColor( 0, 0, 0, 1 );
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
            g.setScreen(GameScreenManager.SCREENS.START_MENU);

        }

    }

    @Override
    public void dispose () {
        batch.dispose();

    }
}
