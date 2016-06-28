package com.HyperCauliflower.world;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 28/06/2016.
 */
class WaterTile extends Tile {
    WaterTile(Point location, SpriteSheet spriteSheet, TileData tileData){super(location, spriteSheet,tileData);}

    @Override
    public boolean isLiquid(){
        return true;
    }
}
