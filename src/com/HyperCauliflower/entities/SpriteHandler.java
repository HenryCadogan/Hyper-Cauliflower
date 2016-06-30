package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.JSONHandler;
import org.json.simple.JSONObject;
import static java.lang.Math.toIntExact;

/**
 * Created by Henry on 30/06/2016.
 */
public class SpriteHandler extends JSONHandler<SpriteData> {

    public SpriteHandler() {
        super("sprites");
    }

    @Override
    protected SpriteData load(JSONObject j) {
        return new SpriteData(toIntExact((long) j.get("startFrame")), toIntExact((long) j.get("endFrame")), toIntExact((long) j.get("row")),toIntExact((long)j.get("width")),toIntExact((long)j.get("height")));

    }
}