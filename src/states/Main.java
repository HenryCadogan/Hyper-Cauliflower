package states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by Henry on 24/06/2016.
 */
public class Main {

    private static final int FPS = 60;

    public static void main(String args[]){
        System.out.println("Welcome to Matt and Henry hacking out a game");
        try {
            AppGameContainer container = new AppGameContainer(new Game());
            container.setDisplayMode(800,600,false);
            container.setTargetFrameRate(FPS);
            container.start();
        } catch (SlickException e){
            e.printStackTrace();
        };
    }
}
