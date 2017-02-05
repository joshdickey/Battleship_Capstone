package edu.weberstate.cs3230.assets;

import edu.weberstate.cs3230.assets.Ship;

/**
 * Created by joshd on 2/5/2017.
 */
public class Destroyer extends Ship {

    private static final int SHIP_SIZE = 2;

    public  Destroyer(){
        this("Destroyer");
    }

    public Destroyer(String name) {
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


