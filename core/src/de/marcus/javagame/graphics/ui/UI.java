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
import lombok.Getter;

@Getter
public class UI {

    Table uiContainer;
    ProgressBar healthBar;
    ProgressBar armorBar;

    public UI() {
        uiContainer = new Table();


        TiledDrawable heartDrawable = new TiledDrawable(new TextureRegion(new Texture("hearts.png")));
        TiledDrawable dead = new TiledDrawable(new TextureRegion(new Texture("hearts_dead.png")));

        TiledDrawable chestplateDrawable = new TiledDrawable(new TextureRegion(new Texture("items/chestplate.png")));
        TiledDrawable chestplateDead = new TiledDrawable(new TextureRegion(new Texture("items/chestplate_dead.png")));
        chestplateDead.tint(Color.GREEN);

        Drawable knob = getColoredDrawable(0, 32, Color.GREEN);
        healthBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(dead, knob));
        healthBar.getStyle().knobBefore = heartDrawable;
        healthBar.setValue(4.0f);
        healthBar.setAnimateDuration(0.5f);

        armorBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(chestplateDead, knob));
        armorBar.getStyle().knobBefore = chestplateDrawable;
        armorBar.setValue(4.0f);
        armorBar.setAnimateDuration(0.5f);


        System.out.println(healthBar.getX());
        System.out.println(healthBar.getY());
        uiContainer.pad(Gdx.graphics.getWidth() * 0.01f);
        uiContainer.setFillParent(true);
        uiContainer.setDebug(false);
        uiContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f);
        uiContainer.row();
        uiContainer.add(armorBar).width(128);
        uiContainer.left().top();


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
