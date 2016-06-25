package world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import states.GameState;
import states.Main;
import states.Renderable;
import states.Updatable;

/**
 * Created by Matt on 24/06/2016.
 */
public class RenderingWorld implements Renderable, Updatable{


    //maintains and draws the visible and soon-to-be visible tiles

    private int seed,u,v;
    private static final int STORED_WIDTH = 2+(Main.INTERNAL_WIDTH>>Chunk.CHUNK_SHIFT), STORED_HEIGHT = 2+(Main.INTERNAL_HEIGHT>>Chunk.CHUNK_SHIFT);
    private Point location;
    private Chunk[][] chunksLoaded;

    public RenderingWorld(int seed, Point location){
        this.seed = seed;
        this.location = location;
        chunksLoaded = new Chunk[STORED_WIDTH][STORED_HEIGHT];
        for(int i = 0;i<STORED_WIDTH;i++){
            for(int j = 0;j<STORED_HEIGHT;j++){
                chunksLoaded[i][j]= new Chunk(Chunk.ChunkType.NORMAL);
            }
        }

    }

    public void render(Graphics graphics, Point offset) {
        for(int i = 0;i<STORED_WIDTH;i++){
            for(int j = 0;j<STORED_HEIGHT;j++){
                chunksLoaded[i][j].render(graphics,new Point(offset.getX()+(i<<Chunk.CHUNK_SHIFT),offset.getY()+(j<<Chunk.CHUNK_SHIFT)));
            }
        }
    }

    public void update(GameState game) {

    }
    private Tile getTileAt(Point location){
        return null;
    }

    /*
    LIST OF THINGS TO DO
    Create the 2D cyclic queue
    Make a new child when empty being accessed
    Think of a generating algorithm

    I THINK THIS CREATES A VARIABLE ORIGIN, CREATE FIX IF NECESSARY
    Get a global coordinate producer



     */
}
