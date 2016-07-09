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

    static final int CHUNK_SHIFT = 3, CHUNK_WIDTH = 1<<CHUNK_SHIFT;

    private Point location;
    private Image image;
    private static final int ZOOM = 10000;

    Chunk(Point location, Perlin noiseGen){
        this.location = location;
        ImageBuffer img = new ImageBuffer(CHUNK_WIDTH,CHUNK_WIDTH);
        for(int i = 0; i < CHUNK_WIDTH;i++) {
            for (int j = 0; j < CHUNK_WIDTH; j++) {
                double pixelVal = noiseGen.getValue((i + location.getX()*CHUNK_WIDTH) / ZOOM, (j + location.getY()*CHUNK_WIDTH) / ZOOM, 0);
                if (pixelVal > 0.6 || (pixelVal > 0.075 && pixelVal < 0.1))
                    img.setRGBA(i, j, 0, 0, 255, 255);
                else if (pixelVal > 0.50 || pixelVal < -0.8)
                    img.setRGBA(i, j, 255, 250, 205, 255);
                else
                    img.setRGBA(i, j, 0, 255, 0, 255);
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
