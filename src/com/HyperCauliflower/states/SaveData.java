package com.HyperCauliflower.states;


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

    int getSeed(){
        return seed;
    }
    Point getLocation(){
        return location;
    }
}
