package com.HyperCauliflower.items;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Matt on 27/08/2016.
 */
public class RangedStandardWeapon extends Weapon implements Ranged {

    ProjectileFactory projectileFactory;
    private Sound bowFireSound;



    public RangedStandardWeapon(int rarityMod, int value, String name, int fireRate, GameState gameState, ProjectileFactory p) {
        super(rarityMod, value, name, fireRate, gameState);
        projectileFactory = p;
        try {
            bowFireSound = new Sound("res/Sounds/Weapons/Bow Fire.wav");
        } catch(SlickException e){
            e.printStackTrace();
        }

    }


    @Override
    public Projectile shoot(Point position, double direction) {
        return projectileFactory.produce(position,direction);
    }

    @Override
    public void use(Point origin, double direction) {
        getGameState().addProjectile(shoot(origin, direction));
        double random = ThreadLocalRandom.current().nextDouble(0.8, 1.2);
        bowFireSound.play((float)random,(float) 0.5);

    }
}
