package edu.weberstate.cs3230.engine;

/**
 * Created by joshd on 2/5/2017.
 */
public class Carrier extends Ship {

    private static final int SHIP_SIZE = 5;

    public  Carrier(){
        this("Carrier");
    }

    public Carrier(String name) {
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
