package com.HyperCauliflower.states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by Henry on 24/06/2016.
 */
public class Main {

    private static final int FPS = 60;
    public static final int INTERNAL_HEIGHT = 720, INTERNAL_WIDTH = 1280;

    public static void main(String args[]){
        try {
            AppGameContainer container = new AppGameContainer(new Game());
            container.setDisplayMode(INTERNAL_WIDTH,INTERNAL_HEIGHT,false);
            container.setTargetFrameRate(FPS);
            container.start();
        } catch (SlickException e){
            e.printStackTrace();
        };
    }
}
