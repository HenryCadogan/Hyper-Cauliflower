package com.HyperCauliflower.states;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 28/06/2016.
 */
public class SaveData {
    private int seed;
    private Point location;
    public SaveData(int seed, int x, int y){
        this.seed = seed;
        location = new Point(x,y);
    }

    public int getSeed(){
        return seed;
    }
    public Point getLocation(){
        return location;
    }
}
