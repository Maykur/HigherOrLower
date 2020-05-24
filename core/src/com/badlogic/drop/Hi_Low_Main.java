package com.badlogic.drop;

import com.badlogic.drop.Screens.MainScreen;
import com.badlogic.gdx.Game;

public class Hi_Low_Main extends Game {
    //INSTANCE VARIABLE
    MainScreen mainscreen;

    /*
    IMPLEMENTED METHOD THAT ACTS AS THE CONSTRUCTOR CREATING A NEW MAINSCREEN
    AND SETTING THE SCREEN TO THAT
     */
    @Override
    public void create(){
        mainscreen = new MainScreen(this);
        setScreen(mainscreen);
    }

    //IMPLEMENTED METHOD
    public void render(){
        super.render();
    }

    //IMPLEMENTED METHOD
    public void dispose(){
        super.dispose();
    }

}
