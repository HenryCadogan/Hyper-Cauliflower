package com.HyperCauliflower.world;

import com.HyperCauliflower.handlers.SpriteHandler;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 28/06/2016.
 */
class SandTile extends PlainTile {
    SandTile(Point location, SpriteHandler spriteHandler){
        super(location, spriteHandler);
    }

    @Override
    protected Point setImage() {
        return new Point(2,0);
    }
}
