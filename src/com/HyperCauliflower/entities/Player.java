package com.HyperCauliflower.entities;
import com.HyperCauliflower.handlers.SpriteHandler;

import com.HyperCauliflower.states.GameState;


/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity{

    public float movementModifier = 1;


    public Player(SpriteHandler spriteHandler, String name) {
        super(spriteHandler,name);

    }

    public void move(int dir){
        if (dir == 0){
            this.location.setX(this.location.getX() + 1*movementModifier);
        }

    }

    public void update(GameState game) {


    }
}
