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
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.elements.ScrollPaneShop;
import de.marcus.javagame.misc.Util;


public class ShopWindow extends Window {
    ScrollPaneShop shop;
    Stage stage;
    Player player;

    Shops currentShopType;

    public ShopWindow(Stage stage, Player player) {
        super("", new com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle(new BitmapFont(), Color.WHITE, null));
        this.stage = stage;
        this.player = player;


        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);

        setPosition(screenWidth / 4.0f, screenHeight / 4.0f);
        setSize(screenWidth / 2.0f, screenHeight / 2.0f);
        TextureRegionDrawable drawableBackground = new TextureRegionDrawable(new TextureRegion(new Texture("shop_background.png")));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture("potion_selected.png"));
        TextureRegionDrawable potion = new TextureRegionDrawable(new Texture("items/heal_potion.png"));
        this.setDebug(true);

        Table table = new Table();
        for (int i = 0; i < 60; i++) {
            TextButton button = new TextButton("Text" + i, new ImageTextButton.ImageTextButtonStyle(potion, null, null, new BitmapFont()));
            Image image = new Image(drawable);
            AlphaAction alphaAction = new AlphaAction();
            alphaAction.setAlpha(0.0f);
            image.addAction(alphaAction);

            if (i > 2) {
                AlphaAction alphaAction2 = new AlphaAction();
                alphaAction2.setAlpha(0.0f);
                button.addAction(alphaAction2);
            }

            Stack stack = new Stack();
            stack.add(image);
            stack.add(button);

            button.setName("Shop Button " + i);
            stack.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);

                    AlphaAction action = new AlphaAction();
                    action.setAlpha(1f);
                    action.setDuration(2f);
                    AlphaAction action2 = new AlphaAction();
                    action2.setAlpha(0.75f);
                    action2.setDuration(5f);


                    ((Stack) event.getListenerActor()).getChild(0).addAction(Actions.forever(Actions.sequence(action, action2)));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);


                    System.out.println("is does exit on click");

                    AlphaAction action = new AlphaAction();
                    action.setAlpha(0.0f);
                    action.setDuration(0.5f);

                    Actor image = ((Stack) event.getListenerActor()).getChild(0);
                    for (Action actionFromList : image.getActions()) {
                        image.removeAction(actionFromList);
                    }
                    image.addAction(action);
                }
            });
            table.add(stack).width(100).height(100);

        }

        shop = new ScrollPaneShop(table, new ScrollPane.ScrollPaneStyle(drawableBackground, null, null, null, null));
        shop.setSmoothScrolling(true);
        shop.setScrollingDisabled(false, true);

        this.add(shop).grow().width(300).height(300);
        this.row();
        Dialog dialog = new Dialog("Confirm purchase", new WindowStyle(new BitmapFont(), Color.GOLD, new TextureRegionDrawable(new Texture("generic_dialog.png"))));
        Label priceLabel = new Label("This is price", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        TextButton buyButton = new TextButton("Confirm", new TextButton.TextButtonStyle(null, null, null, new BitmapFont()));
        dialog.text(priceLabel);
        dialog.button(buyButton);
        dialog.setPosition((screenWidth / 4.0f) - 75, (screenHeight / 4.0f) - 75);
//        dialog.setSize(screenWidth / 6.0f,(screenHeight / 6.0f) * Util.getReversedAspectRatio(stage));
        this.setVisible(false);
        this.addActor(dialog);

        stage.setScrollFocus(shop);


    }

    public void buy(String item) {

        if (player.getInventory().getInventory().get(1).getItemCount() > 0) {

        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
