package edu.weberstate.cs3230;

/**
 * Created by Josh on 2/5/2017.
 */
public class GameTile<T> {
    private T object;
    private boolean hasObject;
    private String objectMarker = "-";

    public String getObjectMarker() {
        return objectMarker;
    }

    public void setObjectMarker(String objectMarker) {
        this.objectMarker = objectMarker;
    }

    public void placeObject(T object) {
        this.object = object;

        this.hasObject = true;
    }

    public boolean hasObject() {
        return hasObject;
    }

    public T getObject() {
        return object;
    }
}
