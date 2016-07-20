package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;

import java.io.IOException;

/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity {

    //load in all values from json to avoid further reads thus being more efficient
    private Point mousePos = new Point(0,0);
    private Point playerAbsPos;
    private int experience;
    public ConfigurableEmitter footsteps;

    public Player(SpriteSheetData spriteSheetData, String name, Point location) {
        super(spriteSheetData, name, location);
        setMoveSpeed(5);
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
        double direction = 0;
        if (dir == 0) {
            direction=facing;
        }
        if (dir == 1) {
            direction=(facing+Math.PI/2);
        }
        if (dir == 2) {
            direction = (facing+Math.PI);
        }
        if(dir == 3) {
            direction = (facing-Math.PI/2);
        }

        moveVector = moveVector.translate(new Point(Math.cos(direction), Math.sin(direction)));
    }

    public void update(GameState game) {
        super.update(game);
        mousePos = game.getMousePosition();
        footsteps.update(game.pSystem,game.getDelta());

    }

    public void render(Graphics graphics, Point offset) {
        rotatePlayer(offset);
        super.render(graphics, offset);
        footsteps.setPosition(this.getLocation().getX(), this.getLocation().getY());
    }

    public int getAnimationFrame() {
        //todo work out animation rates
        return 0;
    }

    private void rotatePlayer(Point p) {
        facing = Math.atan2(this.mousePos.getY() - (this.getLocation().getY() + p.getY()), this.mousePos.getX() - ((this.getLocation().getX() + p.getX())));
    }

    public void enableFootsteps(){
        footsteps.setEnabled(true);
    }
    public void disableFootsteps(){
        footsteps.resetState();
        footsteps.setEnabled(false);
    }
}



