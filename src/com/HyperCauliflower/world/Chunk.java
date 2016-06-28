package com.HyperCauliflower.world;

import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

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
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                tiles[i][j].render(graphics,new Point(offset.getX()+(i<<Tile.TILE_SHIFT),offset.getY()+(j<<Tile.TILE_SHIFT)));
            }
        }
        graphics.setColor(Color.red);
        graphics.drawRect(offset.getX(),offset.getY(),CHUNK_WIDTH<<Tile.TILE_SHIFT,CHUNK_WIDTH<<Tile.TILE_SHIFT);
    }

    Point getLocation(){
        return location;
    }
}
