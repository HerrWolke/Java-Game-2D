package de.marcus.javagame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.managers.GameScreenManager;
import de.marcus.javagame.managers.TextureManager;

public class SelectProfileScreen extends AbstractScreen {
    ImageButton back;
    ImageButton profile1Button;
    ImageButton profile2Button;
    ImageButton profile3Button;
    ImageButton.ImageButtonStyle profile1Style;
    ImageButton.ImageButtonStyle profile2Style;
    ImageButton.ImageButtonStyle profile3Style;
    ImageButton.ImageButtonStyle backStyle;
    Table table;

    public SelectProfileScreen(LoadingScreen app) {
        super(app);
        //app.dispose();


        table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(TextureManager.getFinishedAnimation(true, 2.0f, "play")));
        stage.addActor(table);
        //Style für back Button
        backStyle = new ImageButton.ImageButtonStyle();
        backStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("play"));
        backStyle.imageDown = backStyle.imageUp;
        //erstellen, listener und hinzufügen button
        back = new ImageButton(backStyle);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.START_MENU);
            }

            ;

        });
        table.add(back);
        //Style für Button 1
        profile1Style = new ImageButton.ImageButtonStyle();
        profile1Style.imageDown = new TextureRegionDrawable(TextureManager.getTexture("play"));
        profile1Style.imageUp = profile1Style.imageDown;
        profile1Style.imageOver = new TextureRegionDrawable(TextureManager.getTexture("play"));
        //erstellen, listener und hinzufügen button
        profile1Button = new ImageButton(profile1Style);
        profile1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.GAME1);
            }

            ;
        });
        table.add(profile1Button);
        //Style für Button 2
        profile2Style = new ImageButton.ImageButtonStyle();
        profile2Style = new ImageButton.ImageButtonStyle();
        profile2Style.imageDown = new TextureRegionDrawable(TextureManager.getTexture("play"));
        profile2Style.imageUp = profile2Style.imageDown;
        profile2Style.imageOver = new TextureRegionDrawable(TextureManager.getTexture("play"));
        //erstellen, listener und hinzufügen button
        profile2Button = new ImageButton(profile2Style);
        profile2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.GAME2);
            }

            ;
        });
        table.add(profile2Button);
        //Style für button 3
        profile3Style = new ImageButton.ImageButtonStyle();
        profile3Style = new ImageButton.ImageButtonStyle();
        profile3Style.imageDown = new TextureRegionDrawable(TextureManager.getTexture("play"));
        profile3Style.imageUp = profile3Style.imageDown;
        profile3Style.imageOver = new TextureRegionDrawable(TextureManager.getTexture("play"));
        //erstellen, listener und hinzufügen button
        profile3Button = new ImageButton(profile3Style);
        profile2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.GAME3);
            }

            ;
        });
        table.add(profile3Button);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {

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
