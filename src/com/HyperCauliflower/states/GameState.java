package com.HyperCauliflower.states;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.handlers.SaveHandler;
import com.HyperCauliflower.handlers.SpriteSheetHandler;
import com.HyperCauliflower.world.RenderingWorld;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.particles.ParticleSystem;
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
    public ParticleSystem pSystem;
    public Point mousePos;
    private int delta;

    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        SpriteSheetHandler spriteSheetHandler = new SpriteSheetHandler();
        SaveData s = new SaveHandler().get("test");
        cameraPosition = s.getLocation(); //Change to being loaded from a file
        RenderingWorld r = new RenderingWorld(s.getSeed(), spriteSheetHandler.get("tiles"));
        renderables = new ArrayList<Renderable>();
        renderables.add(r);
        updatables = new ArrayList<Updatable>();
        updatables.add(r);

        player = new Player(spriteSheetHandler.get("entities"),"player",cameraPosition);
        player.setPlayerMoveSpeed(5);

        updatables.add(player);
        renderables.add(player);

        pSystem = new ParticleSystem("res/sprites/Particles/footsteps.png",2000);
        pSystem.usePoints();
        pSystem.addEmitter(player.footsteps);
        pSystem.setVisible(true);
        pSystem.setPosition(0,0);
        player.enableFootsteps();
        pSystem.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);


    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,Main.INTERNAL_WIDTH,Main.INTERNAL_HEIGHT);
        for (Renderable r : renderables) {
            r.render(graphics, new Point((Main.INTERNAL_WIDTH/2)-cameraPosition.getX(), (Main.INTERNAL_HEIGHT/2)-cameraPosition.getY()));
        }
        graphics.drawString(String.valueOf(pSystem.getParticleCount()),20,30);
        pSystem.render();
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        this.mousePos = new Point(gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY());
        this.cameraPosition = player.getLocation();
        boolean moving = false;
        this.delta = delta;

        if (gameContainer.getInput().isKeyDown(Input.KEY_W)){
            player.move(0);
            //todo fix this shit
            moving = true;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_S)){
            player.move(2);
            moving = true;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_A)){
            player.move(3);
            moving = true;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_D)){
            player.move(1);
            moving = true;
        }
        for (Updatable u : updatables) {
            u.update(this);
        }

        pSystem.setPosition(-cameraPosition.getX(),-cameraPosition.getY());
        pSystem.update(delta);
        pSystem.setPosition(Main.INTERNAL_WIDTH/2-cameraPosition.getX(),Main.INTERNAL_HEIGHT/2-cameraPosition.getY());

        if (!moving){
            player.disableFootsteps();
            pSystem.getEmitter(0).resetState();
        }

    }

    public Point getCameraPosition() {
        return cameraPosition;
    }

    public Point getMousePosition(){
        return this.mousePos;
    }

    public int getDelta(){
        return delta;
    }

}