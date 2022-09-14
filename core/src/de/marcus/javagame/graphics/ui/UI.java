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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.windows.DialogWindow;
import de.marcus.javagame.graphics.ui.windows.InventoryWindow;
import de.marcus.javagame.graphics.ui.windows.ShopWindow;
import de.marcus.javagame.managers.SoundManager;
import de.marcus.javagame.misc.Util;
import lombok.Getter;

@Getter
public class UI {


    Table uiContainer;
    Group notificationSystem;

    /**
     * Parent of the UI
     */
    Stage stage;






    /**
     * Extra Windows (Added directly to stage to not change UI positioning;
     */
    InventoryWindow inventoryWindow;
    DialogWindow dialogWindow;
    ShopWindow shopWindow;


    /**
     * Whole screen UI
     */
    Table mainUIContainer;


    /**
     * Top left UI (Hearts, Armor)
     */
    Table playerInfoUIContainer;
    ProgressBar healthBar;
    ProgressBar armorBar;


    /**
     * FPS and Coordinates
     */
    Table debugUI;
    Label playerPositionLabel;
    Label fpsLabel;


    /**
     * This is the top right UI Container
     */
    Table notificationUI;
    Label notification;

    //Time left to display notification
    long notificationDisplayTimeLeft;


    /**
     * Position this container at the bottom of the screen (fill entire bottom width)
     */
    Table bottomUIContainer;
    Image hotbarSlot;
    Image hotbarItem;
    //To layer the hotbar Item above the hotbar slot (show it "inside" of slot)
    Group hotbarGroup;

    public UI(Stage stage, Player player) {
        this.stage = stage;
        uiContainer = new Table();
        uiContainer.setFillParent(true);
        inventoryWindow = new InventoryWindow(player.getInventory(), stage);
        dialogWindow = new DialogWindow(stage, this);

        player.setInventoryWindow(inventoryWindow);
        stage.addActor(dialogWindow);
        stage.addActor(inventoryWindow);
        stage.addActor(uiContainer);
        notificationSystem = new Group();



        TiledDrawable heartDrawable = new TiledDrawable(new TextureRegion(new Texture("hearts.png")));
        TiledDrawable dead = new TiledDrawable(new TextureRegion(new Texture("hearts_dead.png")));

        hotbarSlot = new Image(new Texture("hotbar_slot.png"));

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

        playerPositionLabel = new Label("x: 0 \n y: 0", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        fpsLabel = new Label("FPS: 0", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans_bold_semi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.padLeft = 40;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        notification = new Label("Default Text", new Label.LabelStyle(font12, Color.WHITE));
        notification.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("notification.png")));
        notification.setAlignment(Align.center);
        notification.setVisible(false);

        float aspectRatio = Util.getAspectRatio(stage);
        notification.setSize(notification.getWidth() * aspectRatio * 2f, notification.getHeight() * aspectRatio * 4f);
        notification.setPosition(stage.getCamera().viewportWidth - notification.getWidth(), stage.getCamera().viewportHeight - notification.getHeight());
        notificationSystem.setVisible(true);

        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);
        uiContainer.pad(screenHeight * 0.001f, screenWidth * 0.002f, screenHeight * 0.001f, screenWidth * 0.002f);

        //needs to be 128 otherwise the texture adds another half heart idk
        uiContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top().expandX();
        uiContainer.add(hotbarSlot).size(128, 128).top().right();
        uiContainer.row();
        uiContainer.add(armorBar).width(128).padBottom(healthBar.getHeight() * 0.25f).left().top();
        uiContainer.row();
        uiContainer.add(playerPositionLabel).padBottom(healthBar.getHeight() * 0.25f).left();
        uiContainer.row();
        uiContainer.add(fpsLabel).left();
        notificationSystem.addActor(notification);

        uiContainer.setDebug(true);
        uiContainer.addActor(notificationSystem);


