package com.HyperCauliflower.states;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.handlers.SpriteHandler;
import com.HyperCauliflower.world.RenderingWorld;
import org.newdawn.slick.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 24/06/2016.
 */
public class GameState extends BasicGameState {

    private List<Renderable> renderables;
    private List<Updatable> updatables;
    private Point cameraPosition;
    private Player player;
    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        cameraPosition = new Point(0,0); //Change to being loaded from a file
        RenderingWorld r = new RenderingWorld(0);
        renderables = new ArrayList<Renderable>();
        renderables.add(r);
        updatables = new ArrayList<Updatable>();
        updatables.add(r);
        SpriteHandler spriteHandler = new SpriteHandler();
        player = new Player(spriteHandler,"player");

    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,Main.INTERNAL_WIDTH,Main.INTERNAL_HEIGHT);
        for (Renderable r : renderables) {
            r.render(graphics, new Point((Main.INTERNAL_WIDTH/2)-cameraPosition.getX(), (Main.INTERNAL_HEIGHT/2)-cameraPosition.getY()));
        }
    }



    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        cameraPosition.setX(cameraPosition.getX()+1);
        //cameraPosition.setY(cameraPosition.getY()+2);
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)){
            player.move(0);
            System.out.println("Matt is a faggot");
        }

        for (Updatable u : updatables) {
            u.update(this);
        }
    }

    public Point getCameraPosition() {
        return cameraPosition;
    }

}