package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.states.Point;

/**
 * Created by Henry on 21/07/2016.
 */
public class BowWeapon extends Weapon {


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

    public void fire(Point direction){

    }

}
