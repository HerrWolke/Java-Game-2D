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
import de.marcus.javagame.managers.TextureManager;
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
        Table priceTable = new Table();
        Table shopCenterTable = new Table();
        Table shopItemInformation = new Table();

         priceLabel = new Label("0 Lana",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         itemInformationLabel = new Label("Item Info",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        shop = new ScrollPaneShop(null, new ScrollPane.ScrollPaneStyle(null, null, null, null, null));
        shop.setSmoothScrolling(true);
        shop.setScrollingDisabled(false, true);

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


        this.setModal(false);
        this.setVisible(false);
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

    public void generateShop(Shops shop) {
        Table table = new Table();
        int i = 0;
        for (Shops.ShopItems item : shop.getItems()) {
            i++;
            TextureRegionDrawable potion = new TextureRegionDrawable(TextureManager.getTexture(item.name().toLowerCase()));
            System.out.println(item.name().toLowerCase());
            TextureRegionDrawable selected = new TextureRegionDrawable(TextureManager.getTexture("potion_selected"));

            TextButton button = new TextButton("", new ImageTextButton.ImageTextButtonStyle(potion, null, null, new BitmapFont()));
            Image image = new Image(selected);
            image.addAction(Actions.alpha(0));

            if (i > 3) {
                button.addAction(Actions.alpha(0));
            }
            button.setName(item.name());

            Stack stack = new Stack();
            stack.add(image);
            stack.add(button);


            stack.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);


                    SnapshotArray<Actor> child = ((Stack) event.getListenerActor()).getChildren();
                    Shops.ShopItems shopItem = Shops.ShopItems.valueOf(child.get(1).getName());


                    priceLabel.setText(shopItem.getPrice());
                    priceLabel.addAction(Actions.fadeIn(0.5f));
                    itemInformationLabel.setText(shopItem.getInfo());
                    itemInformationLabel.addAction(Actions.fadeIn(0.5f));

                    child.get(0).addAction(Actions.forever(Actions.sequence(Actions.fadeIn(2f), Actions.alpha(0.75f,5f))));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);

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
                    buy(((Stack) event.getListenerActor()).getChildren().get(1).getName());
                }
            });
            table.add(stack).size(Value.percentWidth(1/6f,this),Value.percentWidth(1/6f,this)).padLeft(Value.percentWidth((1f/2f)/6f,this)).padRight(Value.percentWidth((1f/2f)/6f,this));

        }

        this.shop.setActor(table);
        this.setVisible(true);
    }
}
