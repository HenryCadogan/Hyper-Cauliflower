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

    public Weapon(int rarityMod,int value,String name){
        super(rarityMod,value);
    }
    @Override
    public void render(Graphics graphics, Point offset) {

    }

    @Override
    public void fire(Point direction) {

    }
}
