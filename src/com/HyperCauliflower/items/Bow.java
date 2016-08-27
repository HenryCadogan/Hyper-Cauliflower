package com.HyperCauliflower.items;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import org.newdawn.slick.Image;

/**
 * Created by Matt on 27/08/2016.
 */
public class Bow extends Weapon implements Ranged {

    Image projectileSprite;

    public Bow(int rarityMod, int value, String name, Float fireRate, GameState gameState, SpriteSheetData spriteSheetData) {
        super(rarityMod, value, name, fireRate, gameState);
        projectileSprite = spriteSheetData.getImage("arrow",0);
    }


    @Override
    public Projectile shoot(Point position, double direction) {
        return new Projectile(direction, position, 7, projectileSprite);
    }

    @Override
    public void use(Point origin, double direction) {
        getGameState().addProjectile(shoot(origin, direction));
    }
}
