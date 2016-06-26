package com.HyperCauliflower.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity implements Renderable, Updatable{
    protected Point offset;
    private SpriteSheet spriteSheet;

    private int level;
    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5;
    protected float[][] stats;

    public Entity(SpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        stats = new float[MODCOUNT][STATCOUNT];
    }

    public void update(){
        for(int i = 0; i<STATCOUNT; i++)
            stats[CURRENT][i] = stats[BASE][i]+stats[SCALING][i]*level+stats[ITEM][i]+stats[SKILL][i]+stats[BUFF][i];

    }

    public void render(Graphics graphics, Point offset) {
        //drawing method

    }

}
