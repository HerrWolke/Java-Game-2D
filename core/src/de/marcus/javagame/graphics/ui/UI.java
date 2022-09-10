package de.marcus.javagame.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.InventoryWindow;
import de.marcus.javagame.screens.InventoryScreen;
import lombok.Getter;

import javax.swing.*;

@Getter
public class UI {

    Table uiContainer;
    ProgressBar healthBar;
    ProgressBar armorBar;
    Label position;
    Label fps;

    Label notification;

    InventoryWindow inventory;

    Stage stage;

    long displayTime;




    public UI(Stage stage, Player player) {
        this.stage = stage;
        uiContainer = new Table();
        Table table = new Table();
        inventory = new InventoryWindow(player.getInventory());
        player.setInventoryWindow(inventory);


        TiledDrawable heartDrawable = new TiledDrawable(new TextureRegion(new Texture("hearts.png")));
        TiledDrawable dead = new TiledDrawable(new TextureRegion(new Texture("hearts_dead.png")));

        TiledDrawable chestplateDrawable = new TiledDrawable(new TextureRegion(new Texture("items/chestplate.png")));
        TiledDrawable chestplateDead = new TiledDrawable(new TextureRegion(new Texture("items/chestplate_dead.png")));
        chestplateDead.tint(Color.GREEN);

        //yes this weird 0 width knob is needed, otherwise the health bar bugs out ¯\_(ツ)_/¯
        Drawable knob = getColoredDrawable(0, 32, Color.GREEN);
        healthBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(dead, knob));
        healthBar.getStyle().knobBefore = heartDrawable;
        healthBar.setValue(4.0f);
        healthBar.setAnimateDuration(0.5f);

        armorBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(chestplateDead, knob));
        armorBar.getStyle().knobBefore = chestplateDrawable;
        armorBar.setValue(4.0f);
        armorBar.setAnimateDuration(0.5f);

        position = new Label("x: 0 \n y: 0",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
        fps = new Label("FPS: 0",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.padLeft = 40;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        notification = new Label("Default Text", new Label.LabelStyle(font12,Color.WHITE));
        notification.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("notification.png")));
        notification.setAlignment(Align.center);
        notification.setVisible(false);




        System.out.println(healthBar.getX());
        System.out.println(healthBar.getY());
        uiContainer.pad(Gdx.graphics.getWidth() * 0.01f);

        uiContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top();
        uiContainer.row();
        uiContainer.add(armorBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top();
        uiContainer.row();
        uiContainer.add(position).padBottom(healthBar.getHeight() * 0.25f).left();
        uiContainer.row();
        uiContainer.add(fps).left();
        uiContainer.add(table).growX().top().right();
        table.add(notification).width(Gdx.graphics.getWidth() * 0.25f).height(Gdx.graphics.getHeight() * 0.175f).top();
        uiContainer.setFillParent(true);
        uiContainer.setDebug(true);
        //needs to be 128 otherwise the texture adds another half heart idk




        uiContainer.left().top();
        stage.addActor(inventory);


    }





    public static Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        return drawable;
    }

    public void update(float x, float y) {
        position.setText(String.format("X: %s | Y: %s",Math.round(x * 100.0) / 100.0,Math.round(y * 100.0) / 100.0));
        fps.setText(String.format("FPS: %s",Gdx.graphics.getFramesPerSecond()));

        if(notification.isVisible()) {
            displayTime -= (long) (Gdx.graphics.getDeltaTime() * 1000);
            if(displayTime <= 0) {
                notification.setVisible(false);
            }
        }

    }

    public void changeInventoryShowState() {
        inventory.setVisible(!inventory.isVisible());


    }

    public void displayNotification(long displayTime,String text) {
        notification.setText(text);
        notification.setVisible(true);
        this.displayTime = displayTime;
    }
}
