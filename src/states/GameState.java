package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import world.RenderingWorld;

import java.util.List;

/**
 * Created by Matt on 24/06/2016.
 */
public class GameState extends BasicGameState {

    private List<Renderable> renderables;
    private List<Updatable> updatables;
    private Point cameraPosition;

    public int getID() {
        return Game.State.GAME.ordinal();
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        RenderingWorld r = new RenderingWorld();
        renderables.add(r);
        updatables.add(r);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        for (Renderable r : renderables) {
            r.render(graphics, new Point(-cameraPosition.getX(), -cameraPosition.getY()));
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        for (Updatable u : updatables) {
            u.update(this);
        }
    }

    public Point getCameraPosition() {
        return cameraPosition;
    }


}