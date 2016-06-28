package com.HyperCauliflower.world;

import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 28/06/2016.
 */
public class TownChunk extends Chunk {

    TownChunk(Point location, Perlin noiseGen, SpriteSheet spriteSheet, TileHandler tileHandler) {
        super(location, noiseGen, spriteSheet, tileHandler);
        for(int i = 0; i<CHUNK_WIDTH; i++){
            makeWall(i,0,spriteSheet,tileHandler);
            makeWall(i,CHUNK_WIDTH-1,spriteSheet,tileHandler);
            makeWall(0,i,spriteSheet,tileHandler);
            makeWall(CHUNK_WIDTH-1,i,spriteSheet,tileHandler);
        }
    }

    private void makeWall(int i, int j, SpriteSheet spriteSheet, TileHandler tileHandler){
        tiles[i][j] = new WallTile(new Point(i,j), spriteSheet, tileHandler.get("town wall"));
    }
}
