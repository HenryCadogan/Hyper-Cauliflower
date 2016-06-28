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
        this.movementModifier =1;
        this.moveSpeed = 1;

    }

    public void move(int dir){
        if (dir == 0){
            this.location.setY(this.location.getY() - (this.moveSpeed * movementModifier));
        }
        if (dir == 1){
            this.location.setX(this.location.getX() + (this.moveSpeed * movementModifier));
        }
        if (dir == 2){
            this.location.setY(this.location.getY() + (this.moveSpeed * movementModifier));
        }
        if (dir == 3){
            this.location.setX(this.location.getX() - (this.moveSpeed * movementModifier));
        }

    }

    public void setPlayerMoveSpeed(int speed){
        this.moveSpeed = speed;
    }

    public void setPlayerSpeedModifier(int mod){
        this.movementModifier = mod;
    }

    public void update(GameState game) {


    }
}
