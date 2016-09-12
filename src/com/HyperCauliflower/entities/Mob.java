package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public class Mob extends Entity{

    public Mob(SpriteSheetData spriteSheetData, String name, Point location, int health, int radius){
        super(spriteSheetData,name, location, health, radius);
        setMoveSpeed(4);
    }

    @Override
    public void update(GameState game) {
        moveVector = game.getClosestPlayerTo(this.getLocation()).getLocation().translate(this.getLocation().negated());
        facing = Math.atan(moveVector.getExactY()/moveVector.getExactX());
        super.update(game);
    }
}
