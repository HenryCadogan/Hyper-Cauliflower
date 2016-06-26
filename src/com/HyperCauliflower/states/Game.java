package com.HyperCauliflower.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Matt on 24/06/2016.
 */
class Game extends StateBasedGame {

    enum State{GAME} //gives the values for the id of each state

    Game(){
        super("Fuck you Miguel");
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new GameState());
    }
}
