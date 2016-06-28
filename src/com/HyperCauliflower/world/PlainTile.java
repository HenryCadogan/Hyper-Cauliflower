package com.HyperCauliflower.world;

import com.HyperCauliflower.handlers.SpriteHandler;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 25/06/2016.
 */
class PlainTile extends Tile {
    PlainTile(Point location, SpriteHandler spriteHandler){
        super(location, spriteHandler);
    }

    @Override
    protected Point setImage() {
        return new Point(0,0);
    }
}
