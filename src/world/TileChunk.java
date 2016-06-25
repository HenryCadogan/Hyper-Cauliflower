package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public class TileChunk extends Chunk<Tile> {

    private Chunk<TileChunk> parent;
    private Point location;


    TileChunk(ChunkChunk parent, Point location){
        super(parent, location);
        elems = new Tile[RenderingWorld.CHUNK_WIDTH][RenderingWorld.CHUNK_WIDTH];
    }

    void save() {

    }
}
