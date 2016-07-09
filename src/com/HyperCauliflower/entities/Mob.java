package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetHandler;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Mob extends Entity{

    public Mob(SpriteSheetHandler spriteSheetHandler, String name,Point location){
        super(spriteSheetHandler,"mob", location);
    }

}
