package de.marcus.javagame.graphics.ui.windows;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.elements.ScrollPaneShop;
import de.marcus.javagame.managers.TextureManager;
import de.marcus.javagame.misc.Util;

//TODO: Add a way to close this window without buying anything
public class ShopWindow extends GenericGameWindow {
    ScrollPaneShop shop;
    Stage stage;
    Player player;

    Label priceLabel;
    Label itemInformationLabel;


    Shops currentShopType;

    public ShopWindow(Stage stage, Player player) {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new TextureRegion(TextureManager.getTexture("shop_background")))));
        this.stage = stage;
        this.player = player;


        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);

        setPosition(screenWidth / 4.0f, screenHeight / 4.0f);
        setSize(screenWidth / 2.0f, screenHeight / 2.0f);
        Table priceTable = new Table();
        Table shopCenterTable = new Table();
        Table shopItemInformation = new Table();

        priceLabel = new Label("0 Lana", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        itemInformationLabel = new Label("Item Info", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //TODO: Add page label!

        shop = new ScrollPaneShop(null, new ScrollPane.ScrollPaneStyle(null, null, null, null, null));
        shop.setSmoothScrolling(true);
        shop.setScrollingDisabled(false, true);

        priceTable.add(priceLabel).top().center().padBottom(priceLabel.getHeight() / 2);
        priceTable.top().center();

        this.add(priceTable).growX().padTop(Value.percentHeight(0.11f, this));
        this.row();
        shopCenterTable.bottom();
        shopCenterTable.add(shop).size(Value.percentWidth(1, this), Value.percentHeight(1.0f)).padTop(Value.percentHeight(0.25f));
        this.add(shopCenterTable);
        this.row();
        shopItemInformation.add(itemInformationLabel).padBottom(Value.percentHeight(5.0f));
        this.add(shopItemInformation).grow().top();


        this.setModal(false);
        this.setVisible(false);
        this.setMovable(false);


        stage.setScrollFocus(shop);
    }

    public void handleInput(int keycode) {

    }


    public void buy(String item) {


        if (player.canAfford(item)) {

            this.addActor(generateConfirmationDialog(item));

        }
    }

    /**
     * Generates the confirmation dialog for the buy method.
     * <p>
     * This uses the {@link Dialog} from LibGDX. The dialog has a Button to confirm, a button to close it and a text display telling how much the item will cost
     * <br>
     * It features a button click listener on the confirm button which will close the shop on press (keep? maybe not) TODO
     *
     * @return The dialog
     */
    private Dialog generateConfirmationDialog(String item) {
        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);
        Shops.ShopItems itemToBuy = Shops.ShopItems.valueOf(item);

        Dialog dialog = new Dialog("", new WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(TextureManager.getTexture("generic_dialog"))));
        Label priceLabel2 = new Label(" Dieses Item kostet dich " + itemToBuy.getPrice() + " Lana", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        TextButton buyButton = new TextButton("Einkauf Bestätigen", new TextButton.TextButtonStyle(new TextureRegionDrawable(TextureManager.getTexture("generic_dialog_option2")), null, null, new BitmapFont()));
        TextButton cancelButton = new TextButton("Einkauf Abbrechen", new TextButton.TextButtonStyle(new TextureRegionDrawable(TextureManager.getTexture("generic_dialog_option2")), null, null, new BitmapFont()));

        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("buying " + item);
                player.buyItem(item);

            }
        });
        dialog.text(priceLabel2);
        dialog.button(buyButton);
        dialog.button(cancelButton);
        dialog.getButtonTable().padBottom(buyButton.getHeight() / 4f);
        dialog.getContentTable().padTop(priceLabel2.getHeight());
        dialog.setSize(screenWidth / 5.0f, (screenHeight / 5.0f) * Util.getReversedAspectRatio(stage));
        dialog.setPosition((screenWidth / 4.0f) - dialog.getWidth() / 2, (screenHeight / 4.0f) - dialog.getHeight() / 2);

        return dialog;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    public void generateShop(Shops shop) {
        Table table = new Table();
        int i = 0;

        for (Shops.ShopItems item : shop.getItems()) {
            i++;

            //Get the texture based on the item name. Hopefully all texture have been added or this will crash!
            TextureRegionDrawable potion = new TextureRegionDrawable(TextureManager.getTexture(item.name().toLowerCase()));
            System.out.println(item.name().toLowerCase());
            TextureRegionDrawable selected = new TextureRegionDrawable(TextureManager.getTexture("potion_selected"));

            TextButton button = new TextButton("", new ImageTextButton.ImageTextButtonStyle(potion, null, null, new BitmapFont()));
            Image image = new Image(selected);
            //Do not show this otherwise the button will always show as being hovered (didnt use an over texture as I wanted fancy flashing)
            image.addAction(Actions.alpha(0));

            if (i > 3) {
                //Don't show the buttons if they are out of display area (so the fade in / out works correctly (cant fade in whats already there ^^)
                button.addAction(Actions.alpha(0));
            }
            //Needed for the buy function to work
            button.setName(item.name());

            //Items on top of each other so we can have both in one cell (no other way of doing this)
            Stack stack = new Stack();
            stack.add(image);
            stack.add(button);


            stack.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);


                    SnapshotArray<Actor> child = ((Stack) event.getListenerActor()).getChildren();
                    Shops.ShopItems shopItem = Shops.ShopItems.valueOf(child.get(1).getName());

                    //Hovering the button fades in it's information
                    priceLabel.setText(shopItem.getPrice());
                    priceLabel.addAction(Actions.fadeIn(0.5f));
                    itemInformationLabel.setText(shopItem.getInfo());
                    itemInformationLabel.addAction(Actions.fadeIn(0.5f));

                    //child 0 is the "select" image to show button as selected
                    child.get(0).addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), Actions.alpha(0.75f, 5f))));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);

                    SnapshotArray<Actor> child = ((Stack) event.getListenerActor()).getChildren();


                    priceLabel.addAction(Actions.fadeOut(0.5f));
                    itemInformationLabel.addAction(Actions.fadeOut(0.5f));

                    //Remove all animations aka actions otherwise we will have multiple selected buttons (not good)
                    Actor image = child.get(0);
                    for (Action actionFromList : image.getActions()) {
                        image.removeAction(actionFromList);
                    }
                    image.addAction(Actions.fadeOut(0.5f));
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    buy(((Stack) event.getListenerActor()).getChildren().get(1).getName());
                }
            });
            table.add(stack).size(Value.percentWidth(1 / 6f, this), Value.percentWidth(1 / 6f, this)).padLeft(Value.percentWidth((1f / 2f) / 6f, this)).padRight(Value.percentWidth((1f / 2f) / 6f, this));

        }

        this.shop.setActor(table);
        this.setVisible(true);
    }
}
