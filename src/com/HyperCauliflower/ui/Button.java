package com.HyperCauliflower.ui;

import com.HyperCauliflower.input.Clickable;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.event.MouseEvent;

/**
 * Created by Henry on 28/08/2016.
 */


public class Button extends UIElement implements Renderable,Clickable {

    public Button(Point pos, int width, int height) {
        super(pos,width,height);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY());
        if (this.pointWithinUIElement(p,this)){
            System.out.println("clicked on button");
        } else{
            System.out.println("clicked outside button");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void render(Graphics graphics, Point offset) {
        super.render(graphics,offset);
    }




}
