package world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import states.GameState;
import states.Renderable;
import states.Updatable;

/**
 * Created by Matt on 24/06/2016.
 */
public class RenderingWorld implements Renderable, Updatable{


    static final int CHUNK_WIDTH = 2; //number of tiles contained by a chunk
    //maintains and draws the visible and soon-to-be visible tiles

    public RenderingWorld(){

    }

    public void render(Graphics graphics, Point offset) {

    }

    public void update(GameState game) {

    }

    private void generateElement(Chunk parent, Point pos){}
}
