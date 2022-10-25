package de.marcus.javagame.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Align;
import de.marcus.javagame.datahandling.data.inventory.InventoryItem;
import de.marcus.javagame.datahandling.data.inventory.InventorySlot;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.windows.DialogWindow;
import de.marcus.javagame.graphics.ui.windows.GenericGameWindow;
import de.marcus.javagame.graphics.ui.windows.InventoryWindow;
import de.marcus.javagame.graphics.ui.windows.ShopWindow;
import de.marcus.javagame.managers.SoundManager;
import de.marcus.javagame.managers.TextureManager;
import de.marcus.javagame.misc.Util;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//TODO: Add money counter to top left UI!
@Getter
public class UI {
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

    List<GenericGameWindow> extraWindowList;


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

    Label moneyDisplay;


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
    Image hotbar;

    List<InventorySlot> slotListOld = new ArrayList<>();
    //To layer the hotbar Item above the hotbar slot (show it "inside" of slot)

    Player player;

    public UI(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
        extraWindowList = new ArrayList<>();

        //MAIN TABLE
        mainUIContainer = new Table();
        mainUIContainer.setFillParent(true);
        stage.addActor(mainUIContainer);
        //END MAIN TABLE
        initialiseUIElements();

        //INIT FOR WINDOWS AND ADDING TO PARENT UI
        inventoryWindow = new InventoryWindow(player.getInventory(), stage);
        dialogWindow = new DialogWindow(stage, this);
        shopWindow = new ShopWindow(this, player);
        extraWindowList.add(inventoryWindow);
        extraWindowList.add(dialogWindow);
        extraWindowList.add(shopWindow);


        player.setInventoryWindow(inventoryWindow);


        //TABLES
        playerInfoUIContainer = new Table();
        debugUI = new Table();
        bottomUIContainer = new Table();
        notificationUI = new Table();
        //END TABLES

        stage.addActor(dialogWindow);
        stage.addActor(inventoryWindow);
        stage.addActor(mainUIContainer);
        stage.addActor(shopWindow);


        //TOP LEFT PLAYER UI INIT
        playerInfoUIContainer.padLeft(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        playerInfoUIContainer.add(healthBar).width(128).padBottom(healthBar.getHeight() * 0.25f);
        playerInfoUIContainer.row();
        playerInfoUIContainer.add(armorBar).width(128).padBottom(healthBar.getHeight() * 0.25f);
        playerInfoUIContainer.top().left();
        playerInfoUIContainer.row();
        playerInfoUIContainer.add(moneyDisplay).padBottom(healthBar.getHeight() * 0.25f);
        //TOP LEFT PLAYER UI INIT END

        //TOP RIGHT NOTIFICATION UI INIT
        notificationUI.padRight(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        notificationUI.add(notification).width(Value.percentWidth(0.25f, mainUIContainer)).height((Value.percentHeight(0.25f * Util.getReversedAspectRatio(stage), mainUIContainer)));
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

        float width = (Util.getScreenWidth(stage) * 0.48f);
        float height = (Util.getScreenHeight(stage) * 0.09f);

        TextureRegionDrawable placeholder = new TextureRegionDrawable(TextureManager.getTexture("placeholder"));
        TextureRegionDrawable texture = new TextureRegionDrawable(new Texture("items/heal_potion.png"));
        TextureRegionDrawable hotbar = new TextureRegionDrawable(new Texture("hotbar_slot.png"));
        hotbar.setMinWidth(width / 10);
        hotbar.setMinHeight(height);
        placeholder.setMinWidth((width / 10) * 0.8f);
        placeholder.setMinHeight((height) * 0.8f);
        texture.setMinWidth((width / 10) * 0.8f);
        texture.setMinHeight((height) * 0.8f);

        BitmapFont font = Util.getFontForScreenSize(stage, 12);
        for (int i = 1; i < 11; i++) {
            Image image;
            if (player.getInventory().getHotbar().get((i - 1)).getItem() == null)
                image = new Image(placeholder);
            else {
                TextureRegionDrawable drawable = new TextureRegionDrawable(player.getInventory().getHotbar().get((i - 1)).getTexture());
                drawable.setMinWidth((width / 10) * 0.8f);
                drawable.setMinHeight((height) * 0.8f);
                image = new Image(drawable);
            }
            image.setName("Hitlers Child" + i);
            Image background = new Image(hotbar);
            image.setPosition((hotbar.getMinWidth() - texture.getMinWidth()) / 2.0f, (hotbar.getMinHeight() - texture.getMinHeight()) / 2.0f);

            Stack stack = new Stack();
            stack.addActor(background);

            Group group = new Group();
            group.addActor(image);
            stack.addActor(group);
            bottomUIContainer.add(stack);
        }
        slotListOld = new ArrayList<>(player.getInventory().getHotbar());

        System.out.println(((Group) (((Stack) bottomUIContainer.getChild(0)).getChild(1))).getChild(0).getName());
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
        moneyDisplay.setText(String.format("%s Lana", player.getInventory().getMoney()));
        for (int i = 0; i < player.getInventory().getHotbar().size(); i++) {
            InventoryItem item = player.getInventory().getHotbar().get(i).getItem();
            if (!Objects.equals(item, slotListOld.get(i).getItem())) {
                /*
                First: Get the Stack (use i to get correct one)
                Second: Get The Group, within the stack (always index 1)
                Third: Get the placeholder image (Has to be on top; So index 0)
                 */
                ((Image)((Group) (((Stack) bottomUIContainer.getChild(i)).getChild(1))).getChild(0)).setDrawable(new TextureRegionDrawable(player.getInventory().getHotbar().get(i).getTexture()));
            }
        }
        slotListOld = new ArrayList<>(player.getInventory().getHotbar());


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
        return !dialogWindow.isVisible() && !shopWindow.isVisible();
    }

    public void displayNotification(long displayTime, String text) {
        notification.setText(text);
        notification.setVisible(true);
        SoundManager.playSoundEffect(SoundManager.SoundEffects.NOTIFICATION, false);
        this.notificationDisplayTimeLeft = displayTime;
    }

    public void handleUIInput(int keycode) {
        if (UIControlKeys.INVENTORY_OPEN.contains(keycode)) {
            if (isPlayerAllowedToMove()) {
                changeInventoryShowState();
            }
        }

        if (UIControlKeys.CLOSE_MENU.contains(keycode)) {
            closeAllMenus();
        }

        if(inventoryWindow.isVisible()) {
            inventoryWindow.handleInput(keycode, this);
        }

        if(dialogWindow.isVisible()){
            dialogWindow.handleInput(keycode);
        }
    }

    private void closeAllMenus() {
        inventoryWindow.setVisible(false);
        dialogWindow.setVisible(false);
        shopWindow.setVisible(false);
        Gdx.input.setCursorCatched(true);
    }

    public void initialiseUIElements() {
        //INITIALISE ELEMENTS FOR UI
        BitmapFont font = Util.getFontForScreenSize(stage, 12);
        BitmapFont notificationFont = Util.getFontForScreenSize(stage, 20, 50);
        Label.LabelStyle defaultUILabelStyle = new Label.LabelStyle(font, Color.WHITE);
        //END INITIALISE

        //INITIALISE ELEMENTS FOR TOP LEFT UI

        TiledDrawable heartDrawable = Util.generateTiledDrawable(TextureManager.getTexture("hearts"));
        TiledDrawable heartBackgroundDrawable = Util.generateTiledDrawable(TextureManager.getTexture("hearts_dead"));


        TiledDrawable chestplateDrawable = Util.generateTiledDrawable(TextureManager.getTexture("chestplate"));
        TiledDrawable chestplateBackgroundDrawable = Util.generateTiledDrawable(TextureManager.getTexture("chestplate_dead"));

        moneyDisplay = new Label("Lana", defaultUILabelStyle);

        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(heartBackgroundDrawable, null);
        progressBarStyle.knobBefore = heartDrawable;
        healthBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, progressBarStyle);
        healthBar.setAnimateDuration(0.25f);
        healthBar.setValue(4.0f);
        armorBar = new ProgressBar(0.0f, 4.0f, 1.0f, false, new ProgressBar.ProgressBarStyle(chestplateBackgroundDrawable, null));
        armorBar.getStyle().knobBefore = chestplateDrawable;
        armorBar.setAnimateDuration(0.25f);
        armorBar.setValue(0.0f);

        //END INITIALISE


        //INITIALISE ELEMENTS FOR DEBUG UI
        fpsLabel = new Label("FPS: 0", defaultUILabelStyle);
        playerPositionLabel = new Label("X: 0, Y: 0", defaultUILabelStyle);
        //END INITIALISE

        //INITIALISE ELEMENTS FOR BOTTOM UI

        hotbar = new Image(new Texture("hotbar_complete.png"));


        //END INITIALISE

        //INITIALISE ELEMENTS FOR TOP RIGHT UI

        //TODO: Refactor Notification UI
        notification = new Label("Default Text", new Label.LabelStyle(notificationFont, Color.WHITE));

        notification.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("notification.png")));
        notification.setAlignment(Align.center);
        notification.setVisible(false);

        //END INITIALISE
    }


    @Getter
    public enum UIControlKeys {
        CLOSE_MENU(Input.Keys.BACKSPACE, Input.Keys.ESCAPE),

        INVENTORY_OPEN(Input.Keys.E),
        CHOOSE_OPTION(Input.Keys.ENTER, Input.Keys.SPACE),

        NAV_LEFT(Input.Keys.LEFT),
        NAV_RIGHT(Input.Keys.RIGHT),
        NAV_UP(Input.Keys.UP),
        NAV_DOWN(Input.Keys.DOWN),
        NAV_KEYS(NAV_LEFT, NAV_RIGHT, NAV_DOWN, NAV_UP);


        UIControlKeys(Integer... ints) {
            this.controls = Arrays.asList(ints);
        }

        UIControlKeys(UIControlKeys... key) {
            this.controls = new ArrayList<>();
            Arrays.stream(key).forEach(controlKey -> controls.addAll(controlKey.getControls()));
        }

        public boolean contains(int keycode) {
            return controls.contains(keycode);
        }

        private final List<Integer> controls;

    }
}
