package com.HyperCauliflower.entities;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.Point;

/**
 * Created by Matt on 12/09/2016.
 */
public class MobFactory {

    private int radius, health;
    private String name;

    MobFactory(String name, int health, int radius){
        this.name = name;
        this.health = health;
        this.radius = radius;
    }

    public Mob produce(SpriteSheetData s, Point p){
        return new Mob(s, name, p, health, radius);
    }
}
