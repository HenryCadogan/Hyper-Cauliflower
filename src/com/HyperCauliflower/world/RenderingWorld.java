package com.HyperCauliflower.world;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public class RenderingWorld implements Renderable, Updatable{


    //maintains and draws the visible and soon-to-be visible tiles

    private int seed,u,v;
    //private static final int STORED_WIDTH = 5+(Main.INTERNAL_WIDTH>>Chunk.CHUNK_SHIFT>>Tile.TILE_SHIFT), STORED_HEIGHT = 5+(Main.INTERNAL_HEIGHT>>Chunk.CHUNK_SHIFT>>Tile.TILE_SHIFT);
    private static final int STORED_WIDTH = 3, STORED_HEIGHT = 2;
    private Chunk[][] chunksLoaded;

    public RenderingWorld(int seed){
        this.seed = seed;
        u=v=0;
        chunksLoaded = new Chunk[STORED_WIDTH][STORED_HEIGHT];
        for(int i = 0;i<STORED_WIDTH;i++) {
            for (int j = 0; j < STORED_HEIGHT; j++) {
                chunksLoaded[i][j] = generateChunk(i,j);
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        int i = u;
        do{
            int j = v;
            do{
                chunksLoaded[i][j].render(graphics,new Point(offset.getX()+(i<<Chunk.CHUNK_SHIFT<<Tile.TILE_SHIFT),offset.getY()+(j<<Chunk.CHUNK_SHIFT<<Tile.TILE_SHIFT)));
                j = adjustValue(j+1, STORED_HEIGHT);
            }while(j!=v);
            i = adjustValue(i+1, STORED_WIDTH);
        }while(i!=u);
    }

    public void update(GameState game) {
        Point cameraPosition = new Point((int)game.getCameraPosition().getX()<<Chunk.CHUNK_SHIFT,(int)game.getCameraPosition().getY()<<Chunk.CHUNK_SHIFT);
        Point tl = chunksLoaded[u][v].getLocation();
        Point br = chunksLoaded[adjustValue(u-1,STORED_WIDTH)][adjustValue(v-1,STORED_HEIGHT)].getLocation();
        if(cameraPosition.getX()<=tl.getX()+1){
            u = adjustValue(u-1,STORED_WIDTH);
            updateColumn((int)tl.getX()-1,(int)tl.getY());
        }else if (br.getX()-cameraPosition.getX()<STORED_WIDTH-2){
            u = adjustValue(u+1,STORED_WIDTH);
            updateColumn((int)br.getX()+1,(int)tl.getY());
        }
        if(cameraPosition.getY()<=tl.getY()){
            v = adjustValue(v-1,STORED_HEIGHT);
            updateRow((int)tl.getY() - 1,(int)tl.getX());
        }else if (br.getY()-cameraPosition.getY()<STORED_HEIGHT+1){
            v = adjustValue(v+1,STORED_HEIGHT);
            updateRow((int) br.getY() + 1,(int)tl.getX());
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
        return new Chunk(Chunk.ChunkType.NORMAL, new Point(x, y));
    }

    private int adjustValue(int val, int comp){
        if(val==comp)return 0;
        else if(val==-1)return comp-1;
        else return val;
    }

    private Tile getTileAt(Point location){
        return null;
    }

    /*Todo
    LIST OF THINGS TO DO
    Create the 2D cyclic queue
    Make a new child when empty being accessed
    Think of a generating algorithm

    I THINK THIS CREATES A VARIABLE ORIGIN, CREATE FIX IF NECESSARY
    Get a global coordinate producer

     */
}
