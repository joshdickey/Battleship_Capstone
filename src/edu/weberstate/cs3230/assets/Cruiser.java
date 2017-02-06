package edu.weberstate.cs3230.assets;

/**
 * Created by joshd on 2/5/2017.
 */
public class Cruiser extends Ship {

    private static final int SHIP_SIZE = 3;


    public Cruiser(){
        this("Cruiser");
    }

    public Cruiser(String name) {
        super(name);
    }
    @Override
    public int getShipSize() {
        return SHIP_SIZE;
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

}
