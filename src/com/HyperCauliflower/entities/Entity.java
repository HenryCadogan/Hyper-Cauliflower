package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import com.HyperCauliflower.ui.Bar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity extends Hitbox implements Renderable, Updatable {
    private Point location;
    private int moveSpeed;
    private SpriteSheetData spriteSheetData;
    private String name;
    private int health, level, stamina;
    public int experiencePoints;
    protected double facing;
    private double lastHitTime = 0;
    private final int INVUNERABILITY_PERIOD = 50;
    private Bar healthBar;
    private int damageUpdateCount;
    Hitbox hitbox;

    Point moveVector = new Point(0, 0);

    protected static final int CURRENT = 0, BASE = 1, SCALING = 2, ITEM = 3, SKILL = 4, BUFF = 5, MAXHP = 0, ARMOUR = 1, STR = 2, DEX = 3, INT = 4, MODCOUNT = 6, STATCOUNT = 5, ARBITRARY_WIDTH=25;
    protected float[][] stats;

    Entity(SpriteSheetData spriteSheetData, String name, Point location, int health) {
        super(new Circle(location.getX(),location.getY(),ARBITRARY_WIDTH));
        //todo add meaningful width values
        stats = new float[MODCOUNT][STATCOUNT];
        this.spriteSheetData = spriteSheetData;
        this.location = location;
        this.name = name;
        this.health = health;
        this.healthBar = new Bar(new Point(this.getLocation().getX(),this.getLocation().getY() - 30),32,6, getHealth(),1, new Color(255,0,0));
        this.hitbox = new Hitbox(new Circle(this.getLocation().getX(),this.getLocation().getY(),16));
    }


    public void update(GameState game) {
        for (int i = 0; i < STATCOUNT; i++)
            stats[CURRENT][i] = stats[BASE][i] + stats[SCALING][i] * level + stats[ITEM][i] + stats[SKILL][i] + stats[BUFF][i];
        int speed = (int) getSpeed(game.getSpeedMod(getLocation()));
        Point newLocation;
        do {
            moveVector.normalise();
            moveVector.scale(speed--);
            newLocation = getLocation().translate(moveVector);
        } while (speed != -1 && !game.isWalkable(newLocation));
        getLocation().setPosition(newLocation);
        moveVector = new Point(0, 0);

        healthBar.setPos(new Point(this.getLocation().getX() - 16, this.getLocation().getY() - 26));

        if (damageUpdateCount >= 25565) {
            damageUpdateCount = 0;
        }else {
            damageUpdateCount++;
        }
    }

    public void render(Graphics graphics, Point offset) {
        graphics.pushTransform();
        graphics.rotate(this.getLocation().getX() + offset.getX(), this.getLocation().getY() + offset.getY(), (float) Math.toDegrees(this.facing));
        graphics.drawImage(getImage(0), this.getLocation().getX() - this.getWidth() / 2 + offset.getX(), this.getLocation().getY() - this.getHeight() / 2 + offset.getY());
        graphics.popTransform();
        healthBar.render(graphics, offset, this.getHealth());
        graphics.setColor(Color.cyan);
        graphics.draw(new Circle(this.getLocation().getX() + offset.getX(),this.getLocation().getY() + offset.getY(),16));
    }

    public Point getLocation() {
        return location;
    }

    public int getWidth() {
        return spriteSheetData.getWidth();
    }

    public int getHeight() {
        return spriteSheetData.getHeight();
    }

    private float getSpeed(float movementModifier) {
        return moveSpeed * movementModifier;
    }

    public void setMoveSpeed(int speed) {
        moveSpeed = speed;
    }

    protected Image getImage(int frame) {
        return spriteSheetData.getImage(name, frame);
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    Boolean canTakeDamage() {
        if (damageUpdateCount > INVUNERABILITY_PERIOD) {
            damageUpdateCount = 0;
            return true;
        } else {
            return false;
        }
    }

    public void takeDamage(int damageValue) {
            if (this.health > 0) {
                this.health -= damageValue;
                //System.out.println("Ouch!, im now on " + this.getHealth() + " hp!");
                if (this.health < 0) {
                    // ded
                    // do ded stuff
                }
            } else {
                System.out.println("Piss off im already dead");
            }
        }

    private int getStamina() {
        return this.stamina;
    }

    private void drainStamina(int drainAmount) {
        if (stamina >= drainAmount) {
            stamina -= drainAmount;
        } else {

        }
    }

}
