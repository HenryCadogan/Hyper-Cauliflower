package com.HyperCauliflower.world;

import com.HyperCauliflower.entities.Entity;
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
    private Point location;
    private int spriteStart, row, framesToAdvance;

    Tile(Point location, SpriteSheet spriteSheet, TileData tileData){
        this.spriteStart = tileData.getStartFrame();
        this.row = tileData.getRow();
        framesToAdvance = RenderingWorld.WORLD_FRAME_MAX/(tileData.getEndFrame() - spriteStart + 1);
        this.location = location;
        this.spriteSheet = spriteSheet;
    }

    public boolean isPassable(){
        //walls etc non passable
        return true;
    }

    public void render(Graphics graphics, Point offset){
        if(new Rectangle(-TILE_WIDTH,-TILE_WIDTH, Main.INTERNAL_WIDTH+2*TILE_WIDTH, Main.INTERNAL_HEIGHT+2*TILE_WIDTH).contains(offset)) {
            graphics.drawImage(spriteSheet.getSprite(spriteStart+RenderingWorld.getWorldFrame()/framesToAdvance,row),offset.getX(),offset.getY());
        }
    }

    public void onSteppedOn(Entity entity){
        //override for effects when player walks on
    }


}
