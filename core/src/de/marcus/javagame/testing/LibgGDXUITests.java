package de.marcus.javagame.testing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LibgGDXUITests extends Game {
    Stage stage;

    /**
     * Whole screen UI
     */
    Table mainUIContainer;

    /**
     * Top left UI (Hearts, Armor)
     */
    Table playerInfoUIContainer;

    /**
     * FPS and Coordinates
     */
    Table debugUI;

    /**
     * Position this container at the bottom of the screen (fill entire bottom width)
     */
    Table bottomUIContainer;

    /**
     * This is the top right UI Container
     */
    Table notificationUI;


    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());

        //MAIN TABLE
        mainUIContainer = new Table();
        mainUIContainer.setFillParent(true);
        stage.addActor(mainUIContainer);
        stage.setDebugAll(true);
        //END MAIN TABLE

        //TABLES
        playerInfoUIContainer = new Table();
        debugUI = new Table();
        bottomUIContainer = new Table();
        notificationUI = new Table();
        //END TABLES


        Label label = new Label("TOP TEXT", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label label1 = new Label("NOTIFICATION", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label label2 = new Label("DEBUG", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label label3 = new Label("BOTTOM", new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        //TOP LEFT PLAYER UI INIT
        playerInfoUIContainer.padLeft(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        playerInfoUIContainer.add(label);
        playerInfoUIContainer.top().left();
        //TOP LEFT PLAYER UI INIT END

        //TOP RIGHT NOTIFICATION UI INIT
        notificationUI.padRight(Value.percentWidth(0.01f))
                .padTop(Value.percentHeight(0.01f));

        notificationUI.add(label1);
        notificationUI.top().right();
        //TOP RIGHT NOTIFICATION UI INIT END


        //MIDDLE DEBUG UI INIT
        debugUI.padLeft(Value.percentWidth(0.01f))
                .padRight(Value.percentWidth(0.01f));

        debugUI.add(label2);
        debugUI.left();

        //MIDDLE DEBUG UI INIT END

        //BOTTOM UI INIT
        bottomUIContainer.padLeft(Value.percentWidth(0.01f))
                .padRight(Value.percentWidth(0.01f))
                .padBottom(Value.percentHeight(0.01f));

        bottomUIContainer.add(label3);
        bottomUIContainer.center().bottom();

        //BOTTOM UI INIT END


        mainUIContainer.add(playerInfoUIContainer).grow();
        mainUIContainer.add(notificationUI).grow();
        mainUIContainer.row();
        mainUIContainer.add(debugUI).colspan(2).grow();
        mainUIContainer.row();
        mainUIContainer.add(bottomUIContainer).colspan(2).grow();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
