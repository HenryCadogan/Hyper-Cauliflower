package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import states.Renderable;
import states.Updatable;

/**
 * Created by Matt on 24/06/2016.
 */
public abstract class Entity implements Renderable, Updatable{
    protected Point offset;
    private SpriteSheet spriteSheet;

    public Entity(SpriteSheet spriteSheet){
    }

    public void render(Graphics graphics, Point offset) {
        //drawing method

    }
}
