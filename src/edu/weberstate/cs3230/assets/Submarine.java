package edu.weberstate.cs3230.assets;

/**
 * Created by Josh on 2/5/2017.
 */
public class Submarine extends Ship {

    private static final int SHIP_SIZE = 3;

    public Submarine() {
        this("Submarine");
    }

    public Submarine(String name){
        super(name);
    }

    @Override
    public int getShipSize() {
        return 0;
    }

    @Override
    public int getHitCount() {
        return 0;
    }
}
