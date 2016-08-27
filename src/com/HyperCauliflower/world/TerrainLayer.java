package com.HyperCauliflower.world;

import com.HyperCauliflower.states.*;
import com.flowpowered.noise.module.source.Perlin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.HyperCauliflower.world.Chunk.CHUNK_WIDTH;

/**
 * Created by Matt on 24/06/2016.
 */
public class TerrainLayer implements Renderable, Updatable{

    private int seed,u,v;
    private static final String PATH = "res/JSON/terrain.json";
    private static final int BUFFER = 20, SCREEN_WIDTH = (Main.INTERNAL_WIDTH>>Chunk.CHUNK_SHIFT), STORED_WIDTH = BUFFER+SCREEN_WIDTH, SCREEN_HEIGHT = (Main.INTERNAL_HEIGHT>>Chunk.CHUNK_SHIFT), STORED_HEIGHT = BUFFER + SCREEN_HEIGHT;
    //private static final int SCREEN_WIDTH = 2, SCREEN_HEIGHT = 2, STORED_WIDTH = 8, STORED_HEIGHT = 8;
    static final int GRASS = 0, WATER = 1, SAND = 2, COUNT = 3;
    private Chunk[][] chunksLoaded;
    private Perlin noiseGen;
    private int [][][][] pixels;
    private ArrayList<Boundary> generator;
    private double min = -5;

    public TerrainLayer(int seed, Point spawn){
        try {
            JSONObject j = (JSONObject) new JSONParser().parse(new FileReader(PATH));
            int width = Math.toIntExact((long)j.get("width"));
            SpriteSheet spriteSheet = new SpriteSheet((String)j.get("path"),width, width);

            HashMap<String, Terrain> map = new HashMap<String, Terrain>();
            ((JSONArray)j.get("types")).forEach(o ->{
                JSONObject json = (JSONObject)o;
                map.put((String)json.get("name"),new Terrain(json, width, spriteSheet));
            });

            //it may eventually become a good idea to store the boundaries in a tree structure
            generator = new ArrayList<Boundary>();
            ((JSONArray)j.get("generator")).forEach(o->{
                JSONObject json = (JSONObject) o;
                double max = ((Number)json.get("boundary")).doubleValue();
                generator.add(new Boundary(min, max, map.get(json.get("type"))));
                min = max;
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SlickException e){
            e.printStackTrace();
        }

        noiseGen = new Perlin();
        noiseGen.setOctaveCount(6);
        noiseGen.setSeed(seed);
        u=v=0;
        chunksLoaded = new Chunk[STORED_WIDTH][STORED_HEIGHT];
        for(int i = 0;i<STORED_WIDTH;i++) {
            for (int j = 0; j < STORED_HEIGHT; j++) {
                chunksLoaded[i][j] = generateChunk(i-BUFFER/2+spawn.getX()/CHUNK_WIDTH,j-BUFFER/2+spawn.getY()/CHUNK_WIDTH);
            }
        }
    }

    public void render(Graphics graphics, Point offset) {
        for(Chunk[] ca:chunksLoaded)
            for(Chunk c:ca)
                c.render(graphics,offset);
    }

    private Point getTL(){
        return chunksLoaded[u][v].getLocation();
    }

    private Point getBR(){
        return chunksLoaded[adjustValue(u-1,STORED_WIDTH)][adjustValue(v-1,STORED_HEIGHT)].getLocation();
    }
    public void update(GameState game) {
        Point cameraPosition = new Point(game.getCameraPosition().getX()/ CHUNK_WIDTH,game.getCameraPosition().getY()/ CHUNK_WIDTH);
        if(cameraPosition.getX()-getTL().getX()< (SCREEN_WIDTH/2)+2){
            Point tl = getTL();
            u = adjustValue(u-1,STORED_WIDTH);
            updateColumn(tl.getX()-1,tl.getY());
        }else if (getBR().getX()-cameraPosition.getX()< (SCREEN_WIDTH/2)+1) {
            Point tl = getTL();
            Point br = getBR();
            updateColumn( br.getX() + 1,  tl.getY());
            u = adjustValue(u + 1, STORED_WIDTH);
        }
        if(cameraPosition.getY()-getTL().getY()< (SCREEN_HEIGHT/2)+1){
            Point tl = getTL();
            v = adjustValue(v-1,STORED_HEIGHT);
            updateRow(tl.getY() - 1,tl.getX());
        }else if (getBR().getY()-cameraPosition.getY()< (SCREEN_HEIGHT/2)+2){
            Point br = getBR();
            Point tl = getTL();
            updateRow(br.getY() + 1,tl.getX());
            v = adjustValue(v+1,STORED_HEIGHT);
        }
    }
    private void updateRow(int mod,int x){
        int i = u;
        do{
            chunksLoaded[i][v] = generateChunk(x++,mod);
            i = adjustValue(i+1,STORED_WIDTH);
        }while(i != u);
    }

    private void updateColumn(int mod,int y){
        int j = v;
        do{
            chunksLoaded[u][j] = generateChunk(mod,y++);
            j = adjustValue(j+1,STORED_HEIGHT);
        }while(j != v);
    }

    private Chunk generateChunk(int x, int y){
        return new Chunk(new Point(x, y),noiseGen, generator);
    }

    public boolean getWalkable(int x, int y){
        return getChunk(x,y).getWalkable(adjustValue(x%CHUNK_WIDTH,CHUNK_WIDTH),adjustValue(y%CHUNK_WIDTH,CHUNK_WIDTH));
    }
    public Color getColor(int x, int y){
        return getChunk(x,y).getColor(adjustValue(x%CHUNK_WIDTH,CHUNK_WIDTH),adjustValue(y%CHUNK_WIDTH,CHUNK_WIDTH));
    }
    private Chunk getChunk(int x, int y){
        int chunkX = adjustValue(x/CHUNK_WIDTH+u-getTL().getX(), STORED_WIDTH),
                chunkY = adjustValue(y/CHUNK_WIDTH+v-getTL().getY(), STORED_HEIGHT);
        if(x <= 0){
            chunkX = adjustValue(chunkX - 1, STORED_WIDTH);
        }
        if(y > 0){
            chunkY = adjustValue(chunkY + 1, STORED_HEIGHT);
        }
        return chunksLoaded[chunkX][chunkY];
    }

    public float getSpeedMod(int x, int y){
        int chunkX = adjustValue(x/CHUNK_WIDTH+u-getTL().getX(), STORED_WIDTH),
                chunkY = adjustValue(y/CHUNK_WIDTH+v-getTL().getY(), STORED_HEIGHT);
        if(x <= 0){
            chunkX = adjustValue(chunkX - 1, STORED_WIDTH);
        }
        if(y > 0){
            chunkY = adjustValue(chunkY + 1, STORED_HEIGHT);
        }
        return chunksLoaded[chunkX][chunkY].getSpeedMod(adjustValue(x%CHUNK_WIDTH,CHUNK_WIDTH),adjustValue(y%CHUNK_WIDTH,CHUNK_WIDTH));
    }


    private int adjustValue(int val, int comp){
        if(val>=comp)return val-comp;
        else if(val<0)return comp+val;
        else return val;
    }
}
