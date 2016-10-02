package com.HyperCauliflower.items;

import com.HyperCauliflower.entities.Hitbox;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleIO;

import java.io.IOException;
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
    private ConfigurableEmitter trail;


    public Projectile(double direction, Point pos,int speed, Image sprite){
        super(new Circle(pos.getX(),pos.getY(),5));
        this.sprite = sprite;
        this.pos = pos;
        this.direction = direction;
        velocity = new Point(Math.cos(direction), Math.sin(direction));
        velocity.scale(speed*2);
        pEffect = new ConfigurableEmitter("Insert cool name here");


        try {
            trail = ParticleIO.loadEmitter("/res/sprites/Particles/trail.xml");
            trail.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    @Override
    public void update(GameState game) {
        move();
        moveTrail();
    }

    private void moveTrail() {
        //System.out.println("pos is: " + pos.getX()+ " , " + pos.getY());
    }

    @Override
    public void render(Graphics graphics, Point offset) {
        graphics.pushTransform();
        Point p = pos.translate(offset);
        graphics.rotate(p.getX(),p.getY(),((float)Math.toDegrees(direction)+90));
        graphics.drawImage(this.sprite,p.getExactX(),p.getExactY());
        trail.setPosition(pos.getX() + offset.getX(),pos.getY() + offset.getY());
        graphics.popTransform();
    }


    private void move(){
        pos = pos.translate(velocity);
    }

    public ParticleEmitter getTrail(){
        return trail;
    }
}
