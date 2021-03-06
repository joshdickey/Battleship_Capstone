package edu.weberstate.cs3230.engine;

/**
 * Created by joshd on 1/19/2017.
 */
public abstract class Ship {

    public enum shipStatus {Hit, Destroyed, Invalid}

    private String status;
    int hitCount;
    String orientation;
    private boolean placed;

    private String name;

    public void setStatus(String status) {
        this.status = status;
    }

    protected Ship(String name){
        this.name = name;
        this.hitCount = 0;
        this.status = "Undamaged";
    }

    public final String getName(){
        return name;
    }

    public abstract int getShipSize();

    public String getStatus() {
        return status;
    }

    public abstract int getHitCount();

    public void markAsPlaced(){
        placed = true;
    }

    public boolean getPlacedStatus(){
        return placed;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public shipStatus damageShip(){
        shipStatus result = shipStatus.Invalid;
        hitCount++;

        if (hitCount < this.getShipSize()){
            setStatus("Hit");
            result = shipStatus.Hit;
        }else {
            result = shipStatus.Destroyed;
            setStatus("Destroyed");
        }
        return result;
    }

}
