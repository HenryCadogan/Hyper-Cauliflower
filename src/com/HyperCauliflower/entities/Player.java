package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.items.Inventory;
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

    private Point mousePos = new Point(0, 0), mouseAbsPos = new Point(0, 0);
    public ConfigurableEmitter footsteps;
    private Inventory inventory;
    private final int MOVE_MODE = 0;
    private int fireUpdateCount;


    public Player(SpriteSheetData spriteSheetData, String name, Point location, int health) {
        super(spriteSheetData, name, location, health);
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

    public Inventory getInventory() {
        return this.inventory;
    }

    public void move(int dir) {
        enableFootsteps();
        //todo make holding shift run but drain stamina
        //todo add in option for different movement modes
        if (MOVE_MODE == 0) {
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
        Point offset = new Point(0, 0).translate(game.getCameraPosition());
        offset.scale(-1);
        mouseAbsPos = mousePos.translate(offset);
        footsteps.update(game.pSystem, game.getDelta());
        updateFootstepsColor(game);
        // making sure we dont overflow if the game is left running
        if (fireUpdateCount >= 25565) {
            fireUpdateCount = 0;
        }else {
            fireUpdateCount++;
        }
    }

    private void updateFootstepsColor(GameState game) {
        ((ConfigurableEmitter.ColorRecord) footsteps.colors.get(0)).col = game.getColor(this.getLocation()).darker();
    }

    public void render(Graphics graphics, Point offset) {
        footsteps.setPosition(this.getLocation().getX(), this.getLocation().getY(), true);
        rotatePlayer(offset);
        super.render(graphics, offset);
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
        //not sure if this should be outer loop or not
        if (this.getInventory().getEquippedWeapon() == null) {
            System.out.println("fuck shit dammit");
        }

        if (fireUpdateCount > this.getInventory().getEquippedWeapon().getFireRate()) {
            fireUpdateCount = 0;
            if (this.inventory.getEquippedWeapon() != null) {
                this.inventory.getEquippedWeapon().use(this.getLocation(), facing);
                System.out.println("fired at: " + mousePos.getX() + "," + mousePos.getY());
            }

        }
    }
}




