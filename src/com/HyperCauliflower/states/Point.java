package com.HyperCauliflower.states;

/**
 * Created by Matt on 15/07/2016.
 */
public class Point {

    private float x,y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y){
        this.x = (float)x;
        this.y = (float)y;
    }


    public Point(org.newdawn.slick.geom.Point p){
        x = p.getX();
        y = p.getY();
    }

    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public float getExactX(){return x;}
    public float getExactY(){return y;}
    public Point translate(Point p){
        return new Point(x+p.getExactX(),y+p.getExactY());
    }
    public void setPosition(Point p){
        x = p.getExactX();
        y = p.getExactY();
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void scale(float s){
        x*=s;
        y*=s;
    }
    public void normalise(){
        float xf = x, yf = y, x2 = xf*xf, y2 = yf*yf, m = (float)Math.sqrt(x2+y2);
        if (m!= 0) {
            x = (xf / m);
            y = (yf / m);
        }
    }
}
