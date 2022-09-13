package de.marcus.javagame.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.windows.DialogWindow;
import de.marcus.javagame.graphics.ui.windows.InventoryWindow;
import de.marcus.javagame.managers.SoundManager;
import de.marcus.javagame.misc.Util;
import lombok.Getter;

@Getter
public class UI {

    Table uiContainer;
    ProgressBar healthBar;
    ProgressBar armorBar;
    Label position;
    Label fps;

    Image image;

    Label notification;
    Group notificationSystem;

    InventoryWindow inventory;

    Stage stage;

    long displayTime;

    DialogWindow dialog;




    public UI(Stage stage, Player player) {
        this.stage = stage;
        uiContainer = new Table();
        uiContainer.setFillParent(true);
        inventory = new InventoryWindow(player.getInventory(),stage);
        dialog = new DialogWindow(stage,this);
        Table testTable = new Table();
        player.setInventoryWindow(inventory);
        stage.addActor(dialog);
        stage.addActor(inventory);
        stage.addActor(uiContainer);
        notificationSystem = new Group();



        TiledDrawable heartDrawable = new TiledDrawable(new TextureRegion(new Texture("hearts.png")));
        TiledDrawable dead = new TiledDrawable(new TextureRegion(new Texture("hearts_dead.png")));

        image = new Image(new Texture("hotbar_slot.png"));

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

        float aspectRatio = Util.getAspectRatio(stage);
        notification.setSize(notification.getWidth()  * aspectRatio * 2f,notification.getHeight() * aspectRatio * 4f);
        notification.setPosition(stage.getCamera().viewportWidth - notification.getWidth(),stage.getCamera().viewportHeight - notification.getHeight());
        notificationSystem.setVisible(true);

        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);
        uiContainer.pad(screenHeight * 0.001f,screenWidth*0.002f,screenHeight*0.001f,screenWidth*0.002f);


        //needs to be 128 otherwise the texture adds another half heart idk
        uiContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top().expandX();
        uiContainer.add(image).size(128,128).top().right();
        uiContainer.row();
        uiContainer.add(armorBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top();
        uiContainer.row();
        uiContainer.add(position).padBottom(healthBar.getHeight() * 0.25f).left();
        uiContainer.row();
        uiContainer.add(fps).left();
        notificationSystem.addActor(notification);

        uiContainer.setDebug(true);
        uiContainer.addActor(notificationSystem);





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
        inventory.resetItemOptionGroup();

    }

    public boolean isPlayerAllowedToMove() {
        return !dialog.isVisible();
    }

    public void displayNotification(long displayTime,String text) {
        notification.setText(text);
        notification.setVisible(true);
        SoundManager.playSoundEffect(SoundManager.SoundEffects.NOTIFICATION,false);
        this.displayTime = displayTime;
    }
}
