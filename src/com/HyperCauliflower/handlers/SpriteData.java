package com.HyperCauliflower.handlers;

/**
 * Created by Matt on 11/07/2016.
 */
public class SpriteData {
    private int startFrame, endFrame, row, frames;
    SpriteData(int startFrame, int endFrame, int row){
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.row = row;
        frames = endFrame - startFrame;
    }

    int getFrameLocation(int frame){
        return frame+startFrame;
    }

    int getRow(){return row;}

    int getNextFrame(int currentFrame){
        if (currentFrame == frames)
            return 0;
        else
            return currentFrame+1;
    }
}
