package com.HyperCauliflower.handlers;

import org.json.simple.JSONObject;

/**
 * Created by Matt on 11/07/2016.
 */
public class SpriteHandler extends JSONHandler<SpriteData> {

    SpriteHandler(String name){
        super(name);
    }

    @Override
    protected SpriteData load(JSONObject j) {
        return new SpriteData(Math.toIntExact((long) j.get("startFrame")),Math.toIntExact((long) j.get("endFrame")),Math.toIntExact((long) j.get("row")));
    }
}
