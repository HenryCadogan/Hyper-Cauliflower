package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;

import org.newdawn.slick.Graphics;

/**
 * Created by Experiment Account on 04/09/2016.
 */
public class Zombie extends Mob {

    public Zombie(SpriteSheetData spriteSheetData, String name, Point location, int health) {
        super(spriteSheetData, name, location, health);
    }

    public void update(GameState game){
        super.update(game);
    }

    public void render(Graphics graphics, Point offset){
        super.render(graphics,offset);
    }

}
