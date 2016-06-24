package world;

import entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import states.Renderable;

/**
 * Created by Matt on 24/06/2016.
 */
abstract class Tile implements Renderable{

    static final int TILE_WIDTH = 32,NORTH = 0, EAST = 1,SOUTH = 2, LEFT = 3; //cheeky temp 32
    private Chunk parent;
    private SpriteSheet spriteSheet;

    public Tile(Chunk parent){
        this.parent = parent;
    }

    public boolean isPassable(){
        //walls etc non passable
        return true;
    }

    public Tile getAdjacentTile(int direction){
        return null;
    }

    public void render(Graphics graphics, Point offset){

    }

    public void onSteppedOn(Entity entity){
        //override for effects when player walks on
    }


}
