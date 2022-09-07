package de.marcus.javagame.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

public class UI {

    Table uiContainer;
    ProgressBar healthBar;


    public UI() {
        uiContainer = new Table();

        TextureRegion textureRegion = new TextureRegion(new Texture("hearts.png"));
        TiledDrawable hearts = new TiledDrawable(textureRegion);

        TiledDrawable dead = new TiledDrawable(new TextureRegion(new Texture("hearts_dead.png")));
        Drawable knob = getColoredDrawable(0, 32, Color.GREEN);


        healthBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(dead, knob));
        healthBar.setSize(128, 32);

        healthBar.getStyle().knobBefore = hearts;
        healthBar.setValue(4.0f);

        healthBar.setAnimateDuration(0.5f);


        healthBar.setPosition(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() - 5f), Gdx.graphics.getHeight() - (0.1f * Gdx.graphics.getHeight()));
        System.out.println(healthBar.getX());
        System.out.println(healthBar.getY());
        uiContainer.addActor(healthBar);
        uiContainer.setDebug(true);

    }

    public Table getUiContainer() {
        return uiContainer;
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }

    public static Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        return drawable;
    }
}
