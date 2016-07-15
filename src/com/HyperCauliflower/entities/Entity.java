package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity implements Renderable, Updatable{
    private Point location;
    protected int moveSpeed;
    private SpriteSheetData spriteSheetData;
    private String name;
    protected double facing;

    private int level;
    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5;
    protected float[][] stats;

    Entity(SpriteSheetData spriteSheetData, String name, Point location){
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
        graphics.pushTransform();
        graphics.rotate(this.getLocation().getX() + offset.getX(), this.getLocation().getY() + offset.getY(), (float) Math.toDegrees(this.facing));
        graphics.drawImage(getImage(0), this.getLocation().getX()- this.getWidth() / 2 + offset.getX(), this.getLocation().getY() - this.getHeight() / 2 + offset.getY());
        graphics.popTransform();

    }
    public abstract void move(int dir);

    public Point getLocation(){
        return location;
    }

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
