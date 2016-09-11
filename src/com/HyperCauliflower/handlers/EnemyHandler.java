package com.HyperCauliflower.handlers;

import com.HyperCauliflower.entities.Entity;
import com.HyperCauliflower.entities.Zombie;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Experiment Account on 11/09/2016.
 */
public class EnemyHandler implements Updatable, Renderable{


    private ArrayList<Entity> enemies;
    private int totalSpawns;
    private int maxSpawns;
    private Point spawnLocation;
    private SpriteSheetHandler spriteSheetHandler;

    public EnemyHandler(int maxSpawns){
        this.maxSpawns = maxSpawns;
        this.spriteSheetHandler = new SpriteSheetHandler();
    }


    @Override
    public void update(GameState game) {

        for (Entity e : enemies){
            e.update(game);
            if (e.getHealth() <= 0){
                // i think this will kill everything but we will see
                enemies.remove(e);
            }
        }
        if (totalSpawns < maxSpawns){
            enemies.add(addNewSpawn());
            totalSpawns ++;
        }

        //keeps enemies out of the main updatable loop which i think is nice and tidy

    }


    @Override
    public void render(Graphics graphics, Point offset) {
        for (Entity e : enemies){
            e.render(graphics,offset);
        }
    }

    private Entity addNewSpawn(){
        // todo determine the type of enemy based on the location of the spawn in here

        //todo create random enemy based on the spawn location and biome, but for now just use a zombie
        return new Zombie(spriteSheetHandler.get("zombie"),"zombie",getSpawnLocation(),100);
    }

    private void removeSpawn(Entity e){
        enemies.remove(e);
    }

    private Point getSpawnLocation() {
        //figure out random spawning outside the screen dimensions
        return new Point(0,0);
    }


    public int getMaxSpawns() {
        return maxSpawns;
    }

    public void setMaxSpawns(int maxSpawns) {
        this.maxSpawns = maxSpawns;
    }

    public int getTotalSpawns() {
        return totalSpawns;
    }

    public void setTotalSpawns(int totalSpawns) {
        this.totalSpawns = totalSpawns;
    }

}

