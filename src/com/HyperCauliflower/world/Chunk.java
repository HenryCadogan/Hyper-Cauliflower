package com.HyperCauliflower.world;

import com.HyperCauliflower.handlers.SpriteHandler;
import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import static com.HyperCauliflower.world.Tile.TILE_SHIFT;

/**
 * Created by Matt on 25/06/2016.
 */
public class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 5, CHUNK_WIDTH = 1<<CHUNK_SHIFT, CHUNK_ABS_WIDTH = CHUNK_WIDTH<<TILE_SHIFT;

    enum ChunkType{NORMAL}
    private ChunkType chunkType;
    private Tile[][] tiles;
    private Point location;//chunk coords

    Chunk(ChunkType chunkType, Point location, Perlin noiseGen, SpriteHandler spriteHandler){
        this.location = location;
        this.chunkType = chunkType;
        tiles = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                if(noiseGen.getValue((double)(location.getX()*CHUNK_WIDTH+i)/1000,(double)(location.getY()*CHUNK_WIDTH+j)/1000,0)>0.1 && noiseGen.getValue((double)(location.getX()*CHUNK_WIDTH+i)/1000,(double)(location.getY()*CHUNK_WIDTH+j)/1000,0)<0.2) {
                    tiles[i][j] = new SandTile(new Point(i,j),spriteHandler);
                }else if(noiseGen.getValue((double)(location.getX()*CHUNK_WIDTH+i)/1000,(double)(location.getY()*CHUNK_WIDTH+j)/1000,0)<-0.5)
                    tiles[i][j] = new SandTile(new Point(i,j),spriteHandler);
                else if(noiseGen.getValue((double)(location.getX()*CHUNK_WIDTH+i)/1000,(double)(location.getY()*CHUNK_WIDTH+j)/1000,0)<0.1){
                    tiles[i][j] = new PlainTile(new Point(i,j),spriteHandler);
                }
                else{
                    tiles[i][j] = new WaterTile(new Point(i,j),spriteHandler);
                }
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        if(new Rectangle(-CHUNK_ABS_WIDTH,-CHUNK_ABS_WIDTH, Main.INTERNAL_WIDTH+(CHUNK_ABS_WIDTH<<2), Main.INTERNAL_HEIGHT+(CHUNK_ABS_WIDTH<<2)).contains(offset)) {
            for (int i = 0; i < CHUNK_WIDTH; i++) {
                for (int j = 0; j < CHUNK_WIDTH; j++) {
                    tiles[i][j].render(graphics, new Point(offset.getX() + (i << Tile.TILE_SHIFT), offset.getY() + (j << Tile.TILE_SHIFT)));
                }
            }
        }
        graphics.setColor(Color.red);
        graphics.drawRect(offset.getX(),offset.getY(),CHUNK_WIDTH<<Tile.TILE_SHIFT,CHUNK_WIDTH<<Tile.TILE_SHIFT);
    }

    Point getLocation(){
        return location;
    }
}
