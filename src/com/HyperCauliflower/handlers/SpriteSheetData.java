package com.HyperCauliflower.handlers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Matt on 11/07/2016.
 */
public class SpriteSheetData {

    private SpriteSheet spriteSheet;
    private SpriteHandler spriteHandler;
    private int height,width;

    SpriteSheetData(SpriteSheet spriteSheet, String dataLocation, int height, int width){
        this.spriteSheet = spriteSheet;
        spriteHandler = new SpriteHandler(dataLocation);
        this.height = height;
        this.width = width;
    }

    public Image getImage(String name, int frame){
        SpriteData s = spriteHandler.get(name);
        return spriteSheet.getSprite(s.getFrameLocation(frame),s.getRow());
    }

    int increaseFrame(String name, int frame){
        return spriteHandler.get(name).getNextFrame(frame);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }


}
