package com.HyperCauliflower.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by Henry on 28/06/2016.
 */

abstract class JSONHandler<T> {

    private static String PATH = "MIGUEL PUT THE PATH HERE";
    private JSONArray obj;
    private HashMap<String,T> map;

    JSONHandler(String name) throws Exception{ //todo: don't leave this like this
        obj = (JSONArray)((JSONObject)new JSONParser().parse(new FileReader(PATH))).get(name);
        map = new HashMap<String, T>();
        for (Object j:obj){
            map.put((String)((JSONObject) j).get("name"),load((JSONObject) j));
        }
    }

    protected abstract T load(JSONObject j);

    public T get(String key){
        return map.get(key);
    }
}
