package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import java.io.File;


/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity{

    private float movementModifier;
    //load in all values from json to avoid further reads thus being more efficient
    private int mX,mY = 0;
    private int experience;
    private Input containerInput;
    private double angleToTurn;
    private ParticleSystem pSystem;
    private ConfigurableEmitter emitter;

    public Player(SpriteSheetData spriteSheetData, String name, Input containerInput, Point location) {
        super(spriteSheetData,name,location);
        this.movementModifier =1;
        this.moveSpeed = 1;
        this.experience =0;
        this.containerInput = containerInput;

        //test particle code
        try {
            Image particle = this.getImage(0);
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
        float centerY = this.getLocation().getY() - this.getHeight()/2;
        float centerX = this.getLocation().getX() - this.getWidth() /2;
        graphics.pushTransform();
        angleToTurn = Math.atan2(this.mY - (centerY + offset.getCenterY() + this.getHeight()/2), this.mX - ((centerX +offset.getCenterX()+ this.getHeight()/2)));
        graphics.rotate(centerX + offset.getCenterX() + this.getWidth() /2,centerY + offset.getCenterY()+ this.getHeight()/2,(float)Math.toDegrees(this.angleToTurn));
        graphics.drawImage(getImage(getAnimationFrame()),centerX + offset.getCenterX(),centerY + offset.getCenterY());//todo:: not sure dude, but I think this is wrong
        graphics.popTransform();
        emitter.setPosition(0,0);
        pSystem.setPosition(centerX +offset.getCenterX()+ this.getHeight()/2,centerY + offset.getCenterY() + this.getHeight()/2);
        pSystem.render();
    }

    public int getAnimationFrame() {
        //todo work out animation rates
        return 0;
    }

    public void slow(int slowMod, int duration){
        this.movementModifier = slowMod;
    }
}



