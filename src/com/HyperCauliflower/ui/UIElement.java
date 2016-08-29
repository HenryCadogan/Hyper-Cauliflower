package com.HyperCauliflower.ui;

import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Henry on 28/08/2016.
 */
public abstract class UIElement implements Renderable {


    private int width,height;
    private Point pos;
    public UIElement(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics graphics, Point Offset){
        graphics.draw(new Rectangle(this.pos.getX(),this.pos.getY(),this.width,this.height));
    }

    // getting x and y is probably redundant now but you can fix this if you want matt
    // its late, im tired and i was hoping this was going to be easier to implement
    public int getX() {
        return this.pos.getX();
    }

    public int getY() {
        return this.pos.getY();
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Point getPos(){
        return this.pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Boolean pointWithinUIElement(Point p, UIElement u){
        if (p.getX()> u.getX() && p.getX() < u.getBR().getX() && p.getY()> u.getY() && p.getY() < u.getBR().getY()) {
            return true;
        }else{
            return false;
        }
    }

    private Point getBR(){
        return new Point(this.pos.getX() + this.width,this.pos.getY() + this.height);
    }





}
