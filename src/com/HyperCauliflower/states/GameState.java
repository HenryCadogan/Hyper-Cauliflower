package com.HyperCauliflower.states;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.handlers.EnemyHandler;
import com.HyperCauliflower.handlers.SaveHandler;
import com.HyperCauliflower.handlers.SpriteSheetHandler;
import com.HyperCauliflower.items.Projectile;
import com.HyperCauliflower.items.Weapon;
import com.HyperCauliflower.items.WeaponHandler;
import com.HyperCauliflower.ui.Button;
import com.HyperCauliflower.world.StructureLayer;
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
    private TerrainLayer terrainLayer;
    private StructureLayer structureLayer;
    private GameContainer gameContainer;
    private EnemyHandler enemyHandler;

    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.gameContainer = gameContainer;
        SpriteSheetHandler spriteSheetHandler = new SpriteSheetHandler();
        WeaponHandler weaponHandler = new WeaponHandler(spriteSheetHandler.get("projectiles"));
        SaveData s = new SaveHandler().get("test");
        cameraPosition = s.getLocation();
        terrainLayer = new TerrainLayer(s.getSeed(),cameraPosition.translate(new Point(-Main.INTERNAL_WIDTH/2,-Main.INTERNAL_HEIGHT/2)));
        structureLayer = new StructureLayer(s.getSeed());
        renderables = new ArrayList<Renderable>();
        renderables.add(terrainLayer);
        renderables.add(structureLayer);
        updatables = new ArrayList<Updatable>();
        updatables.add(terrainLayer);

        player = new Player(spriteSheetHandler.get("entities"),"player",cameraPosition,100);
        updatables.add(player);

        //temporary weapon
        Weapon testBow = weaponHandler.generateWeapon("bow",this);
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

        //test ui
        Button testButton = new Button(new Point(20,20),200,40);
        renderables.add(testButton);

        //enemies and shiz
        enemyHandler = new EnemyHandler(15);
        renderables.add(enemyHandler);
        updatables.add(enemyHandler);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,Main.INTERNAL_WIDTH,Main.INTERNAL_HEIGHT);
        for (Renderable r : renderables) {
            r.render(graphics, new Point((Main.INTERNAL_WIDTH/2)-cameraPosition.getX(), (Main.INTERNAL_HEIGHT/2)-cameraPosition.getY()));
        }

        pSystem.render();
        // render ui
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        this.mousePos = new Point(gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY());
        this.cameraPosition = player.getLocation();
        boolean moving = false;
        player.enableFootsteps();

        //System.out.println(terrainLayer.getWalkable(player.getLocation().getX(),player.getLocation().getY()));

        this.delta = delta;
        if (gameContainer.getInput().isMouseButtonDown(0)) {
            player.usePrimary();
        }
        if (gameContainer.getInput().isMouseButtonDown(1)){
            player.takeDamage(12);
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
        return structureLayer.getWalkable(p.getX(),p.getY());
    }
    public float getSpeedMod(Point p){
        return terrainLayer.getSpeedMod(p.getX(),p.getY());
    }
    public Point getMousePosition() {
        return this.mousePos;
    }
    public int getDelta(){
        return delta;
    }

    public Color getColor(Point p){return terrainLayer.getColor(p.getX(),p.getY());}

    public void addProjectile(Projectile p){
        updatables.add(p);
        renderables.add(p);
    }
}