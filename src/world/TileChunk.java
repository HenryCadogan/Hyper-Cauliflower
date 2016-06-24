package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public class TileChunk implements Chunk<Tile> {

    private Chunk parent;

    TileChunk(Chunk parent){
        this.parent = parent;
    }

    public void save() {

    }

    public Tile getPos(Point pos) {
        return null;
    }

    public void setPos(Point pos, Tile elem) {

    }

    public Tile getAtPos(Point pos) {
        return null;
    }
}
