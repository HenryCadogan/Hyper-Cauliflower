package com.HyperCauliflower.items.Projectiles;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.tests.xml.Entity;

/**
 * Created by Experiment Account on 24/07/2016.
 */
public abstract class Projectile implements Updatable,Renderable{

    private Point target, pos;
    private int dx,dy,speed;
    private Image sprite;


    public Projectile(Point target, Point pos,int speed){
        this.target = target;
        this.pos = pos;
    }

    public Projectile(Entity target,Point pos, int speed){
        //use this for homing attacks onto an entity
    }


    @Override
    public void update(GameState game) {

    }

    @Override
    public void render(Graphics graphics, Point offset) {
        graphics.drawImage(this.sprite,this.pos.getExactX(),this.pos.getExactY());
    }

    private void move(){
        this.pos.setPosition(new Point(this.pos.getExactX() + dx,this.pos.getExactY()+ dy));
    }

}
