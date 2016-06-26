package com.HyperCauliflower.entities;

import org.newdawn.slick.SpriteSheet;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Mob extends Entity{

    public Mob(String JsonObject,SpriteSheet spriteSheet){
        super(JsonObject);
    }


}
