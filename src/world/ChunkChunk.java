package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
class ChunkChunk extends Chunk<Chunk> {

    ChunkChunk(ChunkChunk parent, Point location){
        super(parent, location);
        elems = new Chunk[RenderingWorld.CHUNK_WIDTH][RenderingWorld.CHUNK_WIDTH];

    }

    public void save() {

    }
}
