package de.marcus.javagame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public TextureAtlas textureAtlas;
    public AssetManager manager;


    public void load() {

        textureAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        manager = new AssetManager();

        manager.load(new AssetDescriptor<>("world/grass.png", Texture.class));

    }

    public void dispose() {
        textureAtlas.dispose();
        manager.dispose();
    }
}