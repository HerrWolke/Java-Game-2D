package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.TextureManager;

public class StartMenuScreen extends AbstractScreen {
    Image backgroundImage; //background
    ImageButton.ImageButtonStyle startStyle;
    ImageButton.ImageButtonStyle menuStyle;
    ImageButton.ImageButtonStyle quitStyle;
    ImageButton.ImageButtonStyle achievementStyle;
    ImageButton achievement;
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
        startStyle = new ImageButton.ImageButtonStyle();

        startStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("play"));
        startStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("play_sel"));
        startStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("play_sel"));
        //startStyle.imageChecked = new TextureRegionDrawable(TextureManager.getTexture("start_menu_startButton_normal"));
        //style für Menübutton
        menuStyle = new ImageButton.ImageButtonStyle();

        menuStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("set"));
        menuStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("set_sel"));
        menuStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("set_sel"));

        //style für quitButton
        quitStyle = new ImageButton.ImageButtonStyle();

        quitStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("quit"));
        quitStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("quit_sel"));
        quitStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("quit_sel"));

        //style für achievements
        achievementStyle = new ImageButton.ImageButtonStyle();

        achievementStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("ach"));
        achievementStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("ach_sel"));
        achievementStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("ach_sel"));

        //erstellen der Buttons
        start = new ImageButton(startStyle);
        settings = new ImageButton(menuStyle);
        quit = new ImageButton(quitStyle);
        achievement = new ImageButton(achievementStyle);

        //listener für startButton
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.SELECT_PROFILE);
            }

            ;
        });

        //listener für quit Button
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                app.dispose();
            }
        });

        //listener für settings Button
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.SETTINGS);
            }

            ;
        });

        //listener für archievements
        achievement.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.SETTINGS);                                       //TODO: Achievement screen
            }

            ;
        });

        //Bild für linken Rand
        // backgroundImage = new Image(TextureManager.getTexture("background"));
        //vertical Group wo die Buttons reinkommen
        // verticalGroup.setWidth(550f);
        verticalGroup.setDebug(true);
        verticalGroup.addActor(start);
        verticalGroup.addActor(achievement);
        verticalGroup.addActor(settings);
        verticalGroup.addActor(quit);
        verticalGroup.pad(120.0f);


        Gdx.input.setInputProcessor(stage);
        //table in der linkes Bild und verticalGroup sind
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        table.setDebug(true);
        table.add(backgroundImage);
        table.add(verticalGroup);
        table.setFillParent(true);


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
