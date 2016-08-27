package com.HyperCauliflower.items.weapons;


import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Image;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class RangedWeapon<P> extends Weapon {

    private int damage;
    private Image projectileSprite;
    private GameState gameState;

    public RangedWeapon(int rarityMod, int value, String name, Float fireRate, SpriteSheetData spriteSheet, GameState gameState) {
        super(rarityMod, value,name,fireRate);
        projectileSprite = spriteSheet.getImage("arrow",0);
        this.gameState = gameState;
        //todo generate weapon values from other aspects
    }

    private void generateWeapon(boolean random){
        if (random){
            //generate random weapon here
        }else{
            //do stuff
        }
    }

}
