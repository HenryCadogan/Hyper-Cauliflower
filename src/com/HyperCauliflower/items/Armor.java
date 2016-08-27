package com.HyperCauliflower.items;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.items.Item;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public class Armor extends Item {
    enum Slot {
        HEAD,
        TORSO,
        LEGS,
        NECK,
        RING,
        BOOTS
    }
    private Slot slot;
    private String type;
    private int armorRating;

    public Armor(int rarityModifier, int value,String type) {
        super(rarityModifier, value);
        this.type = type;

    }

    public String getType(){ return this.type;}

    public int getArmorRating(){
        return this.armorRating;
    }
}
