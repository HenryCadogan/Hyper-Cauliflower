package com.HyperCauliflower.items;

import com.HyperCauliflower.entities.Hitbox;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ConfigurableEffect;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.particles.ConfigurableEmitter;

/**
 * Created by Experiment Account on 24/07/2016.
 */
public class Projectile extends Hitbox implements Updatable,Renderable{

    private Point velocity, pos;
    private Image sprite;
    private double direction;
    private ConfigurableEmitter pEffect;


    public Projectile(double direction, Point pos,int speed, Image sprite){
        super(new Circle(pos.getX(),pos.getY(),5));
        this.sprite = sprite;
        this.pos = pos;
        this.direction = direction;
        velocity = new Point(Math.cos(direction), Math.sin(direction));
        velocity.scale(speed*2);
        pEffect = new ConfigurableEmitter("Insert cool name here");


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


    private void move(){
        pos = pos.translate(velocity);
    }

}
