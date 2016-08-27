package com.HyperCauliflower.items;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class Weapon extends Item implements Useable {

    private String name;
    private int damage;
    private int reqLvl;
    private float fireRate;
    private GameState gameState;

    public Weapon(int rarityMod,int value,String name,float fireRate, GameState gameState) {
        super(rarityMod, value);
        this.fireRate = fireRate;
        this.gameState = gameState;
    }

    public float getFireRate(){

        return this.fireRate;
    }

    protected GameState getGameState(){return gameState;}
}
