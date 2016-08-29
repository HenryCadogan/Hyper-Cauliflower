package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
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
    private int moveSpeed;
    private SpriteSheetData spriteSheetData;
    private String name;
    private int health,level,stamina;
    public int experiencePoints;
    protected double facing;

    Point moveVector = new Point(0,0);

    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5;
    protected float[][] stats;

    Entity(SpriteSheetData spriteSheetData, String name, Point location, int health){
        stats = new float[MODCOUNT][STATCOUNT];
        this.spriteSheetData = spriteSheetData;
        this.location = location;
        this.name = name;
        this.health = health;
    }

    public void update(GameState game){
        for(int i = 0; i<STATCOUNT; i++)
            stats[CURRENT][i] = stats[BASE][i]+stats[SCALING][i]*level+stats[ITEM][i]+stats[SKILL][i]+stats[BUFF][i];
        int speed = (int)getSpeed(game.getSpeedMod(getLocation()));
        Point newLocation;
        do {
            moveVector.normalise();
            moveVector.scale(speed--);
            newLocation = getLocation().translate(moveVector);
        }while(speed != -1 && !game.isWalkable(newLocation));
        getLocation().setPosition(newLocation);
        moveVector = new Point(0,0);
    }

    public void render(Graphics graphics, Point offset) {
        graphics.pushTransform();
        graphics.rotate(this.getLocation().getX() + offset.getX(), this.getLocation().getY() + offset.getY(), (float) Math.toDegrees(this.facing));
        graphics.drawImage(getImage(0), this.getLocation().getX()- this.getWidth() / 2 + offset.getX(), this.getLocation().getY() - this.getHeight() / 2 + offset.getY());
        graphics.popTransform();

    }

    public Point getLocation(){
        return location;
    }

    public int getWidth(){
        return spriteSheetData.getWidth();
    }

    public int getHeight(){
        return spriteSheetData.getHeight();
    }

    private float getSpeed(float movementModifier){
        return moveSpeed*movementModifier;
    }

    public void setMoveSpeed(int speed) {moveSpeed = speed;}

    protected Image getImage(int frame) {
        return spriteSheetData.getImage(name, frame);
    }

    public int getLevel(){
        return this.level;
    }

    public int getHealth(){
        return this.health;
    }

    private void takeDamage(int damageValue){
        // todo make this line work this.health - (damageValue - this.inventory.getTotalArmorValue());
    }

    private int getStamina(){
        return this.stamina;
    }
    private void drainStamina(int drainAmount){
        if (this.stamina >= drainAmount){
            this.stamina -= drainAmount;
        } else{

        }
    }

}
