package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteHandler;
import org.json.simple.JSONObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity implements Renderable, Updatable{
    protected Point location;
    private SpriteSheet spriteSheet;
    public int moveSpeed;

    private int level;
    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5;
    protected float[][] stats;

    public Entity(SpriteHandler spriteHandler, String name){
        stats = new float[MODCOUNT][STATCOUNT];
        this.spriteSheet = spriteHandler.get(name);
        this.location = new Point(0,0);
    }

    public void update(){
        for(int i = 0; i<STATCOUNT; i++)
            stats[CURRENT][i] = stats[BASE][i]+stats[SCALING][i]*level+stats[ITEM][i]+stats[SKILL][i]+stats[BUFF][i];

    }

    public void render(Graphics graphics, Point offset) {
        //drawing method

    }
    public abstract void move(int dir);

}
