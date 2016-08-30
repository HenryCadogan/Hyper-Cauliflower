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
    private int borderSize;
    float barPercentage = 0;

    public Bar(Point pos, int width, int height,int maxVal,int borderSize, Color color) {
        super(pos, width, height);
        this.maxVal = maxVal;
        this.color = color;
        this.borderSize = borderSize;
    }

    public void render(Graphics graphics, Point offset,int amount) {
        graphics.setColor(Color.white);
        graphics.fillRect(this.getX()+ offset.getX() -borderSize,this.getY()+ offset.getY()- borderSize, this.getWidth() + borderSize*2, this.getHeight() +borderSize*2);
        graphics.setColor(this.color);
        //todo make sure the bar doesnt go below 0
        if ((float)amount/maxVal<0){
          barPercentage = 0;
        } else{
            barPercentage = (float)amount/maxVal;
        }
        graphics.fillRect(this.getX() + offset.getX(),this.getY() + offset.getY(),getWidth()*barPercentage,this.getHeight());
    }
}
