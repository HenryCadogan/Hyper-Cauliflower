package states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
public interface Renderable {
    void render(Graphics graphics, Point offset);
}
