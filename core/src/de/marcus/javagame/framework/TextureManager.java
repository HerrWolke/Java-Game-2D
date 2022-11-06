package de.marcus.javagame.framework;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TextureManager {
    public static TextureAtlas images = new TextureAtlas("packed/sprite.atlas");
    public static TextureAtlas characterAtlas = new TextureAtlas("packed/character.atlas");

    public static TextureRegion getTexture(String name) {
        TextureAtlas.AtlasRegion region = images.findRegion(name);
        return region;

    }

    public static Animation<TextureRegion> getCharacterAnimation(String name, boolean looped, float duration) {
        return new Animation<TextureRegion>(duration, getCharacterRegion(name), looped ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
    }


    public static Array<TextureAtlas.AtlasRegion> getRegion(String name) {
        return images.findRegions(name);
    }

    public static Array<TextureAtlas.AtlasRegion> getCharacterRegion(String name) {
        return characterAtlas.findRegions(name);
    }

    public static Animation<TextureRegion> getAnimation(String name, boolean looped, float duration) {
        return new Animation<TextureRegion>(duration, getRegion(name), looped ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
    }

    public static TextureRegion getFinishedAnimation(boolean looped, float duration, String name) {
        return getAnimation(name, looped, duration).getKeyFrame(duration, looped);
    }
}
