package edu.weberstate.cs3230;

/**
 * Created by joshd on 1/25/2017.
 */
public class Player {

    private  String name;

    public Player() {

    }

    public  String getName(){
        return name;
    }

    public void setName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }else {
            this.name = name;
        }
    }
}
