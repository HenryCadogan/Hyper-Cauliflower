package com.HyperCauliflower.entities;
import com.HyperCauliflower.handlers.SpriteSheetHandler;

import com.HyperCauliflower.states.GameState;
import org.newdawn.slick.*;

import org.newdawn.slick.geom.Point;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import java.io.File;


/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity{

    private float movementModifier;
    private SpriteHandler spriteHandler;
    private SpriteData spriteData;
    //load in all values from json to avoid further reads thus being more efficient
    private int row,width,height,startFrame,endFrame,mX,mY = 0;
    private int experience;
    private Input containerInput;
    private double angleToTurn;
    private ParticleSystem pSystem;
    private ConfigurableEmitter emitter;





    public Player(SpriteSheetHandler spriteSheetHandler, String name,Input containerInput,Point location) {
        super(spriteSheetHandler,name,location);
        this.movementModifier =1;
        this.moveSpeed = 1;
        this.spriteHandler = new SpriteHandler();
        this.spriteData = spriteHandler.get("player");
        this.row = spriteData.getRow();
        this.width = spriteData.getWidth();
        this.height = spriteData.getHeight();
        this.startFrame = spriteData.getStartFrame();
        this.endFrame = spriteData.getEndFrame();
        this.experience =0;
        this.containerInput = containerInput;
        Animation walking = new Animation(this.getSpriteSheet(),4);
        walking.setLooping(true);

        //test particle code
        try {
            Image particle = this.getImage(this.getAnimationFrame());
            this.pSystem = new ParticleSystem(particle, 1400);
            File particleXml = new File("res/sprites/Particles/footsteps.xml");
            emitter = ParticleIO.loadEmitter(particleXml);
            emitter.setEnabled(true);
            pSystem.addEmitter(this.emitter);
            pSystem.setVisible(true);

        }catch (Exception e){
            e.printStackTrace();
            System.exit(2);
        }
        pSystem.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
    }



    public void move(int dir){

        if (dir == 0){
            this.location.setY(this.location.getCenterY() - (this.moveSpeed * movementModifier));
        }
        if (dir == 1){
            this.location.setX(this.location.getCenterX() + (this.moveSpeed * movementModifier));
        }
        if (dir == 2){
            this.location.setY(this.location.getCenterY() + (this.moveSpeed * movementModifier));
        }
        if (dir == 3){
            this.location.setX(this.location.getCenterX() - (this.moveSpeed * movementModifier));
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
        this.mX = this.containerInput.getMouseX();
        this.mY = this.containerInput.getMouseY();
        pSystem.update(60);
    }

    public void render(Graphics graphics, Point offset){
        float centerY = this.getLocation().getY() - this.height/2;
        float centerX = this.getLocation().getX() - this.width/2;
        graphics.pushTransform();
        angleToTurn = Math.atan2(this.mY - (centerY + offset.getCenterY() + this.height/2), this.mX - ((centerX +offset.getCenterX()+ this.height/2)));
        graphics.rotate(centerX + offset.getCenterX() + this.width/2,centerY + offset.getCenterY()+ this.height/2,(float)Math.toDegrees(this.angleToTurn));
        graphics.drawImage(getImage(getAnimationFrame()),centerX + offset.getCenterX(),centerY + offset.getCenterY());
        graphics.popTransform();
        emitter.setPosition(0,0);
        pSystem.setPosition(centerX +offset.getCenterX()+ this.height/2,centerY + offset.getCenterY() + this.height/2);
        pSystem.render();
    }

    public int getAnimationFrame() {
        //todo work out animation rates
        return 1;
    }

    public void slow(int slowMod, int duration){
        this.movementModifier = slowMod;
    }
}



