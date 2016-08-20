package com.HyperCauliflower.items.armor;

import com.HyperCauliflower.entities.Player;
import com.HyperCauliflower.items.Item;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class Armor extends Item {
    private String type;
    private int armorRating;

    public Armor(int rarityModifier, int value,String type) {
        super(rarityModifier, value);
        this.type = type;

    }

    @Override
    public void render(Graphics graphics, Point offset) {

    }

    public String getType(){ return this.type;}

    private void generateArmorRating(Player player){

    }
}
