package com.HyperCauliflower.states;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.handlers.SaveHandler;
import com.HyperCauliflower.handlers.SpriteSheetHandler;
import com.HyperCauliflower.items.weapons.BowWeapon;
import com.HyperCauliflower.items.weapons.Weapon;
import com.HyperCauliflower.world.TerrainLayer;
import org.newdawn.slick.*;
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
    private Point mousePos;
    private int delta;
    private TerrainLayer r;

    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        SpriteSheetHandler spriteSheetHandler = new SpriteSheetHandler();
        SaveData s = new SaveHandler().get("test");
        cameraPosition = s.getLocation(); //Change to being loaded from a file
        r = new TerrainLayer(s.getSeed(),cameraPosition.translate(new Point(-Main.INTERNAL_WIDTH/2,-Main.INTERNAL_HEIGHT/2)));
        renderables = new ArrayList<Renderable>();
        renderables.add(r);
        updatables = new ArrayList<Updatable>();
        updatables.add(r);

        player = new Player(spriteSheetHandler.get("entities"),"player",cameraPosition);
        updatables.add(player);

        //temporary weapon
        BowWeapon testBow = new BowWeapon(0, 100, "Test", (float) 30);
        player.getInventory().equipWeapon(testBow);

        //stuff for particles
        pSystem = new ParticleSystem("res/sprites/Particles/footsteps.png", 2000);
        pSystem.usePoints();
        renderables.add(player);
        pSystem.addEmitter(player.footsteps);
        pSystem.setVisible(true);
        pSystem.setPosition(0,0);
        player.enableFootsteps();
        pSystem.setBlendingMode(ParticleSystem.BLEND_COMBINE);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,Main.INTERNAL_WIDTH,Main.INTERNAL_HEIGHT);
        for (Renderable r : renderables) {
            r.render(graphics, new Point((Main.INTERNAL_WIDTH/2)-cameraPosition.getX(), (Main.INTERNAL_HEIGHT/2)-cameraPosition.getY()));
        }

        pSystem.render();
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        this.mousePos = new Point(gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY());
        this.cameraPosition = player.getLocation();
        boolean moving = false;
        player.enableFootsteps();
        //System.out.println(r.getWalkable(player.getLocation().getX(),player.getLocation().getY()));

        this.delta = delta;
        if (gameContainer.getInput().isMouseButtonDown(0)) {
            player.usePrimary();
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)) {
            player.move(0);
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

        if (!moving){
            player.disableFootsteps();
        }

        for (Updatable u : updatables) {
            u.update(this);
        }
        pSystem.setPosition(-cameraPosition.getX(),-cameraPosition.getY());
        pSystem.update(delta);
        pSystem.setPosition(Main.INTERNAL_WIDTH/2-cameraPosition.getX(),Main.INTERNAL_HEIGHT/2-cameraPosition.getY());

    }

    public Point getCameraPosition() {
        return cameraPosition;
    }
    public boolean isWalkable(Point p){
        return r.getWalkable(p.getX(),p.getY());
    }
    public float getSpeedMod(Point p){
        return r.getSpeedMod(p.getX(),p.getY());
    }

    public Point getMousePosition() {
        return this.mousePos;
    }
    public int getDelta(){
        return delta;
    }

}