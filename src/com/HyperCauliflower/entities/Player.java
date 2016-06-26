package com.HyperCauliflower.entities;

import org.newdawn.slick.SpriteSheet;
import com.HyperCauliflower.states.GameState;

/**
 * Created by Matt on 24/06/2016.
 */
public class Player extends Entity implements com.HyperCauliflower.states.Renderable, com.HyperCauliflower.states.Updatable{

    public Player(SpriteSheet spriteSheet){
        super(spriteSheet);

    }

    public void move(){

    }

    public void update(GameState game) {

    }
}
