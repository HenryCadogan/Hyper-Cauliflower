package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;

import java.io.IOException;

/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity {

    private float movementModifier;
    //load in all values from json to avoid further reads thus being more efficient
    private Point mousePos = new Point(0,0);
    private Point playerAbsPos;
    private int experience;
    private double angleToTurn;
    public ConfigurableEmitter footsteps;

    public Player(SpriteSheetData spriteSheetData, String name, Point location) {
        super(spriteSheetData, name, location);
        this.movementModifier = 1;
        this.moveSpeed = 1;
        this.experience = 0;
        try {
            footsteps = ParticleIO.loadEmitter("/res/sprites/Particles/footsteps.xml");

        }catch(IOException e){
            e.printStackTrace();
            System.exit(3);
        }
    }


    public void move(int dir) {
        enableFootsteps();
        if (dir == 0) {
            this.location.setY(this.location.getCenterY() - (this.moveSpeed * movementModifier));
        }
        if (dir == 1) {
            this.location.setX(this.location.getCenterX() + (this.moveSpeed * movementModifier));
        }
        if (dir == 2) {
            this.location.setY(this.location.getCenterY() + (this.moveSpeed * movementModifier));
        }
        if (dir == 3) {
            this.location.setX(this.location.getCenterX() - (this.moveSpeed * movementModifier));
        }
    }

    public void setPlayerMoveSpeed(int speed) {
        this.moveSpeed = speed;
    }

    private void setPlayerSpeedModifier(int mod) {
        this.movementModifier = mod;
    }

    public Point getLocation() {
        return this.location;
    }

    public void update(GameState game) {
        this.mousePos = game.getMousePosition();
        footsteps.update(game.pSystem,game.getDelta());
    }

    public void render(Graphics graphics, Point offset) {
        rotatePlayer(graphics, offset);
        footsteps.setPosition(this.getLocation().getX() + this.getWidth() / 2, this.getLocation().getY() + this.getHeight() / 2);
    }

    public int getAnimationFrame() {
        //todo work out animation rates
        return 0;
    }

    private void rotatePlayer(Graphics g, Point p) {
        g.pushTransform();
        angleToTurn = Math.atan2(this.mousePos.getY() - (this.getLocation().getY() + p.getCenterY() + this.getHeight() / 2), this.mousePos.getX() - ((this.getLocation().getX() + p.getCenterX() + this.getHeight() / 2)));
        g.rotate(this.getLocation().getX() + p.getX() + this.getWidth() / 2, this.getLocation().getY() + p.getY() + this.getHeight() / 2, (float) Math.toDegrees(this.angleToTurn));
        g.drawImage(getImage(getAnimationFrame()), this.getLocation().getX() + p.getX(), this.getLocation().getY() + p.getY());
        g.popTransform();
    }


    public void slow(int slowMod, int duration) {
        setPlayerSpeedModifier(this.moveSpeed * slowMod);
    }

    public void enableFootsteps(){
        footsteps.setEnabled(true);
    }

    public void disableFootsteps(){
        footsteps.setEnabled(false);
    }
}



