package com.HyperCauliflower.items.weapons;

/**
 * Created by Henry on 26/07/2016.
 */
public class MeleeWeapon extends Weapon implements Fireable{
    private int reach;
    public MeleeWeapon(int rarityMod, int value,String name,Float fireRate,int reach) {
        super(rarityMod, value,name,fireRate);
        this.reach = reach;
    }

}
