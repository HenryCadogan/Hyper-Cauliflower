package com.HyperCauliflower.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by Henry on 28/06/2016.
 */

public class JSONHandler<T> {

    private static String PATH = "MIGUEL PUT THE PATH HERE";
    JSONArray obj;
    HashMap<String,T> shit;

    public JSONHandler(String name, Loadable<T> func) throws Exception{ //todo: don't leave this like this
        obj = (JSONArray)((JSONObject)new JSONParser().parse(new FileReader(PATH))).get(name);
        shit = new HashMap<String, T>();
        for (Object j:obj){
            shit.put((String)((JSONObject) j).get("name"),func.load((JSONObject) j));
        }
    }

    public T get(String key){
        return shit.get(key);
    }
}
