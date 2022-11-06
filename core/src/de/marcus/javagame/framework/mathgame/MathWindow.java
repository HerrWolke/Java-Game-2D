package de.marcus.javagame.framework.mathgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import de.marcus.javagame.ui.ui.GenericGameWindow;
import de.marcus.javagame.framework.TextureManager;
import de.marcus.javagame.framework.Util;

import java.util.ArrayList;
import java.util.List;

public class MathWindow extends GenericGameWindow {
    private Stage stage;
    private Skin mSkin;
    private ImageTextButton txtUsername;
    Group mStage;



    public MathWindow (Stage stage) {
        super("", new WindowStyle(new BitmapFont(), Color.WHITE, new TextureRegionDrawable(new TextureRegion(TextureManager.getTexture("generic_dialog")))));
        this.stage = stage;

        float screenHeight = Util.getScreenHeight(stage);
        float screenWidth = Util.getScreenWidth(stage);

        setPosition(screenWidth / 4.0f, screenHeight / 4.0f);
        setSize(screenWidth / 2.0f, screenHeight / 2.0f);
        List<Question> questions = randomQuestions(10);



        Table topTable = new Table();
        Table bottomTable = new Table();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


        TextField txtAnswer = new TextField("", skin);
        Label priceLabel2 = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        TextButton okButton = new TextButton("Ok", new TextButton.TextButtonStyle(new TextureRegionDrawable(TextureManager.getTexture("generic_dialog_option2")), null, null, new BitmapFont()));
        topTable.add(priceLabel2);
        topTable.add(txtAnswer);

        bottomTable.add(okButton);

        this.add(topTable).growX();
        this.row();
        this.add(bottomTable).growX();
    }

    private java.util.List<Question> randomQuestions(int count) {
        List<Question> questions = new ArrayList<Question>();
        while (count --> 0) questions.add(new Question());
        return questions;
    }

}
