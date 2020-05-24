package com.badlogic.drop.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Loss extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    Game game;
    Stage stage;
    Table table;
    Viewport viewport;
    Table table2;
    Table table3;
    Label label;
    Label label2;
    SpriteBatch batch;
    Sound kahoot;

    //CONSTRUCTOR THAT SETS UP THE LOSS SCREEN WITH TEXT, IMAGES, AND MUSIC
    public Loss(final Game game){
        this.game = game;
        kahoot = Gdx.audio.newSound(Gdx.files.internal("Sad Trombone.mp3"));
        kahoot.play(1.0f);
        kahoot.loop();
        viewport = new StretchViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        table = new Table();
        table2 = new Table();
        table3 = new Table();
        label = new Label("LOSE :(", new Label.LabelStyle(new BitmapFont(), Color.RED));
        label2 = new Label("You got 3 wrong and only got " + GameScreen.getWins() +" correct.", new Label.LabelStyle(new BitmapFont(), Color.RED));

        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn = new TextButton("Restart!", textButtonStyle);
        btn.setPosition(350, 50);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                kahoot.stop();
                game.setScreen(new GameScreen(game));
                return false;
            }
        });


        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("wood.png"))));

        table.top();
        table.setFillParent(true);
        table2.center();
        table2.setFillParent(true);
        table3.bottom();
        table3.setFillParent(true);

        Table background = new Table();
        background.add(backGround).center();

        label.setFontScale(2);
        label2.setFontScale(2);

        stage.addActor(backGround);
        table.add(label);
        table2.add(label2);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(btn);
    }


    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    //IMPLEMENTED METHOD THAT RENDERS SCREEN
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    //IMPLEMENTED METHOD
    @Override
    public void hide() {

    }
}
