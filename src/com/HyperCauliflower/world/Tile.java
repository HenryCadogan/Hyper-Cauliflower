package com.HyperCauliflower.world;

import com.HyperCauliflower.entities.Entity;
import com.HyperCauliflower.handlers.SpriteHandler;
import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Matt on 24/06/2016.
 */
abstract class Tile implements Renderable{

    static final int TILE_SHIFT = 5,TILE_WIDTH = 1<<TILE_SHIFT; //cheeky temp 32

    private SpriteSheet spriteSheet;
    private Point location, image;
    Tile(Point location, SpriteHandler spriteHandler){
        this.location = location;
        image = setImage();
        spriteSheet = spriteHandler.get("tiles");
    }

    protected abstract Point setImage();

    public boolean isPassable(){
        //walls etc non passable
        return true;
    }

    public void render(Graphics graphics, Point offset){
        if(new Rectangle(-TILE_WIDTH,-TILE_WIDTH, Main.INTERNAL_WIDTH+2*TILE_WIDTH, Main.INTERNAL_HEIGHT+2*TILE_WIDTH).contains(offset)) {
            graphics.drawImage(spriteSheet.getSprite((int)image.getX(),(int)image.getY()),offset.getX(),offset.getY());
        }
    }

    public void onSteppedOn(Entity entity){
        //override for effects when player walks on
    }


}
