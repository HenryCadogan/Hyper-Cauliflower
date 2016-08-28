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
    private int armorRating;

    public Armor(int rarityModifier, int value, String name, String slot) {
        super(rarityModifier, value, name);
        this.slot = Slot.valueOf(slot);
    }

    public int getArmorRating(){
        return this.armorRating;
    }
}
