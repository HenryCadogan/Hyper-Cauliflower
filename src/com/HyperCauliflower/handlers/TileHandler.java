package com.HyperCauliflower.handlers;

import org.json.simple.JSONObject;

/**
 * Created by Matt on 11/07/2016.
 */
public class TileHandler extends JSONHandler<TileData> {

    public TileHandler(){
        super("tilesData");
    }
    @Override
    protected TileData load(JSONObject j) {
        return new TileData(Math.toIntExact((long)j.get("endFrame") - (long)j.get("startFrame")));
    }
}
