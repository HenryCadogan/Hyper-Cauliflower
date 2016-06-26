package com.HyperCauliflower.handlers;

import org.newdawn.slick.SpriteSheet;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Henry on 25/06/2016.
 */
public class SpriteHandler {

    private HashMap<String,SpriteSheet> SpriteMap;

    public SpriteHandler(){
        SpriteMap = new HashMap<String, SpriteSheet>();
    }

    public SpriteSheet getSpriteSheet(String name){

        try{
            if (SpriteMap.get(name) != null){
                return SpriteMap.get(name);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Sprite sheet " + name + "is not a valid sprite sheet");
            System.exit(2);
        }
        return null;
    }
}
