package com.HyperCauliflower.world;

/**
 * Created by Matt on 28/06/2016.
 */
class TileData {

    private int startFrame, endFrame, row;

    TileData(int startFrame, int endFrame, int row){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.row = row;
    }

    int getStartFrame(){return startFrame;}
    int getEndFrame(){return endFrame;}
    int getRow(){return row;}
}
