package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Mob extends Entity{

    public Mob(SpriteSheetData spriteSheetData, String name, Point location){
        super(spriteSheetData,"mob", location);
    }

}
