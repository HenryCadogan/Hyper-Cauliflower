package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.items.Inventory;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity {

    private Point mousePos = new Point(0, 0), mouseAbsPos = new Point(0, 0);
    public ConfigurableEmitter footsteps;
    private Inventory inventory;
    private final int MOVE_MODE = 0;
    private int fireUpdateCount;
    ArrayList<Sound> hurtSounds;

    public Player(SpriteSheetData spriteSheetData, String name, Point location, int health) {
        super(spriteSheetData, name, location, health);
        setMoveSpeed(5);
        this.experiencePoints = 0;
        this.inventory = new Inventory();
        hurtSounds = new ArrayList<>();
        getSounds();

        try {
            footsteps = ParticleIO.loadEmitter("/res/sprites/Particles/footsteps.xml");
            footsteps.setEnabled(true);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    private void getSounds() {

        try {
            File[] sounds = new File("res/Sounds/Entities/Hurts/").listFiles();
            for (File sound : sounds) {
                System.out.println(sound.getPath());
                hurtSounds.add(new Sound(sound.getPath()));
            }
        } catch (SlickException e) {
            e.printStackTrace();

        }
        // fancy way that I haven't even checked if it works or not
    /*/
        try {
            Files.walk(Paths.get("res/Sounds/Entities/Hurts/"))
                    .forEach(filePath -> {
                        if (!Files.isDirectory(filePath)) {
                            try {
                                System.out.println(filePath.toString());
                                hurtSounds.add(new Sound(filePath.toString()));
                            }catch(SlickException e){
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();

        }
/*/
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
        // making sure we don't overflow if the game is left running
        if (fireUpdateCount >= 25565) {
            fireUpdateCount = 0;
        } else {
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
        // render the weapon image on top
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
                //System.out.println("fired at: " + mousePos.getX() + "," + mousePos.getY());
            }

        }
    }

    public void takeDamage(int damageValue) {

        if (super.canTakeDamage()) {
            super.takeDamage(damageValue);
            int randomSoundNo = ThreadLocalRandom.current().nextInt(0, hurtSounds.size() - 1);
            System.out.println(hurtSounds.get(randomSoundNo).toString());
            hurtSounds.get(randomSoundNo).play(1.0F, 0.5F);
        }
    }

}




