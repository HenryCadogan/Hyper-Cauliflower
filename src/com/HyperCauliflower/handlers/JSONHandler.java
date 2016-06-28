package com.HyperCauliflower.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Henry on 28/06/2016.
 */

abstract class JSONHandler<T> {

    private static String PATH = "res/JSON/Configs.JSON";
    private JSONArray obj;
    private HashMap<String,T> map;

    JSONHandler(String name){
        try {
            obj = (JSONArray) ((JSONObject) new JSONParser().parse(new FileReader(PATH))).get(name);
            map = new HashMap<String, T>();
            for (Object j : obj) {
                map.put((String) ((JSONObject) j).get("name"), load((JSONObject) j));
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("JSON File not found at: " + PATH);
        } catch(ParseException e){
            e.printStackTrace();
        }
    }

    protected abstract T load(JSONObject j);

    public T get(String key){
        return map.get(key);
    }
}
