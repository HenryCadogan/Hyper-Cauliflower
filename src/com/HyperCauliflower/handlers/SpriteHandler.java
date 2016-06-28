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

    public SpriteHandler() {
        try {
            loadSpriteSheets("res/JSON/test.JSON");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    private SpriteSheet loadImage(String path, int width, int height, int spacing) throws SlickException {
        return new SpriteSheet(path, width, height, spacing);
    }

    //todo for each element in the json file
    public void loadSpriteSheets(String path) throws IOException, ParseException {
        JSONHandler spritehandler = new JSONHandler();
        spritehandler.LoadSprites();
}
