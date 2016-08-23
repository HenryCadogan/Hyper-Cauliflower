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


    public Projectile(double direction, Point pos,int speed, Image sprite){
        this.sprite = sprite;
        this.pos = pos;
        velocity = new Point(Math.cos(direction), Math.sin(direction));
        velocity.scale(speed);
    }

    @Override
    public void update(GameState game) {
        move();
    }

    @Override
    public void render(Graphics graphics, Point offset) {
        Point p = pos.translate(offset);
        graphics.drawImage(this.sprite,p.getExactX(),p.getExactY());
    }

    private void move(){
        pos = pos.translate(velocity);
    }

}
