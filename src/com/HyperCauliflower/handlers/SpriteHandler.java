package com.HyperCauliflower.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Henry on 25/06/2016.
 */
public class SpriteHandler {

    private HashMap<String, SpriteSheet> spriteMap = new HashMap<>();

    public SpriteHandler(){
    }

    public SpriteSheet getSpriteSheet(String name) {

        try {
            if (spriteMap.get(name) != null) {
                return spriteMap.get(name);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Sprite sheet " + name + "is not a valid sprite sheet");
            System.exit(2);
        }
        return null;
    }

    private SpriteSheet loadImage(String path,int width, int height, int spacing) throws SlickException {
        return new SpriteSheet(path,width,height,spacing);
    }

    //todo for each element in the json file
    public void loadSpriteSheets(String path) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject;
            Object obj = parser.parse(new FileReader(path));

            jsonObject = (JSONObject) obj;
            JSONArray sprites = (JSONArray)jsonObject.get("sprites");
            for(Object j:sprites){
                jsonObject = (JSONObject) j;
                this.spriteMap.put((String)jsonObject.get("name"),loadImage((String)jsonObject.get(path),(int)jsonObject.get("width"),(int)jsonObject.get("height"),(int)jsonObject.get("spacing")));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Miguel probably did something wrong, go shout at him \n" +
                    "but seriously, no sprite file found at: " + path );
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
}
