package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity implements Renderable, Updatable{
    protected Point location;
    private SpriteSheetData spriteSheetData;
    public int moveSpeed;
    private String name;


    private int level;
    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5;
    protected float[][] stats;

    public Entity(SpriteSheetData spriteSheetData, String name, Point location){
        stats = new float[MODCOUNT][STATCOUNT];
        this.spriteSheetData = spriteSheetData;
        this.location = location;
        this.name = name;
    }

    public void update(){
        for(int i = 0; i<STATCOUNT; i++)
            stats[CURRENT][i] = stats[BASE][i]+stats[SCALING][i]*level+stats[ITEM][i]+stats[SKILL][i]+stats[BUFF][i];

    }

    public void render(Graphics graphics, Point offset) {


    }
    public abstract void move(int dir);

    public int getWidth(){
        return spriteSheetData.getWidth();
    }

    public int getHeight(){
        return spriteSheetData.getHeight();
    }

    protected Image getImage(int frame) {
        return spriteSheetData.getImage(name, frame);
    }
}
