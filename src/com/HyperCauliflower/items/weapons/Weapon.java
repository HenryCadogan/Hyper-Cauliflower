package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.items.Item;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public class Weapon extends Item implements Fireable {
    private String name;

    public Weapon(int rarityMod,int value){
        super(rarityMod,value);
    }
    @Override
    public void render(Graphics graphics, Point offset) {

    }

    @Override
    public void fire() {

    }
}
