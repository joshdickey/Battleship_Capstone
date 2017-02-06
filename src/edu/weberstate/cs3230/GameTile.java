package edu.weberstate.cs3230;

/**
 * Created by Josh on 2/5/2017.
 */
public class GameTile<T> {
    private T object;
    private boolean hasShip;

    public void placeObject(T object) {
        this.object = object;
        this.hasShip = true;
    }

    public boolean hasShip() {
        return hasShip;
    }
}
