package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;

/**
 * Created by Matt on 27/08/2016.
 */
public class Bow extends RangedWeapon {

    public Bow(int rarityMod, int value, String name, Float fireRate, SpriteSheetData spriteSheet, GameState gameState) {
        super(rarityMod, value, name, fireRate, spriteSheet, gameState);
    }

    @Override
    public void fire(Point origin, double direction) {

    }
}
