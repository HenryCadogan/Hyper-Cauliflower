package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
interface Chunk<T> {//stores a 2^n by 2^n grid of tiles

    void save();

    T getPos(Point pos);

    void setPos(Point pos, T elem);

}
