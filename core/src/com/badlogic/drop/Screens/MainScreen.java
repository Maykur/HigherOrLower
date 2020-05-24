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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    Game game;
    Stage stage;
    Viewport viewport;
    SpriteBatch batch;
    Sound ds;

    //CONSTRUCTOR THAT SETS UP THE MAIN SCREEN WITH MUSIC, IMAGES, AND TEXT
    public MainScreen(final Game game){
        this.game = game;
        ds = Gdx.audio.newSound(Gdx.files.internal("Dark Souls III Soundtrack OST - Main Menu Theme.mp3"));
        ds.play(0.5f);
        ds.loop();
        viewport = new FitViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn = new TextButton("Click to Play!", textButtonStyle);
        btn.setPosition(350, 50);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                ds.stop();
                game.setScreen(new GameScreen(game));
                return false;
            }
        });
        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("high2 (1).png"))));
        Table background = new Table();
        background.add(backGround).center();

        stage.addActor(backGround);
        stage.addActor(btn);
    }


    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    //IMPLEMENTED METHOD THAT RENDERS THE SCREEN
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
