package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteHandler;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Mob extends Entity{

    public Mob(SpriteHandler spriteHandler,String name){
        super(spriteHandler,"mob");
    }

}
