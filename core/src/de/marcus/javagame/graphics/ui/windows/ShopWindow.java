package de.marcus.javagame.graphics.ui.windows;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.elements.ScrollPaneShop;
import de.marcus.javagame.misc.Util;


public class ShopWindow extends Window {
    ScrollPaneShop shop;
    Stage stage;
    Player player;

    Label priceLabel;
    Label itemInformationLabel;


    Shops currentShopType;

    public ShopWindow(Stage stage, Player player) {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new TextureRegion(new Texture("shop_background.png")))));
        this.stage = stage;
        this.player = player;


        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);

        setPosition(screenWidth / 4.0f, screenHeight / 4.0f);
        setSize(screenWidth / 2.0f, screenHeight / 2.0f);
        TextureRegionDrawable drawableBackground = new TextureRegionDrawable(new TextureRegion(new Texture("shop_background.png")));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture("potion_selected.png"));
        TextureRegionDrawable potion = new TextureRegionDrawable(new Texture("items/heal_potion.png"));
        Table priceTable = new Table();
        Table shopCenterTable = new Table();
        Table shopItemInformation = new Table();

         priceLabel = new Label("0,0 Lana",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         itemInformationLabel = new Label("Item Info",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table = new Table();
        for (int i = 0; i < 60; i++) {
            TextButton button = new TextButton("Text" + i, new ImageTextButton.ImageTextButtonStyle(potion, null, null, new BitmapFont()));
            Image image = new Image(drawable);
            AlphaAction alphaAction = new AlphaAction();
            alphaAction.setAlpha(0.0f);
            image.addAction(alphaAction);

            if (i > 1) {
                AlphaAction alphaAction2 = new AlphaAction();
                alphaAction2.setAlpha(0.0f);
                button.addAction(alphaAction2);
                button.setName("HEAL_POTION");
            } else {
                button.setName("STRENGTH_POTION");
            }


            Stack stack = new Stack();
            stack.add(image);
            stack.add(button);


            stack.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);

                    AlphaAction action2 = new AlphaAction();
                    action2.setAlpha(0.75f);
                    action2.setDuration(5f);

                    SnapshotArray<Actor> child = ((Stack) event.getListenerActor()).getChildren();
                    Shops.ShopItems shopItem = Shops.ShopItems.valueOf(child.get(1).getName());
                    priceLabel.setText(shopItem.getPrice());
                    priceLabel.addAction(Actions.fadeIn(0.5f));
                    itemInformationLabel.setText(shopItem.getInfo());
                    itemInformationLabel.addAction(Actions.fadeIn(0.5f));

                   child.get(0).addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), action2)));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    System.out.println("The button was exited???");

                    SnapshotArray<Actor> child = ((Stack) event.getListenerActor()).getChildren();


                    priceLabel.addAction(Actions.fadeOut(0.5f));
                    itemInformationLabel.addAction(Actions.fadeOut(0.5f));

                    Actor image = child.get(0);
                    for (Action actionFromList : image.getActions()) {
                        image.removeAction(actionFromList);
                    }
                    image.addAction(Actions.fadeOut(0.5f));
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    System.out.println("Button " + event.getListenerActor());
                    buy(((Stack) event.getListenerActor()).getChildren().get(1).getName());
                }
            });
            table.add(stack).size(Value.percentWidth(1/6f,this),Value.percentWidth(1/6f,this)).padLeft(Value.percentWidth((1f/2f)/6f,this)).padRight(Value.percentWidth((1f/2f)/6f,this));

        }

        shop = new ScrollPaneShop(table, new ScrollPane.ScrollPaneStyle(null, null, null, null, null));
        shop.setSmoothScrolling(true);
        shop.setScrollingDisabled(false, true);

//        this.add(shop).grow().width(shop.getWidth()*3).height(shop.getHeight());
//        this.row();

        priceTable.add(priceLabel).top().center().padBottom(priceLabel.getHeight()/2);
        priceTable.top().center();

        this.add(priceTable).growX().padTop(Value.percentHeight(0.11f,this));
        this.row();
        shopCenterTable.bottom();
        shopCenterTable.add(shop).size(Value.percentWidth(1,this),Value.percentHeight(1.0f)).padTop(Value.percentHeight(0.25f));
        this.add(shopCenterTable);
        this.row();
        shopItemInformation.add(itemInformationLabel).padBottom(Value.percentHeight(5.0f));
        this.add(shopItemInformation).grow().top();
        Dialog dialog = new Dialog("Confirm purchase", new WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(new Texture("generic_dialog.png"))));
        Label priceLabel2 = new Label("This is price", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        TextButton buyButton = new TextButton("Confirm", new TextButton.TextButtonStyle(null, null, null, new BitmapFont()));
        dialog.text(priceLabel2);
        dialog.button(buyButton);
        dialog.setPosition((screenWidth / 4.0f) - 75, (screenHeight / 4.0f) - 75);
//        dialog.setSize(screenWidth / 6.0f,(screenHeight / 6.0f) * Util.getReversedAspectRatio(stage));
        this.setModal(false);
        this.setVisible(true);
        this.setMovable(false);


        stage.setScrollFocus(shop);


    }



    public void buy(String item) {
        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);
        Shops.ShopItems itemToBuy = Shops.ShopItems.valueOf(item);

        if(player.canAfford(item)) {
            Dialog dialog = new Dialog("", new WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(new Texture("generic_dialog.png"))));
            Label priceLabel2 = new Label(" Dieses Item kostet dich " + itemToBuy.getPrice() + " Lana", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            TextButton buyButton = new TextButton("Einkauf Bestätigen", new TextButton.TextButtonStyle(null, null, null, new BitmapFont()));

            buyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    System.out.println("buying " + item);
                    player.buyItem(item);
                    setVisible(false);
                }
            });
            dialog.text(priceLabel2);
            dialog.button(buyButton);
            dialog.setSize(screenWidth / 5.0f,(screenHeight / 5.0f) * Util.getReversedAspectRatio(stage));
            dialog.setPosition((screenWidth / 4.0f)-dialog.getWidth()/2, (screenHeight / 4.0f)-dialog.getHeight()/2);
            this.addActor(dialog);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
