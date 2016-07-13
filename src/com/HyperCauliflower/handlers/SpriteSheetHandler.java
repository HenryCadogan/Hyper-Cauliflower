package com.HyperCauliflower.handlers;

import org.json.simple.JSONObject;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


/**
 * Created by Henry on 28/06/2016.
 */
public class SpriteSheetHandler extends JSONHandler<SpriteSheetData> {


    public SpriteSheetHandler() {
        super("spritesheets");
    }

    @Override
    protected SpriteSheetData load(JSONObject j) {
        try {
            return new SpriteSheetData(new SpriteSheet((String) j.get("path"), Math.toIntExact((long)j.get("height")),Math.toIntExact((long)j.get("width"))), ((String) j.get("name")).concat("Data"),Math.toIntExact((long)j.get("height")),Math.toIntExact((long)j.get("width")));
        }catch (SlickException e){
            e.printStackTrace();
            System.exit(2);
        }
        return null;
    }
}
