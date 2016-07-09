package com.HyperCauliflower.world;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public class RenderingWorld implements Renderable, Updatable{


    //maintains and draws the visible and soon-to-be visible tiles

    private int seed,u,v;
    private static final int BUFFER = 10, SCREEN_WIDTH = (Main.INTERNAL_WIDTH>>Chunk.CHUNK_SHIFT), STORED_WIDTH = BUFFER+SCREEN_WIDTH, SCREEN_HEIGHT = (Main.INTERNAL_HEIGHT>>Chunk.CHUNK_SHIFT), STORED_HEIGHT = BUFFER + SCREEN_HEIGHT;
    //private static final int SCREEN_WIDTH = 2, SCREEN_HEIGHT = 2, STORED_WIDTH = 8, STORED_HEIGHT = 8;
    private Chunk[][] chunksLoaded;
    private Perlin noiseGen;

    public RenderingWorld(int seed){
        noiseGen = new Perlin();
        noiseGen.setOctaveCount(6);
        noiseGen.setSeed(seed);
        u=v=0;
        chunksLoaded = new Chunk[STORED_WIDTH][STORED_HEIGHT];
        for(int i = 0;i<STORED_WIDTH;i++) {
            for (int j = 0; j < STORED_HEIGHT; j++) {
                chunksLoaded[i][j] = generateChunk(i,j);
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        for(Chunk[] ca:chunksLoaded)
            for(Chunk c:ca)
                c.render(graphics,offset);
    }

    private Point getTL(){
        return chunksLoaded[u][v].getLocation();
    }

    private Point getBR(){
        return chunksLoaded[adjustValue(u-1,STORED_WIDTH)][adjustValue(v-1,STORED_HEIGHT)].getLocation();
    }
    public void update(GameState game) {
        Point cameraPosition = new Point((int)game.getCameraPosition().getX()/Chunk.CHUNK_WIDTH,(int)game.getCameraPosition().getY()/Chunk.CHUNK_WIDTH);
        if(cameraPosition.getX()-getTL().getX()< (SCREEN_WIDTH/2)+2){
            Point tl = getTL();
            u = adjustValue(u-1,STORED_WIDTH);
            updateColumn((int)tl.getX()-1,(int)tl.getY());
        }else if (getBR().getX()-cameraPosition.getX()< (SCREEN_WIDTH/2)+1) {
            Point tl = getTL();
            Point br = getBR();
            updateColumn((int) br.getX() + 1, (int) tl.getY());
            u = adjustValue(u + 1, STORED_WIDTH);
        }
        if(cameraPosition.getY()-getTL().getY()< (SCREEN_HEIGHT/2)+2){
            Point tl = getTL();
            v = adjustValue(v-1,STORED_HEIGHT);
            updateRow((int)tl.getY() - 1,(int)tl.getX());
        }else if (getBR().getY()-cameraPosition.getY()< (SCREEN_HEIGHT/2)+1){
            Point br = getBR();
            Point tl = getTL();
            updateRow((int) br.getY() + 1,(int)tl.getX());
            v = adjustValue(v+1,STORED_HEIGHT);
        }
    }
    private void updateRow(int mod,int x){
        int i = u;
        do{
            chunksLoaded[i][v] = generateChunk(x++,mod);
            i = adjustValue(i+1,STORED_WIDTH);
        }while(i != u);
    }

    private void updateColumn(int mod,int y){
        int j = v;
        do{
            chunksLoaded[u][j] = generateChunk(mod,y++);
            j = adjustValue(j+1,STORED_HEIGHT);
        }while(j != v);
    }

    private Chunk generateChunk(int x, int y){
        return new Chunk(new Point(x, y),noiseGen);
    }

    private int adjustValue(int val, int comp){
        if(val==comp)return 0;
        else if(val==-1)return comp-1;
        else return val;
    }
}