        uiContainer.left().top();


    }



    public UI() {
        initialiseUIElements();

        //MAIN TABLE
        mainUIContainer = new Table();
        mainUIContainer.setFillParent(true);
        stage.addActor(mainUIContainer);
        stage.setDebugAll(true);
        //END MAIN TABLE

        //TABLES
        playerInfoUIContainer = new Table();
        debugUI = new Table();
        bottomUIContainer = new Table();
        notificationUI = new Table();
        //END TABLES



        //TOP LEFT PLAYER UI INIT
        playerInfoUIContainer.padLeft(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        playerInfoUIContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f);
        playerInfoUIContainer.row();
        playerInfoUIContainer.add(armorBar).width(128).padBottom(healthBar.getHeight() * 0.25f);
        playerInfoUIContainer.top().left();
        //TOP LEFT PLAYER UI INIT END

        //TOP RIGHT NOTIFICATION UI INIT
        notificationUI.padRight(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        notificationUI.add(notification);
        notificationUI.top().right();
        //TOP RIGHT NOTIFICATION UI INIT END


        //MIDDLE DEBUG UI INIT
        debugUI.padLeft(Value.percentWidth(0.01f))
                .padRight(Value.percentWidth(0.01f));

        debugUI.add(fpsLabel).padBottom(fpsLabel.getHeight() * 0.25f);
        debugUI.row();
        debugUI.add(playerPositionLabel);
        debugUI.left();

        //MIDDLE DEBUG UI INIT END

        //BOTTOM UI INIT
        bottomUIContainer.padLeft(Value.percentWidth(0.01f))
                .padRight(Value.percentWidth(0.01f))
                .padBottom(Value.percentHeight(0.01f));

        bottomUIContainer.add(hotbarSlot);
        bottomUIContainer.center().bottom();

        //BOTTOM UI INIT END


        mainUIContainer.add(playerInfoUIContainer).grow();
        mainUIContainer.add(notificationUI).grow();
        mainUIContainer.row();
        mainUIContainer.add(debugUI).colspan(2).grow();
        mainUIContainer.row();
        mainUIContainer.add(bottomUIContainer).colspan(2).grow();
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
        playerPositionLabel.setText(String.format("X: %s | Y: %s", Math.round(x * 100.0) / 100.0, Math.round(y * 100.0) / 100.0));
        fpsLabel.setText(String.format("FPS: %s", Gdx.graphics.getFramesPerSecond()));


        if (notification.isVisible()) {
            notificationDisplayTimeLeft -= (long) (Gdx.graphics.getDeltaTime() * 1000);
            if (notificationDisplayTimeLeft <= 0) {
                notification.setVisible(false);
            }
        }

    }

    public void changeInventoryShowState() {
        inventoryWindow.setVisible(!inventoryWindow.isVisible());
        inventoryWindow.resetItemOptionGroup();

    }

    public boolean isPlayerAllowedToMove() {
        return !dialogWindow.isVisible();
    }

    public void displayNotification(long displayTime, String text) {
        notification.setText(text);
        notification.setVisible(true);
        SoundManager.playSoundEffect(SoundManager.SoundEffects.NOTIFICATION, false);
        this.notificationDisplayTimeLeft = displayTime;
    }

    public void initialiseUIElements() {
        //INITIALISE ELEMENTS FOR UI
        BitmapFont font = Util.getFontForScreenSize(stage,12);
        Label.LabelStyle defaultUILabelStyle = new Label.LabelStyle(font, Color.WHITE);
        //END INITIALISE

        //INITIALISE ELEMENTS FOR TOP LEFT UI
        ProgressBar.ProgressBarStyle defaultUIProgressBarStyle = new ProgressBar.ProgressBarStyle(null,null);
        defaultUIProgressBarStyle.knob = getColoredDrawable(0, 32, Color.GREEN);

        TiledDrawable heartDrawable = Util.generateTiledDrawable(new Texture("hearts.png"));
        TiledDrawable heartBackgroundDrawable = Util.generateTiledDrawable(new Texture("hearts_dead.png"));

        TiledDrawable chestplateDrawable = Util.generateTiledDrawable(new Texture("chestplate.png"));
        TiledDrawable chestplateBackgroundDrawable = Util.generateTiledDrawable(new Texture("chestplate_dead.png"));

        healthBar = new ProgressBar(0.0f,4.0f,1.0f,false,defaultUIProgressBarStyle);
        healthBar.getStyle().knobBefore = heartDrawable;
        healthBar.getStyle().knobAfter = heartBackgroundDrawable;
        armorBar = new ProgressBar(0.0f,4.0f,1.0f,false,defaultUIProgressBarStyle);
        armorBar.getStyle().knobBefore = chestplateDrawable;
        armorBar.getStyle().knobAfter = chestplateBackgroundDrawable;

        //END INITIALISE

        //INITIALISE ELEMENTS FOR TOP RIGHT UI

        //TODO: Refactor Notification UI
        notification = new Label("Default Text", new Label.LabelStyle(font, Color.WHITE));
        notification.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("notification.png")));
        notification.setAlignment(Align.center);
        notification.setVisible(false);

        float aspectRatio = Util.getAspectRatio(stage);
        notification.setSize(notification.getWidth() * aspectRatio * 2f, notification.getHeight() * aspectRatio * 4f);

        //END INITIALISE

        //INITIALISE ELEMENTS FOR DEBUG UI
        fpsLabel = new Label("FPS: 0",defaultUILabelStyle);
        playerPositionLabel = new Label("X: 0, Y: 0",defaultUILabelStyle);
        //END INITIALISE

        //INITIALISE ELEMENTS FOR BOTTOM UI

        //Currently no elements in this category. Maybe we will add some in the future

        //END INITIALISE
    }
}
