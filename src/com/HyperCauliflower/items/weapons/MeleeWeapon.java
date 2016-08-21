package com.HyperCauliflower.items.weapons;

/**
 * Created by Henry on 26/07/2016.
 */
public class MeleeWeapon extends Weapon implements Fireable{
    public MeleeWeapon(int rarityMod, int value,String name,Float fireRate) {
        super(rarityMod, value,name,fireRate);
    }

    public void fire(){

    }

}
