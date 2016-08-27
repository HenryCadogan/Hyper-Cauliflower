package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.items.Item;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class Weapon extends Item implements Fireable {

    private String name;
    private int damage;
    private int reqLvl;
    private float fireRate;

    public Weapon(int rarityMod,int value,String name,float fireRate){
        super(rarityMod,value);
        this.fireRate = fireRate;
    }


    @Override
    public void render(Graphics graphics, Point offset) {
        //todo render the weapon on top of the player
    }
    public abstract void fire(Point origin,double direction);

    public float getFireRate(){

        return this.fireRate;
    }
}
