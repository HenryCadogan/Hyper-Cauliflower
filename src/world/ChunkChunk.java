package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
class ChunkChunk implements Chunk<Chunk> {

    private Chunk[][] elems;
    private Chunk parent;


    ChunkChunk(Chunk parent){
        elems = new Chunk[RenderingWorld.CHUNK_WIDTH][RenderingWorld.CHUNK_WIDTH];
        this.parent = parent;
    }

    public void save() {

    }

    public Chunk getPos(Point pos) {
        return null;
    }

    public void setPos(Point pos, Chunk elem) {

    }

    public Chunk getAtPos(Point pos) {
        return null;
    }
}
