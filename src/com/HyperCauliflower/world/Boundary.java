package com.HyperCauliflower.world;

/**
 * Created by Matt on 14/07/2016.
 */
class Boundary {



    private double start, end;
    private Terrain terrain;

    Boundary(double start, double end, Terrain terrain){
        this.start = start;
        this.end = end;
        this.terrain = terrain;
    }

    boolean inBoundary(double x){
        return (x>=start && x <= end);
    }

    Terrain getTerrain(){
        return terrain;
    }
}
