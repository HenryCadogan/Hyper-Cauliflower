package input;

import net.java.games.input.Mouse;

import java.awt.event.MouseEvent;

/**
 * Created by Henry on 26/06/2016.
 */
public interface Clickable {

    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
}
