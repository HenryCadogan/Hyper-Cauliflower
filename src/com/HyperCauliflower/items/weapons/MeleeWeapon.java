package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.states.Point;

/**
 * Created by Henry on 26/07/2016.
 */
public class MeleeWeapon extends Weapon implements Fireable{
    private int reach;
    public MeleeWeapon(int rarityMod, int value,String name,Float fireRate,int reach) {
        super(rarityMod, value,name,fireRate);
        this.reach = reach;
    }

    @Override
    public void fire(Point origin, double direction) {

    }
}
