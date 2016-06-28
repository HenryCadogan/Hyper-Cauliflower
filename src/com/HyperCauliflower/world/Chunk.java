package com.HyperCauliflower.world;

import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import static com.HyperCauliflower.world.Tile.TILE_SHIFT;

/**
 * Created by Matt on 25/06/2016.
 */
public abstract class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 6, CHUNK_WIDTH = 1<<CHUNK_SHIFT, CHUNK_ABS_WIDTH = CHUNK_WIDTH<<TILE_SHIFT;

    protected Tile[][] tiles;
    private Point location;

    protected Chunk(Point location, Tile[][] tiles){
        this.tiles = tiles;
        this.location = location;
    }


    static Chunk makeChunk(Point location, Perlin noiseGen, SpriteSheet spriteSheet, TileHandler tileHandler, boolean town){
        Tile[][] r = new Tile[CHUNK_WIDTH][CHUNK_WIDTH];
        for(int i = 0;i<CHUNK_WIDTH;i++){
            for(int j = 0;j<CHUNK_WIDTH;j++){
                double tileVal = noiseGen.getValue((double)(location.getX()*CHUNK_WIDTH+i)/1000,(double)(location.getY()*CHUNK_WIDTH+j)/1000,0);
                if (tileVal>0.6 || (tileVal>0.075 && tileVal < 0.1))
                    r[i][j] = new WaterTile(new Point(i,j),spriteSheet, tileHandler.get("water"));
                else if(tileVal > 0.50 || tileVal <-0.8)
                    r[i][j] = new BasicTile(new Point(i,j),spriteSheet, tileHandler.get("sand"));
                else
                    r[i][j] = new BasicTile(new Point(i,j), spriteSheet, tileHandler.get("grass"));
            }
        }
        boolean liquid = false;
        for(Tile[] ta:r)
            for(Tile t:ta)
                liquid = liquid || t.isLiquid();
        if(!liquid)
            return new TownChunk(location, r, spriteSheet, tileHandler);
        else
            return new BasicChunk(location, r);
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
