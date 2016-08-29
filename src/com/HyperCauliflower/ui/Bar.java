package com.HyperCauliflower.ui;

import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Henry on 29/08/2016.
 */
public class Bar extends UIElement implements Renderable {
    private int maxVal;
    private Color color;

    public Bar(Point pos, int width, int height,int maxVal, Color color) {
        super(pos, width, height);
        this.maxVal = maxVal;
        this.color = color;
    }

    public void render(Graphics graphics, Point offset) {
        graphics.setColor(Color.white);
        graphics.fillRect(this.getX()+ offset.getX() -2,this.getY()+ offset.getY()- 2, this.getWidth() + 4, this.getHeight() +4);
        graphics.setColor(this.color);
        graphics.fillRect(this.getX() + offset.getX(),this.getY() + offset.getY(),this.getWidth(),this.getHeight());
    }

    //todo add in actually changing the bar length for taking damage




}
