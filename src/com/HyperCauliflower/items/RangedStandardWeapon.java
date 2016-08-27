package com.HyperCauliflower.items;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.json.simple.JSONObject;
import org.newdawn.slick.Image;

/**
 * Created by Matt on 27/08/2016.
 */
public class RangedStandardWeapon extends Weapon implements Ranged {

    ProjectileFactory projectileFactory;

    public RangedStandardWeapon(int rarityMod, int value, String name, int fireRate, GameState gameState, ProjectileFactory p) {
        super(rarityMod, value, name, fireRate, gameState);
        projectileFactory = p;
    }


    @Override
    public Projectile shoot(Point position, double direction) {
        return projectileFactory.produce(position,direction);
    }

    @Override
    public void use(Point origin, double direction) {
        getGameState().addProjectile(shoot(origin, direction));
    }
}
