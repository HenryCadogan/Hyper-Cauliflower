package com.HyperCauliflower.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import com.HyperCauliflower.states.Renderable;

/**
 * Created by Matt on 25/06/2016.
 */
public class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 1, CHUNK_WIDTH = 1<<CHUNK_SHIFT;

    enum ChunkType{NORMAL}
    private ChunkType chunkType;
    private Tile[][] tiles;
    private Point location;//chunk coords

    Chunk(ChunkType chunkType, Point location){
        this.location = location;
        this.chunkType = chunkType;
        tiles = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                tiles[i][j] = new PlainTile(new Point(i,j));
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        Point newOffset = new Point(offset.getX()+((int)location.getX()<<CHUNK_SHIFT<<Tile.TILE_SHIFT),offset.getY()+((int)location.getY()<<CHUNK_SHIFT<<Tile.TILE_SHIFT));
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                tiles[i][j].render(graphics,new Point(newOffset.getX()+(i<<Tile.TILE_SHIFT),newOffset.getY()+(j<<Tile.TILE_SHIFT)));
            }
        }
    }

    Point getLocation(){
        return location;
    }
}
