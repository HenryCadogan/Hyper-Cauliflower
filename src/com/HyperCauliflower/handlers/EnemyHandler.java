package com.HyperCauliflower.handlers;

import com.HyperCauliflower.entities.Entity;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Updatable;

import java.util.ArrayList;

/**
 * Created by Experiment Account on 11/09/2016.
 */
public class EnemyHandler implements Updatable{


    private ArrayList<Entity> enemies;
    private int totalSpawns;
    private int maxSpawns;
    private Point spawnLocation;

    public EnemyHandler(int maxSpawns){
        this.maxSpawns = maxSpawns;
    }


    @Override
    public void update(GameState game) {

        if (totalSpawns < maxSpawns){
            addNewSpawn();
            totalSpawns ++;
        }

        //keeps enemies out of the main updatable loop which i think is nice and tidy
        for (Entity e : enemies){
            e.update(game);
        }
    }

    private void addNewSpawn(){
        // todo determine the type of enemy based on the location of the spawn in here
        getSpawnLocation();
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

