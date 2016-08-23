package com.HyperCauliflower.items.weapons;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Experiment Account on 24/07/2016.
 */
public abstract class Projectile implements Updatable,Renderable{

    private Point velocity, pos;
    private Image sprite;
    private double direction;


    public Projectile(double direction, Point pos,int speed, Image sprite){
        this.sprite = sprite;
        this.pos = pos;
        this.direction = direction;
        velocity = new Point(Math.cos(direction), Math.sin(direction));
        velocity.scale(speed);

    }

    @Override
    public void update(GameState game) {
        move();
    }

    @Override
    public void render(Graphics graphics, Point offset) {
        graphics.pushTransform();
        Point p = pos.translate(offset);
        graphics.rotate(p.getX(),p.getY(),((float)Math.toDegrees(direction)+90));
        graphics.drawImage(this.sprite,p.getExactX(),p.getExactY());
        graphics.popTransform();
    }

    private void rotateProjectile(Graphics g,Point offset) {

    }

    private void move(){
        pos = pos.translate(velocity);
    }

}
