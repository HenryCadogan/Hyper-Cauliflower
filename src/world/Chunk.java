package world;

import org.newdawn.slick.geom.Point;

/**
 * Created by Matt on 24/06/2016.
 */
abstract class Chunk<T> {//stores a 2^n by 2^n grid of tiles


    protected T[][] elems;
    abstract void save();
    private Chunk<Chunk> parent;
    private Point location;

    public Chunk(ChunkChunk parent, Point location){
        this.parent = parent;
        this.location = location;
    }

    T getPos(Point pos){
        return elems[(int)pos.getX()][(int)pos.getY()];
    }

    void setPos(Point pos, T elem){
        elems[(int)pos.getX()][(int)pos.getY()] = elem;
    }

    public Chunk<T> getAdjacent(Point direction) {
        Point adj = new Point(direction.getX()+location.getX(),direction.getY()+location.getY());
        if(adj.getX()!= 0 || adj.getX()!=1 || adj.getY() != 0 || adj.getY() != 1)
            return parent.getPos(adj);
        else
            return parent.getAdjacent(direction).getPos(new Point(adj.getX()%2,adj.getY()%2));
    }
}
