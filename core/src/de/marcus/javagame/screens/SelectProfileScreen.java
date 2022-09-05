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



        table = new Table();
        stage.addActor(table);

        table.pad(50f);
        table.setDebug(true);
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(TextureManager.getTexture("background")));

        //Style für back Button
        backStyle = new ImageButton.ImageButtonStyle();
        backStyle.imageUp = new TextureRegionDrawable(TextureManager.getTexture("back_arrow"));
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
        TextureRegionDrawable tower = new TextureRegionDrawable(TextureManager.getTexture("tower"));
        // Default Values (Resizen wenn wir wollen)
        tower.setMinHeight(54);
        tower.setMinWidth(30);
        
        back.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-5f),Gdx.graphics.getHeight()-(0.1f * Gdx.graphics.getHeight()));
        stage.addActor(back);
        //Style für Button 1
        profile1Style = new ImageButton.ImageButtonStyle();
        profile1Style.imageDown = tower;
        profile1Style.imageUp = profile1Style.imageDown;
        profile1Style.imageOver = tower;
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


        });
        table.add(profile1Button).padBottom(0f);
        //Style für Button 2

        profile2Style = new ImageButton.ImageButtonStyle();
        profile2Style.imageDown = tower;
        profile2Style.imageUp = profile2Style.imageDown;
        profile2Style.imageOver = tower;
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
        
        profile3Style.imageDown = tower;
        profile3Style.imageUp = profile3Style.imageDown;
        profile3Style.imageOver = tower;



        //erstellen, listener und hinzufügen button
        profile3Button = new ImageButton(profile3Style);

        profile3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                dispose();
                app.g.setScreen(GameScreenManager.SCREENS.GAME3);
            }


        });
        table.add(profile3Button);


    }

    @Override
    public void update(float delta) {
        back.setPosition(Gdx.graphics.getWidth()-(0.99f * Gdx.graphics.getWidth()),Gdx.graphics.getHeight()-(0.05f * Gdx.graphics.getHeight()));
    }

    @Override
    public void show() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Gdx.input.setInputProcessor(stage);
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
