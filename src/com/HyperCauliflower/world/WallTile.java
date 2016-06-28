package com.HyperCauliflower.world;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 28/06/2016.
 */
public class WallTile extends Tile {

    WallTile(Point location, SpriteSheet spriteSheet, TileData tileData) {
        super(location, spriteSheet, tileData);
    }

    @Override
    public boolean isPassable(){return false;}
}
