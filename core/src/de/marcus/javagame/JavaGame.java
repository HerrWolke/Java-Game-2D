package de.marcus.javagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.marcus.javagame.entities.Item;
import de.marcus.javagame.game.Assets;
import de.marcus.javagame.managers.EntityManager;

public class JavaGame extends ApplicationAdapter {
	SpriteBatch batch;
	Assets assets;
	Item sword;
	EntityManager entityManager;
	OrthographicCamera camera;
	OrthographicCamera uiCam;
	Viewport viewport;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();



		entityManager = new EntityManager();
		camera = new OrthographicCamera(50,50);
		viewport = new StretchViewport(camera.viewportWidth,camera.viewportHeight,camera);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (int i = 0; i <= camera.viewportWidth; i+=5) {
			for (int x = 0; x <= camera.viewportHeight; x+=5) {
				batch.draw(assets.manager.<Texture>get("world/grass.png"),i,x,5,5);
			}
		}

		Sprite bow = new Sprite(assets.textureAtlas.findRegion("bow"), 0, 0, 32, 32);
		bow.setSize(1,1);
		bow.draw(batch);
		font.draw(batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, camera.viewportHeight);
//		sword.render(batch);
		batch.end();


	}

	public void update() {

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
	}

	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
