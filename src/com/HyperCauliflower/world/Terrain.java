package com.HyperCauliflower.world;

import org.json.simple.JSONObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Matt on 14/07/2016.
 */
class Terrain {

    private boolean walkable;
    private int[][][] colors;
    static final int RED = 0, GREEN = 1, BLUE = 2;
    private float speedMod;
    Terrain(JSONObject jsonObject, int width, SpriteSheet spriteSheet){
        colors = new int[width][width][3];
        walkable = (boolean)jsonObject.get("walkable");
        speedMod = ((Number)jsonObject.get("speed")).floatValue();
        Image img = spriteSheet.getSprite(Math.toIntExact((long)jsonObject.get("x")),Math.toIntExact((long)jsonObject.get("y")));
        for(int i = 0; i < width; i++)
            for(int j = 0; j < width; j++){
                colors[i][j][RED] = img.getColor(i,j).getRed();
                colors[i][j][GREEN] = img.getColor(i,j).getGreen();
                colors[i][j][BLUE] = img.getColor(i,j).getBlue();
            }

    }


    boolean getWalkable(){
        return walkable;
    }
    float getSpeedMod(){return speedMod;}
    int getColor(int x, int y, int color){
        return colors[x][y][color];
    }
}
