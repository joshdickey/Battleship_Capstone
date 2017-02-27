package edu.weberstate.cs3230;

import edu.weberstate.cs3230.assets.Ship;
import org.jetbrains.annotations.Contract;

/**
 * Created by joshd on 1/19/2017.
 */
public class GameBoard {

    private GameTile[][] battlefield;
    int size;

    public GameBoard(int boardSize){
        size = boardSize;
        battlefield = new GameTile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                battlefield[i][j] = new GameTile();
            }
        }
    }

    public <T> boolean placeInGameTile(T item, int x, int y){
        boolean tileStatus = false;

        if (battlefield[y][x - 1].hasShip()){
            tileStatus = true;
        }else {
            tileStatus = false;
            battlefield[y][x - 1].placeObject(item);
        }
        return tileStatus;
    }

//    private void placeShip(Ship ship, String orientation, int x, int y){
//
//        if (orientation.equalsIgnoreCase("Horizontal") && ship.getShipSize() + x <= size){
//            System.out.print("ship is Horizontal");
//
//            while(placeInGameTile(ship, x, y)){
//                placeInGameTile(ship, x, y);
//            }
//
//        } else if (orientation.equalsIgnoreCase("Vertical") && ship.getShipSize() + y <= size){
//            System.out.print("ship is Vertical");
//        } else {
//            System.out.print("ship is not placed");
//        }
//    }

    @Contract(pure = true)
    public boolean tileHasShip(int y, int x){
        boolean notEmpty;
        notEmpty = battlefield[y][x - 1].hasShip();

        return notEmpty;
    }

    public void showGameBoard(){

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (battlefield[i][j].hasShip()) {
                    System.out.print("X");
                }else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    public String setItemOrientation(int orientation){
        String orientationSet = "";
        if (orientation == 1){
            orientationSet = "Horizontal";
        }else if (orientation == 2){
            orientationSet = "Vertical";
        }else {
            orientationSet = "Not Set";
        }

        return orientationSet;
    }

    static int convertY(String Y){
        int yAxis = 0;

        if (Y.toUpperCase().charAt(0)< 64 || Y.toUpperCase().charAt(0) < 91) {
            yAxis =  Y.toUpperCase().charAt(0) - 65;
        }else{
            System.out.println("Invalid input for letter A-J");
            yAxis = -1;
        }

        return yAxis;
    }

    public boolean ChieckIfEmpty(GameTile[][] board ){
        boolean passed = false;


        return passed;
    }

}
