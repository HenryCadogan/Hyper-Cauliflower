package com.HyperCauliflower.world;

import com.HyperCauliflower.states.GameState;
import com.HyperCauliflower.states.Point;
import com.HyperCauliflower.states.Renderable;
import com.HyperCauliflower.states.Updatable;
import org.newdawn.slick.Graphics;

/**
 * Created by Matt on 28/08/2016.
 */
public class StructureLayer implements Renderable{

    Structure[] structures;

    public StructureLayer(int seed){
        structures = new Structure[]{
            new Structure("town")
        };
    }

    @Override
    public void render(Graphics graphics, Point offset) {
        for (Structure structure : structures) {
            structure.render(graphics,offset);
        }
    }

    public boolean getWalkable(int x, int y){
        Boolean r = true;
        for (Structure structure : structures) {
            r = r && structure.isWalkable(x,y);
        }
        return r;
    }
}
