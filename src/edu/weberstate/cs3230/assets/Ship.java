package edu.weberstate.cs3230.assets;

/**
 * Created by joshd on 1/19/2017.
 */
public abstract class Ship {

    public enum shipStatus {Hit, Destroyed, Invalid}
    int hitCount;

    private String name;

    protected Ship(String name){
        this.name = name;
        this.hitCount = 0;
    }

    public final String getName(){
        return name;
    }

    public abstract int getShipSize();

    public abstract int getHitCount();

    public shipStatus damageShip(){
        shipStatus result = shipStatus.Invalid;
        hitCount++;

        if (hitCount < this.getShipSize()){
            result = shipStatus.Hit;
        }else {
            result = shipStatus.Destroyed;
        }
        return result;
    }

}
