package com.HyperCauliflower.world;

import com.HyperCauliflower.states.Main;
import com.HyperCauliflower.states.Renderable;
import com.flowpowered.noise.module.source.Perlin;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 25/06/2016.
 */
public class Chunk implements Renderable{

    static final int CHUNK_SHIFT = 5, CHUNK_WIDTH = 1<<CHUNK_SHIFT;
    private Point location;
    private Image image;
    private static final int ZOOM = 10000;

    Chunk(Point location, Perlin noiseGen, RenderingWorld world){
        this.location = location;
        ImageBuffer img = new ImageBuffer(CHUNK_WIDTH,CHUNK_WIDTH);
        for(int i = 0; i < CHUNK_WIDTH;i++) {
            for (int j = 0; j < CHUNK_WIDTH; j++) {
                double pixelVal = noiseGen.getValue((i + location.getX()*CHUNK_WIDTH) / ZOOM, (j + location.getY()*CHUNK_WIDTH) / ZOOM, 0);
                int t;
                if (pixelVal > 0.6 || (pixelVal > 0.075 && pixelVal < 0.1))
                    t = RenderingWorld.WATER;
                else if (pixelVal > 0.50 || pixelVal < -0.8)
                    t = RenderingWorld.SAND;
                else {
                    t = RenderingWorld.GRASS;
                }
                img.setRGBA(i, j,world.getPixelColor(t,i,j,0), world.getPixelColor(t,i,j,1), world.getPixelColor(t,i,j,2), 255);
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
