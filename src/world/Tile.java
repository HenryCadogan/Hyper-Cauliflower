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

    static final int TILE_WIDTH = 32; //cheeky temp 32

    private TileChunk parent;
    private SpriteSheet spriteSheet;
    private Point location;

    public Tile(TileChunk parent, Point location){
        this.parent = parent;
        this.location = location;
    }

    public boolean isPassable(){
        //walls etc non passable
        return true;
    }

    Tile getAdjacentTile(Point direction){
        Point adj = new Point(direction.getX()+location.getX(),direction.getY()+location.getY());
        if(adj.getX()!= 0 || adj.getX()!=1 || adj.getY() != 0 || adj.getY() != 1)
            return parent.getPos(adj);
        else
            return parent.getAdjacent(direction).getPos(new Point(adj.getX()%2,adj.getY()%2));
    }

    public void render(Graphics graphics, Point offset){

    }

    public void onSteppedOn(Entity entity){
        //override for effects when player walks on
    }


}
