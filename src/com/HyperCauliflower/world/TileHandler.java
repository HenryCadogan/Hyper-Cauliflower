package com.HyperCauliflower.world;

import com.HyperCauliflower.handlers.JSONHandler;
import org.json.simple.JSONObject;

import static java.lang.Math.toIntExact;

/**
 * Created by Matt on 28/06/2016.
 */
public class TileHandler extends JSONHandler<TileData> {

    public TileHandler(){super("tiles");}

    @Override
    protected TileData load(JSONObject j) {
        return new TileData(toIntExact((long)j.get("startFrame")),toIntExact((long)j.get("endFrame")),toIntExact((long)j.get("row")));
    }
}
