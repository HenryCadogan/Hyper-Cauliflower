package com.HyperCauliflower.handlers;

import com.HyperCauliflower.states.SaveData;
import org.json.simple.JSONObject;

import static java.lang.Math.toIntExact;

/**
 * Created by Matt on 28/06/2016.
 */
public class SaveHandler extends JSONHandler<SaveData> {

    public SaveHandler() {
        super("saves");
    }

    @Override
    protected SaveData load(JSONObject j) {
        return new SaveData(toIntExact((long)j.get("seed")),toIntExact((long)j.get("x")),toIntExact((long)j.get("y")));
    }
}
