package com.HyperCauliflower.entities;

import org.newdawn.slick.geom.Shape;

/**
 * Created by Matt on 11/09/2016.
 */
public class Hitbox {
    private Shape hitbox;

    public Hitbox(Shape hitbox){
        this.hitbox = hitbox;
    }

    private Shape getShape(){
        return  hitbox;
    }
    public Boolean hits(Hitbox h){
        return h.getShape().intersects(hitbox);
    }
}
