package com.HyperCauliflower.world;

import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Matt on 28/08/2016.
 */
class Structure implements Renderable{

    private static final int WALL_SIZE = 16;
    private static final String PATH = "res/sprites/wall.png";
    private static final String PATH2 = "res/sprites/floor.png";
    Boolean[][] walls;
    Point location;
    int size;
    Image wallImage, floorImage;

    Structure(String name){
        try {
            wallImage = new Image(PATH);
            floorImage = new Image(PATH2);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        location = new Point(-400,-400);
        size = 50;
        walls = new Boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                walls[i][j]=i==0 ||j==0 || i == size-1 || j ==size-1;
            }
        }
    }

    Boolean isWalkable(int x, int y){
        if(x>location.getX()&&x<location.getX()+WALL_SIZE*size&&y>location.getY()&&y<location.getY()+WALL_SIZE*size)
            return !walls[(x-location.getX())/WALL_SIZE][(y-location.getY())/WALL_SIZE];
        else
            return true;
    }

    @Override
    public void render(Graphics graphics, Point offset) {
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[i].length; j++) {
                if(walls[i][j])
                    graphics.drawImage(wallImage,location.getX()+offset.getX()+i*WALL_SIZE,location.getY()+offset.getY()+j*WALL_SIZE);
                else
                    graphics.drawImage(floorImage,location.getX()+offset.getX()+i*WALL_SIZE,location.getY()+offset.getY()+j*WALL_SIZE);
            }
        }
    }


}
