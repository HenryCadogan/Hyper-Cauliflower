package com.HyperCauliflower.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Henry on 28/06/2016.
 */

public class JSONHandler {
    ArrayList<String> paths = new ArrayList<>();

    public JSONHandler(){
        paths = loadPaths();
    }

    private ArrayList<String> loadPaths(){
        return null;
    }


    public void LoadSprites(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject;
            Object obj = parser.parse(new FileReader(path));

            jsonObject = (JSONObject) obj;
            JSONArray sprites = (JSONArray) jsonObject.get("sprites");
            for (Object j : sprites) {
                jsonObject = (JSONObject) j;
                this.spriteMap.put((String) jsonObject.get("name"), SpriteHandler.((String) jsonObject.get(path), (int) jsonObject.get("width"), (int) jsonObject.get("height"), (int) jsonObject.get("spacing")));
                System.out.println((String) jsonObject.get("name"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Matt probably did something wrong, go shout at him \n" +
                    "but seriously, no sprite file found at: " + path);
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }

    }
}
