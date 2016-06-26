package world;

import entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import states.Renderable;

/**
 * Created by Matt on 24/06/2016.
 */
abstract class Tile implements Renderable{

    static final int TILE_SHIFT = 5,TILE_WIDTH = 1<<TILE_SHIFT; //cheeky temp 32

    private SpriteSheet spriteSheet;
    private Point location;

    public Tile(Point location){
        this.location = location;
    }

    public boolean isPassable(){
        //walls etc non passable
        return true;
    }

    public void render(Graphics graphics, Point offset){
        graphics.setColor(Color.green);
        graphics.fillRect(offset.getX(),offset.getY(),TILE_WIDTH,TILE_WIDTH);
        graphics.setColor(Color.cyan);
        graphics.drawRect(offset.getX(),offset.getY(),TILE_WIDTH,TILE_WIDTH);
    }

    public void onSteppedOn(Entity entity){
        //override for effects when player walks on
    }


}
