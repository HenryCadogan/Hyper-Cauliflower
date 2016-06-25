package world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import states.Renderable;

/**
 * Created by Matt on 25/06/2016.
 */
public class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 6, CHUNK_WIDTH = 1<<CHUNK_SHIFT;

    enum ChunkType{NORMAL}
    private ChunkType chunkType;
    private Tile[][] tiles;

    Chunk(ChunkType chunkType){
        this.chunkType = chunkType;
        tiles = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                tiles[i][j] = new PlainTile();
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                tiles[i][j].render(graphics,new Point(offset.getX()+(i<<Tile.TILE_SHIFT),offset.getY()+(j<<Tile.TILE_SHIFT)));
            }
        }
    }
}
