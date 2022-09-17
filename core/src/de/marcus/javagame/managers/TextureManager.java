package de.marcus.javagame.managers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TextureManager {
    public static TextureAtlas images = new TextureAtlas("packed/sprite.atlas");    //muss geupdated werden

    public static TextureRegion getTexture(String name) {
        return images.findRegion(name);
    }

    public static Array<TextureAtlas.AtlasRegion> getRegion(String name) {
        return images.findRegions(name);
    }

    public static Animation<TextureRegion> getAnimation(String name, boolean looped, float duration) {
        return new Animation<TextureRegion>(duration, getRegion(name), looped ? Animation.PlayMode.LOOP : Animation.PlayMode.NORMAL);
    }

    public static TextureRegion getFinishedAnimation(boolean looped, float duration, String name) {
        return getAnimation(name, looped, duration).getKeyFrame(duration, looped);
    }
}
