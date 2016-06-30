package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetHandler;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Mob extends Entity{

    public Mob(SpriteSheetHandler spriteSheetHandler, String name){
        super(spriteSheetHandler,"mob");
    }

}
