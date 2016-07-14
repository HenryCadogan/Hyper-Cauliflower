package com.HyperCauliflower.world;

import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

/**
 * Created by Matt on 25/06/2016.
 */
public class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 5, CHUNK_WIDTH = 1<<CHUNK_SHIFT;
    private Point location;
    private Image image;
    private static final int ZOOM = 10000;
    private boolean[][] walkable;
    private Terrain t;


    Chunk(Point location, Perlin noiseGen, ArrayList<Boundary> generator){
        this.location = location;
        walkable = new boolean[32][32];
        ImageBuffer img = new ImageBuffer(CHUNK_WIDTH,CHUNK_WIDTH);
        for(int i = 0; i < CHUNK_WIDTH;i++) {
            for (int j = 0; j < CHUNK_WIDTH; j++) {
                double pixelVal = noiseGen.getValue((i + location.getX()*CHUNK_WIDTH) / ZOOM, (j + location.getY()*CHUNK_WIDTH) / ZOOM, 0);
                generator.forEach(boundary->{
                    if(boundary.inBoundary(pixelVal))
                        t = boundary.getTerrain();
                });
                walkable[i][j] = t.getWalkable();
                img.setRGBA(i, j,t.getColor(i,j,Terrain.RED), t.getColor(i,j,Terrain.GREEN), t.getColor(i,j,Terrain.BLUE), 255);
            }
        }
        image = img.getImage();
    }

    public void render(Graphics graphics, Point offset) {
        Point drawLocation = new Point(location.getX()*CHUNK_WIDTH + offset.getX(),location.getY()*CHUNK_WIDTH + offset.getY());
        if(drawLocation.getX()>-CHUNK_WIDTH && drawLocation.getX()<Main.INTERNAL_WIDTH && drawLocation.getY()>-CHUNK_WIDTH&&drawLocation.getY()<Main.INTERNAL_HEIGHT)
        graphics.drawImage(image, drawLocation.getX(),drawLocation.getY());
    }

    Point getLocation(){
        return location;
    }
}
