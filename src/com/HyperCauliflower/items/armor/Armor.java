package com.HyperCauliflower.items.armor;

import com.HyperCauliflower.items.Item;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class Armor extends Item {

    public Armor(int rarityModifier, int value) {
        super(rarityModifier, value);
    }

    @Override
    public void render(Graphics graphics, Point offset) {

    }
}
