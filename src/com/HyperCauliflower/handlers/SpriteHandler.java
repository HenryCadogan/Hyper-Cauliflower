package com.HyperCauliflower.handlers;

import org.json.simple.JSONObject;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Henry on 28/06/2016.
 */
public class SpriteHandler extends JSONHandler<SpriteSheet> {

    public SpriteHandler(String name){
        super(name);
    }

    @Override

    protected SpriteSheet load(JSONObject j) {
        return null;
    }
}
