package edu.weberstate.cs3230;

/**
 * Created by joshd on 1/25/2017.
 */
public class Coordinate {

    private Coordinate(){

    }

    public static int changeToRowIndex(char row){
       char upperdaseChar = Character.toUpperCase(row);

       return upperdaseChar - (int)'A';
    }

    public static Coordinate createInstance(){
        return new Coordinate();
    }

}
