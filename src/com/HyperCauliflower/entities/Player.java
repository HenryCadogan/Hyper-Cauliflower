package com.HyperCauliflower.entities;
import com.HyperCauliflower.handlers.SpriteSheetHandler;

import com.HyperCauliflower.states.GameState;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;


/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity{

    private float movementModifier = 1;
    private SpriteHandler spriteHandler;
    private SpriteData spriteData;
    //load in all values from json to avoid further reads thus being more efficient
    private int row,width,height,startFrame,endFrame = 0;

    public Player(SpriteSheetHandler spriteSheetHandler, String name) {
        super(spriteSheetHandler,name);
        this.movementModifier =1;
        this.moveSpeed = 1;
        this.spriteHandler = new SpriteHandler();
        this.spriteData = spriteHandler.get("player");
        this.row = spriteData.getRow();
        this.width = spriteData.getWidth();
        this.height = spriteData.getHeight();
        this.startFrame = spriteData.getStartFrame();
        this.endFrame = spriteData.getEndFrame();
    }

    public void move(int dir){
        if (dir == 0){
            this.location.setY(this.location.getY() - (this.moveSpeed * movementModifier));
        }
        if (dir == 1){
            this.location.setX(this.location.getX() + (this.moveSpeed * movementModifier));
        }
        if (dir == 2){
            this.location.setY(this.location.getY() + (this.moveSpeed * movementModifier));
        }
        if (dir == 3){
            this.location.setX(this.location.getX() - (this.moveSpeed * movementModifier));
        }

    }

    private Image getImage(int animFrame){
        return getSpriteSheet().getSubImage(animFrame,row,this.width,this.height);
    }

    public void setPlayerMoveSpeed(int speed){
        this.moveSpeed = speed;
    }

    public void setPlayerSpeedModifier(int mod){
        this.movementModifier = mod;
    }

    public Point getLocation(){
        return this.location;
    }

    public void update(GameState game) {

    }

    public void render(Graphics graphics, Point offset){
        graphics.drawImage(getImage(getAnimationFrame()),this.location.getCenterX() + offset.getCenterX(),this.location.getCenterY() + offset.getCenterY());
    }

    public int getAnimationFrame() {
        //todo work out animation rates
        return 1;
    }
}



