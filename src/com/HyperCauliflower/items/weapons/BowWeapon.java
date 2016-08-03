package com.HyperCauliflower.items.weapons;

/**
 * Created by Henry on 21/07/2016.
 */
public class BowWeapon extends Weapon {


    public BowWeapon(int rarityMod, int value, String name) {
        super(rarityMod, value,name);
        //todo generate weapon values from other aspects
    }

    private void generateWeapon(boolean random){
        if (random){
            //generate random weapon here
        }else{
            //do stuff
        }

    }

    public void fire(){

    }

}
