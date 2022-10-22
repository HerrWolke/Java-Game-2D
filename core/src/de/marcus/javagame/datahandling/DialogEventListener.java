package de.marcus.javagame.datahandling;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import de.marcus.javagame.datahandling.data.shop.Shops;
import de.marcus.javagame.entities.Player;
import de.marcus.javagame.graphics.ui.UI;

public class DialogEventListener extends TypingAdapter {
    private UI ui;
    private Player player;

    public DialogEventListener(UI ui) {
        this.ui = ui;
        player = ui.getPlayer();
    }

    @Override
    public void event(String event) {
        System.out.println("Received text event: " + event);
        if(event.equalsIgnoreCase("OpenShop")) {
            ui.getShopWindow().generateShop(Shops.POTION_SHOP);
        }
    }

    @Override
    public void end() {
        Group postion = ui.getDialogWindow().getDialogOptionsGroup();
        for (int i = 0; i < postion.getChildren().size; i++) {
            ImageTextButton imageTextButton = (ImageTextButton) postion.getChild(i);
            if (!String.valueOf(imageTextButton.getText()).equalsIgnoreCase("")) {
                imageTextButton.setVisible(true);
                imageTextButton.setChecked(i == 0);
            }
        }

        if (ui.getDialogWindow().getDialogHandler().isDialogFinished())
            ui.displayNotification(3000, "Drücke eine beliebige Taste um fortzufahren...");
    }
}
