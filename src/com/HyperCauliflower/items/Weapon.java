package com.HyperCauliflower.items;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.json.simple.JSONObject;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 21/07/2016.
 */
public abstract class Weapon extends Item implements Useable {

    private int damage;
    private int reqLvl;
    private int fireRate;
    private GameState gameState;

    public Weapon(int rarityMod,int value,String name,int fireRate, GameState gameState) {
        super(rarityMod, value, name);
        this.fireRate = fireRate;
        this.gameState = gameState;
    }

    public int getFireRate(){

        return this.fireRate;
    }

    protected GameState getGameState(){return gameState;}
}
