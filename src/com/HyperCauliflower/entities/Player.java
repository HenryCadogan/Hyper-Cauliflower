package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.items.Inventory;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity {

    //load in all values from json to avoid further reads thus being more efficient
    private Point mousePos = new Point(0, 0);
    private Point playerAbsPos;
    public ConfigurableEmitter footsteps;
    private Inventory inventory;
    private final int MOVE_MODE = 0;

    public Player(SpriteSheetData spriteSheetData, String name, Point location) {
        super(spriteSheetData, name, location);
        setMoveSpeed(5);
        this.experiencePoints = 0;
        this.inventory = new Inventory();

        try {
            footsteps = ParticleIO.loadEmitter("/res/sprites/Particles/footsteps.xml");
            footsteps.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }


    public void move(int dir) {
        enableFootsteps();
        //todo make holding shift run but drain stamina

        if (MOVE_MODE == 1) {
            int shiftMod = 0;

            double direction = 0;
            if (dir == 0) {
                direction = facing;
            }
            if (dir == 1) {
                direction = (facing + Math.PI / 2);
            }
            if (dir == 2) {
                direction = (facing + Math.PI);
            }
            if (dir == 3) {
                direction = (facing - Math.PI / 2);
            }

            moveVector = moveVector.translate(new Point(Math.cos(direction), Math.sin(direction)));
        }
    }

    public void update(GameState game) {
        super.update(game);
        mousePos = game.getMousePosition();
        footsteps.update(game.pSystem, game.getDelta());

    }

    public void render(Graphics graphics, Point offset) {
        rotatePlayer(offset);
        super.render(graphics, offset);
        footsteps.setPosition(this.getLocation().getX(), this.getLocation().getY(), true);
    }

    public int getAnimationFrame() {
        //todo work out animation rates and sort actual animations out
        return 0;
    }

    private void rotatePlayer(Point p) {
        facing = Math.atan2(this.mousePos.getY() - (this.getLocation().getY() + p.getY()), this.mousePos.getX() - ((this.getLocation().getX() + p.getX())));
    }

    public void enableFootsteps() {
        footsteps.spawnCount.setMax(5);
        footsteps.spawnCount.setMin(5);
    }

    public void disableFootsteps() {
        footsteps.spawnCount.setMax(0);
        footsteps.spawnCount.setMin(0);
    }

    public void usePrimary() {
        if (this.inventory.getEquippedWeapon() != null) {
            this.inventory.getEquippedWeapon().fire(mousePos);

        } else {
            //print no primary weapon equipped
        }
    }
}



