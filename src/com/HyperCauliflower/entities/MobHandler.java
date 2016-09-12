package com.HyperCauliflower.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Matt on 12/09/2016.
 */
public class MobHandler {

    private static final String PATH = "res/json/entities.json";
    private HashMap<String, MobFactory> factories;
    private Player player;

    public MobHandler(){
        try {
            factories = new HashMap<>();
            JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader(PATH));
            for(Object entity :(JSONArray)obj.get("mobs")){
                JSONObject e = (JSONObject) entity;
                String name = (String)e.get("name");
                factories.put(name,new MobFactory(
                        name,
                        ((Number)e.get("health")).intValue(),
                        ((Number)e.get("radius")).intValue()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public MobFactory getFactory(String name){
        return factories.get(name);
    }

}
