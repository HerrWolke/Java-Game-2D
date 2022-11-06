package de.marcus.javagame.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.framework.TextureManager;

public class StartMenuScreen extends AbstractScreen {
    TextureRegion backgroundImage; //background
    ImageButton.ImageButtonStyle startStyle;
    ImageButton.ImageButtonStyle menuStyle;
    ImageButton.ImageButtonStyle quitStyle;
    ImageButton.ImageButtonStyle achievementStyle;
    ImageButton achievement;
    ImageButton start;
    ImageButton settings; //setting Screen
    ImageButton quit;
    Table table;

    public StartMenuScreen(LoadingScreen app) {

        super(app);

        //app.dispose();

        //style für Startbutton
        startStyle = new ImageButton.ImageButtonStyle();
        startStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("play"));
        startStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("play_sel"));
        startStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("play_sel"));
        //startStyle.imageChecked = new TextureRegionDrawable(TextureManager.getTexture("start_menu_startButton_normal"));
        //style für Menübutton
        menuStyle = new ImageButton.ImageButtonStyle();
        menuStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("settings"));
        menuStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("settings_sel"));
        menuStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("settings_sel"));

        //style für quitButton
        quitStyle = new ImageButton.ImageButtonStyle();

        quitStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("quit"));
        quitStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("quit_sel"));
        quitStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("quit_sel"));
        //style für archievments
        achievementStyle = new ImageButton.ImageButtonStyle();
        achievementStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("achievement"));
        achievementStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("achievement_sel"));
        achievementStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("achievement_sel"));
        //erstellen der Buttons
        start = new ImageButton(startStyle);
        settings = new ImageButton(menuStyle);
        settings.setDisabled(true);
        quit = new ImageButton(quitStyle);

        achievement = new ImageButton(achievementStyle);
        achievement.setDisabled(true);
        //listener für startButton
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                app.g.setScreen(GameScreenManager.SCREENS.SELECT_PROFILE);
                dispose();

            }

            ;
        });
        //listner für quit Button
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                app.dispose();
                System.exit(0);
            }
        });
        //listner für settings Button
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
        //listner für achievements
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
        backgroundImage = TextureManager.getTexture("background");


        //vertical Group wo die Buttons reinkommen
        // verticalGroup.setWidth(550f);


        Gdx.input.setInputProcessor(stage);
        //table in der linkes Bild und verticalGroup sind

        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(backgroundImage));

        table.add(start).pad(25f);
        table.row();
        table.add(settings).pad(25f);
        table.row();
        table.add(achievement).pad(25f);
        table.row();
        table.add(quit).pad(25f);

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
    public void render(float delta) {
        super.render(delta);
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