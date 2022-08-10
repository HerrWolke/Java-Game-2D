package de.marcus.javagame.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.TextureManager;

public class StartMenuScreen extends AbstractScreen{
    Image leftImage;
    ImageButton.ImageButtonStyle startStyle;
    ImageButton.ImageButtonStyle menuStyle;
    ImageButton.ImageButtonStyle quitStyle;
    ImageButton start;
    ImageButton settings; //setting Screen
    ImageButton quit;
    VerticalGroup verticalGroup;
    Table table;
    public StartMenuScreen(LoadingScreen app) {

        super(app);
        verticalGroup = new VerticalGroup();
        app.dispose();

        //style für Startbutton
        ImageButton.ImageButtonStyle startStyle = new ImageButton.ImageButtonStyle();
        startStyle.up           = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.down         = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.checked      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.imageUp      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_startButton_clicked"));
        startStyle.imageDown    = new TextureRegionDrawable(TextureManager.getTexture("start_menu_startButton_normal"));
        startStyle.imageChecked = new TextureRegionDrawable(TextureManager.getTexture("start_menu_startButton_normal"));
        //style für Menübutton
        ImageButton.ImageButtonStyle menuStyle = new ImageButton.ImageButtonStyle();
        startStyle.up           = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.down         = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.checked      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.imageUp      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_menuButton_clicked"));
        startStyle.imageDown    = new TextureRegionDrawable(TextureManager.getTexture("start_menu_menuButton_normal"));
        startStyle.imageChecked = new TextureRegionDrawable(TextureManager.getTexture("start_menu_menuButton_normal"));
       //style für quitButton
        ImageButton.ImageButtonStyle quitStyle = new ImageButton.ImageButtonStyle();
        startStyle.up           = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.down         = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.checked      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_buttonBackground"));
        startStyle.imageUp      = new TextureRegionDrawable(TextureManager.getTexture("start_menu_quitButton_clicked"));
        startStyle.imageDown    = new TextureRegionDrawable(TextureManager.getTexture("start_menu_quitButton_normal"));
        startStyle.imageChecked = new TextureRegionDrawable(TextureManager.getTexture("start_menu_quitButton_normal"));
        //erstellen der Buttons
        start = new ImageButton(startStyle);
        settings = new ImageButton(menuStyle);
        quit = new ImageButton(quitStyle);
       //Bild für linken Rand
        leftImage = new Image(new Texture(Gdx.files.internal("items.png")));
        //vertical Group wo die Buttons reinkommen
        verticalGroup.setWidth(550f);
        verticalGroup.setDebug(true);
        verticalGroup.addActor(start);
        verticalGroup.addActor(settings);
        verticalGroup.addActor(quit);
        verticalGroup.pad(120.0f);


        Gdx.input.setInputProcessor(stage);
        //table in der linkes Bild und verticalGroup sind
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        table.setDebug(true);
        table.add(leftImage).expand();
        table.add(verticalGroup);

    }

    @Override
    public void update(float delta) {
      //code der geupdatet wird
    }

    @Override
    public void show() {
       //statisch

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
