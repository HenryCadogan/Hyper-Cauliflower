package com.HyperCauliflower.entities;

/**
 * Created by Henry on 30/06/2016.
 */
public class EntitySpriteData {

    private int startFrame, endFrame, row,width,height;

    EntitySpriteData(int startFrame, int endFrame, int row, int width, int height){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.row = row;
        this.width = width;
        this.height = height;
    }

    int getStartFrame(){return startFrame;}
    int getEndFrame(){return endFrame;}
    int getRow(){return row;}
    int getWidth(){return width;}
    int getHeight(){return height;}
}

