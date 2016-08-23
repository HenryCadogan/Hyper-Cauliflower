package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.items.Projectiles.Projectile;
import com.HyperCauliflower.states.Point;

/**
 * Created by Henry on 21/07/2016.
 */
public class BowWeapon extends Weapon {

    private int damage;


    public BowWeapon(int rarityMod, int value, String name,Float fireRate) {
        super(rarityMod, value,name,fireRate);
        //todo generate weapon values from other aspects
    }

    private void generateWeapon(boolean random){
        if (random){
            //generate random weapon here
        }else{
            //do stuff
        }
    }

    public void fire(Point position,Point target){
        //create arrow
        //add arrow to update and render methods
    }


    private class Arrow extends Projectile{

        public Arrow(Point target, Point pos, int speed) {
            super(target, pos, speed);
        }

    }

}
