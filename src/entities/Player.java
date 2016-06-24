package entities;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import states.GameState;

/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity implements states.Renderable,states.Updatable{

    public Player(SpriteSheet spriteSheet){
        super(spriteSheet);

    }

    public void move(){

    }

    public void update(GameState game) {

    }
}
