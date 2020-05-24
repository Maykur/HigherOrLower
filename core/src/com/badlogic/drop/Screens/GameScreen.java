package com.badlogic.drop.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameScreen extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    SpriteBatch batch;
    private static int loss;
    private static int wins;
    private static int cardCount;
    Texture img;
    TextureRegion textRe;
    Image cardImage;
    Game game;
    Stage stage;
    Table table;
    Viewport viewport;
    Table table2;
    Table table3;
    Table table10;
    Label label;
    Label label2;
    List keylist;
    HashMap<String,Integer> cards;
    Sound wavSound;

    /*
    CONSTRUCTOR THAT CREATES THE GAME AND USES THE BUTTONS TO PLAY
    Works by adding up all the images of cards into a HashMap called cards, which stores a string that represents an image and an integer, representing a value
    for that card. We use the string and integer to display the card and check if the guess was correct or not. The game ends when you've guessed wrong 3 times
    or you get through all 52 cards in the deck. If you end up guessing wrong 3 times, you lose and it takes you to the lose screen where it displays how many you got right
    and wrong. If you win, you go to the win screen and get congratulated on winning. The game uses the deal method to have a current and future card which is what's being checked
    every round.
     */
    public GameScreen(final Game game) {
        wavSound = Gdx.audio.newSound(Gdx.files.internal("Kahoot Lobby Music.mp3"));
        wavSound.play(1.0f);
        wavSound.loop();
        loss = 0;
        wins = 0;
        cardCount = 0;
        this.game = game;
        viewport = new StretchViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        table = new Table();
        table2 = new Table();
        table10 = new Table();

        cards = new HashMap<>();
        cards.put("Clubs/AC.png", 1);
        cards.put("Clubs/2C.png", 2);
        cards.put("Clubs/3C.png", 3);
        cards.put("Clubs/4C.png", 4);
        cards.put("Clubs/5C.png", 5);
        cards.put("Clubs/6C.png", 6);
        cards.put("Clubs/7C.png", 7);
        cards.put("Clubs/8C.png", 8);
        cards.put("Clubs/9C.png", 9);
        cards.put("Clubs/10C.png", 10);
        cards.put("Clubs/JC.png", 11);
        cards.put("Clubs/QC.png", 12);
        cards.put("Clubs/KC.png",13 );
        cards.put("Diamond/AD.png",1);
        cards.put("Diamond/2D.png",2);
        cards.put("Diamond/3D.png",3);
        cards.put("Diamond/4D.png",4);
        cards.put("Diamond/5D.png",5);
        cards.put("Diamond/6D.png",6);
        cards.put("Diamond/7D.png",7);
        cards.put("Diamond/8D.png",8);
        cards.put("Diamond/9D.png",9);
        cards.put("Diamond/10D.png",10);
        cards.put("Diamond/JD.png",11);
        cards.put("Diamond/QD.png",12);
        cards.put("Diamond/KD.png",13);
        cards.put("Hearts/AH.png",1);
        cards.put("Hearts/2H.png",2);
        cards.put("Hearts/3H.png",3);
        cards.put("Hearts/4H.png",4);
        cards.put("Hearts/5H.png",5);
        cards.put("Hearts/6H.png",6);
        cards.put("Hearts/7H.png",7);
        cards.put("Hearts/8H.png",8);
        cards.put("Hearts/9H.png",9);
        cards.put("Hearts/10H.png",10);
        cards.put("Hearts/JH.png",11);
        cards.put("Hearts/QH.png",12);
        cards.put("Hearts/KH.png",13);
        cards.put("Spades/AS.png",1);
        cards.put("Spades/2S.png",2);
        cards.put("Spades/3S.png",3);
        cards.put("Spades/4S.png",4);
        cards.put("Spades/5S.png",5);
        cards.put("Spades/6S.png",6);
        cards.put("Spades/7S.png",7);
        cards.put("Spades/8S.png",8);
        cards.put("Spades/9S.png",9);
        cards.put("Spades/10S.png",10);
        cards.put("Spades/JS.png",11);
        cards.put("Spades/QS.png",12);
        cards.put("Spades/KS.png",13);
        keylist = new ArrayList(cards.keySet());
        Collections.shuffle(keylist);

        table3 = new Table();
        table3.bottom();
        table3.pad(0,800,200,0);

        textRe = new TextureRegion(new Texture(Gdx.files.internal((String)keylist.get(0))));

       // card = deck.deal();
       // card2 = deck.deal();

        label = new Label("Number Correct: " + wins, new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        label2= new Label("Number Wrong: " + loss, new Label.LabelStyle(new BitmapFont(), Color.RED));
        table.setPosition(100, 750);
        table2.setPosition(100, 730);
        table10.setPosition(650,750);

        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn = new TextButton("Higher!", textButtonStyle);
        btn.setPosition(275, 50);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(cardCount < 52 && loss < 3) {
                    String currentCard = dealCard();
                    String nextCard = ranCard();
                    if (isEqual(currentCard, nextCard)) {
                        //do nothing :)
                    } else if (isHigher(currentCard, nextCard)) {
                        wins++;
                        cardCount++;
                        if (cardCount == 52) {
                            wavSound.stop();
                            game.setScreen(new Win(game));
                        }
                        label.setText("Number Correct: " + wins);
                        System.out.println("ye");
                        table3.clear();
                        textRe = new TextureRegion(new Texture(Gdx.files.internal(nextCard)));
                        cardImage = new Image(textRe);
                        table3.add(cardImage).width(250).height(400).center();
                    } else {
                        loss++;
                        cardCount++;
                        if (loss == 3) {
                            wavSound.stop();
                            game.setScreen(new Loss(game));
                        } else if (cardCount == 52) {
                            wavSound.stop();
                            game.setScreen(new Win(game));
                        }
                        label2.setText("Number Wrong: " + loss);

                        System.out.println("no");
                        table3.clear();
                        textRe = new TextureRegion(new Texture(Gdx.files.internal(nextCard)));
                        cardImage = new Image(textRe);
                        table3.add(cardImage).width(250).height(400).center();
                    }


                    return false;
                }
                else if(loss == 3){
                    wavSound.stop();
                    game.setScreen(new Loss(game));
                }
                else if(cardCount == 52){
                    wavSound.stop();
                    game.setScreen(new Win(game));
                }
                return false;
            }
        });

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.up = skin.newDrawable("button", Color.WHITE);
        tbs.down = skin.newDrawable("button-down", Color.WHITE);
        tbs.font = font2;

        TextButton butt = new TextButton("Lower!", textButtonStyle);
        butt.setPosition(500, 50);
        butt.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(cardCount < 52 && loss < 3) {
                    String currentCard = dealCard();
                    String nextCard = ranCard();
                    if(isEqual(currentCard, nextCard)){
                        //do nothing :)
                    }
                    else if(isLower(currentCard, nextCard)) {
                        wins++;
                        cardCount++;
                        if(cardCount == 52){
                            wavSound.stop();
                            game.setScreen(new Win(game));
                        }
                        label.setText("Number Correct: " + wins);
                        System.out.println("ye");
                        table3.clear();
                        textRe = new TextureRegion(new Texture(Gdx.files.internal(nextCard)));
                        cardImage = new Image(textRe);
                        table3.add(cardImage).width(250).height(400).center();
                    }
                    else{
                        loss++;
                        cardCount++;
                        if(loss == 3){
                            wavSound.stop();
                            game.setScreen(new Loss(game));
                        }
                        else if(cardCount == 52){
                            wavSound.stop();
                            game.setScreen(new Win(game));
                        }
                        label2.setText("Number Wrong: " + loss);

                        System.out.println("no");
                        table3.clear();
                        textRe = new TextureRegion(new Texture(Gdx.files.internal(nextCard)));
                        cardImage = new Image(textRe);
                        table3.add(cardImage).width(250).height(400).center();
                    }
                    return false;
                }
                else if(loss == 3){
                    wavSound.stop();
                    game.setScreen(new Loss(game));
                }
                else if(cardCount == 52){
                    wavSound.stop();
                    game.setScreen(new Win(game));
                }
                return false;
            }
        });

        TextButton.TextButtonStyle help = new TextButton.TextButtonStyle();
        help.up = skin.newDrawable("button", Color.WHITE);
        help.down = skin.newDrawable("button-down", Color.WHITE);
        help.font = font2;

        TextButton halp = new TextButton("Help", textButtonStyle);
        halp.setPosition(700, 730);
        halp.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                JOptionPane.showMessageDialog(null,"Game Breakdown:\n" +
                        "This is a game of higher or lower where you must guess to win.\n" +
                        "You are presented a card and must guess whether the next card will be higher or lower.\n" +
                        "You are only given 3 chances before failing so guess correctly!\n" +
                        "The point system works as follows:\n" +
                        "Correct = +1 point for correct\n" +
                        "Incorrect = +1 point for wrong\n" +
                        "Equal = +0 for both; Why gain or lose when they were equal");
                return false;
            }
        });


        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("wood.png"))));
        Table background = new Table();
        background.add(backGround).center();

        cardImage=new Image(textRe);
        table.add(label);
        table2.add(label2);
        stage.addActor(backGround);
        stage.addActor(btn);
        stage.addActor(butt);
        stage.addActor(halp);
        table3.add(cardImage).width(250).height(400);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);
    }

    /*
    This method deals a card, and returns a string which is whatever the image name at keylist.get(0) is.
    This method then removes the element at that index.
     */
    public String dealCard(){
        String s = (String) keylist.get(0);
        keylist.remove(0);
        return s;
    }

    //IMPLEMENTED METHOD
    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    //IMPLEMENTED METHOD THAT RENDERS THE SCREEN
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    //IMPLEMENTED METHOD
    @Override
    public void hide() {

    }

    /*
    This method checks if the card's value for c1 is greater than c2, this is where the integer value from setting up the
    hashmap comes in handy.
     */
    private boolean isHigher(String c1, String c2) {
        return cards.get(c1) > cards.get(c2);
    }

    /*
   This method checks if the card's value for c2 is greater than c1, this is where the integer value from setting up the
   hashmap comes in handy.
    */
    private boolean isLower(String c1, String c2) {

        return cards.get(c1) < cards.get(c2);
    }

    /*
   This method checks if the card's value for c1 is equal to c2, this is where the integer value from setting up the
   hashmap comes in handy.
    */
    private boolean isEqual(String c1, String c2) {

        return cards.get(c1) == cards.get(c2);
    }

    //IMPLEMENTED METHOD THAT DISPOSES CONTENT
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
        wavSound.dispose();
    }

    /*
    This method returns loss, so we can have it for the loss and win screen
    */
    public static int getLoss(){
        return loss;
    }

    /*
    This method returns wins, so we can have it for the loss and win screen
    */
    public static int getWins(){
        return wins;
    }

    /*
    This method deals a random card from the shuffled array list (keylist) which has the string values from the hash map.
    After that, it sets the texture of the card in the center of the screen equal to the value of the string of keylist at index 0.
    Then it sets the image object cardImage equal to the textureRegion and then clear the old table to add the new card onto it. Draw the stage with it's updated
    table and return the card's string that we drew.
     */
    private String ranCard(){
        Collections.shuffle(keylist);
        textRe = new TextureRegion(new Texture(Gdx.files.internal((String)keylist.get(0))));
        cardImage = new Image(textRe);
        table3.clear();
        table3.add(cardImage).width(250).height(400).center();
        stage.draw();
        return (String)keylist.get(0);

    }

}
