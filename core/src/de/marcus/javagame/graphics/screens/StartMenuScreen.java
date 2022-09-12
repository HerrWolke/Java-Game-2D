package de.marcus.javagame.graphics.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.TextureManager;

public class StartMenuScreen extends AbstractScreen {
    TextureRegion backgroundImage; //background
    ImageButton.ImageButtonStyle startStyle;
    ImageButton.ImageButtonStyle menuStyle;
    ImageButton.ImageButtonStyle quitStyle;
    ImageButton.ImageButtonStyle archievementStyle;
    ImageButton archievment;
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
        archievementStyle = new ImageButton.ImageButtonStyle();
        archievementStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("achievement"));
        archievementStyle.imageDown = new TextureRegionDrawable(TextureManager.getTexture("achievement_sel"));
        archievementStyle.imageOver = new TextureRegionDrawable(TextureManager.getTexture("achievement_sel"));
        //erstellen der Buttons
        start = new ImageButton(startStyle);
        settings = new ImageButton(menuStyle);
        quit = new ImageButton(quitStyle);
        archievment = new ImageButton(archievementStyle);
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
        //listner für archievements
        archievment.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.SETTINGS);                                       //TODO: Archievment screen
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
        table.setDebug(true);
        stage.addActor(table);
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(backgroundImage));

        table.add(start).pad(25f);
        table.row();
        table.add(settings).pad(25f);
        table.row();
        table.add(archievment).pad(25f);
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