package com.HyperCauliflower.items;

import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Image;

/**
 * Created by Matt on 27/08/2016.
 */
public class ProjectileFactory {

    private int speed;
    private Image sprite;
    ProjectileFactory(int speed, Image sprite){
        this.speed = speed;
        this.sprite = sprite;
    }

    Projectile produce(Point position, double direction){
        return new Projectile(direction, position, speed, sprite);
    }
}
