package com.HyperCauliflower.states;

import com.HyperCauliflower.entities.Mob;
import com.HyperCauliflower.entities.MobFactory;
import com.HyperCauliflower.entities.MobHandler;
import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.handlers.SaveHandler;
import com.HyperCauliflower.handlers.SpriteSheetHandler;
import com.HyperCauliflower.items.ItemHandler;
import com.HyperCauliflower.items.Projectile;
import com.HyperCauliflower.items.Weapon;
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
    private Player[] players;
    private int seed;
    private MobFactory factory;
    private SpriteSheetHandler spriteSheetHandler;

    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        spriteSheetHandler = new SpriteSheetHandler();
        MobHandler mobHandler = new MobHandler();
        ItemHandler weaponHandler = new ItemHandler(spriteSheetHandler.get("projectiles"));
        SaveData s = new SaveHandler().get("test");
        cameraPosition = s.getLocation();
        seed = s.getSeed();

        renderables = new ArrayList<>();
        updatables = new ArrayList<>();

        initLayers();

        player = new Player(spriteSheetHandler.get("entities"),"player",cameraPosition,100);
        updatables.add(player);

        //temporary weapon
        Weapon testBow = weaponHandler.generateWeapon("bow",this);
        player.getInventory().equipWeapon(testBow);

        //temporary mob factory
        factory = mobHandler.getFactory("zombie");

        initParticles();

        //test ui
        Button testButton = new Button(new Point(20,20),200,40);
        renderables.add(testButton);

        //grouping the players to do stuff with
        players = new Player[1];
        players[0] = player;

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
        Mob fuckOffMiguel = factory.produce(spriteSheetHandler.get("entities"), new Point(300,167));
        updatables.add(fuckOffMiguel);
        renderables.add(fuckOffMiguel);
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
    private void initLayers(){
        terrainLayer = new TerrainLayer(seed,cameraPosition.translate(new Point(-Main.INTERNAL_WIDTH/2,-Main.INTERNAL_HEIGHT/2)));
        structureLayer = new StructureLayer(seed);
        renderables.add(terrainLayer);
        renderables.add(structureLayer);
        updatables.add(terrainLayer);
    }
    private void initParticles(){
        pSystem = new ParticleSystem("res/sprites/Particles/footsteps.png", 2000);
        pSystem.usePoints();
        renderables.add(player);
        pSystem.addEmitter(player.footsteps);
        pSystem.setVisible(true);
        pSystem.setPosition(0,0);
        player.enableFootsteps();
        pSystem.setBlendingMode(ParticleSystem.BLEND_COMBINE);
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

    public Player getClosestPlayerTo(Point point){
        double distance = Integer.MAX_VALUE;
        // dont want to initialise properly or it wont throw the error catch at the bottom
        int playerIndex = -1;
        double newDistance = 0;
        for (int i =0; i < players.length;i++){
            //calculate distance between the points
            Player player = players[i];
            newDistance = Math.sqrt((Math.pow(player.getLocation().getX() - point.getX(),2)+ (Math.pow(player.getLocation().getY() - point.getY(),2))));
            if (distance > newDistance){
                distance = newDistance;
                playerIndex = i;
            }
        }
        try {
            return players[playerIndex];

        }catch(IndexOutOfBoundsException e){
            System.out.println("You Dun Goofed Somehow...");
            e.printStackTrace();
        } // pointless return as it wont get here but still;
        return players[players.length];
    }

}