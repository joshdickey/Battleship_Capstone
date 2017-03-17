package edu.weberstate.cs3230;

import edu.weberstate.cs3230.assets.Ship;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * Created by joshd on 1/19/2017.
 */
public class GameBoard {

    private GameTile[][] battlefield;
    int size;
    private String gamePhase;

    public GameBoard(int boardSize){
        size = boardSize;
        battlefield = new GameTile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                battlefield[i][j] = new GameTile();
            }
        }
    }

    void setGamePhase(String gamePhase) {
        this.gamePhase = gamePhase;
    }

    //    public <T> boolean placeInGameTile(T item, int x, int y){
    boolean placeInGameTile(Ship ship, int x, int y){
        boolean tileStatus = false;

        String shipType = "-";

        if  (Objects.equals(ship.getName(), "Carrier")){
            shipType = "C";
        }else if (Objects.equals(ship.getName(), "Battleship")){
            shipType = "B";
        }else if (Objects.equals(ship.getName(), "Cruiser")){
            shipType = "Z";
        }else if (Objects.equals(ship.getName(), "Destroyer")){
            shipType = "D";
        }else if (Objects.equals(ship.getName(), "Submarine")){
            shipType = "S";
        }else {
            shipType = "*";
        }

        if (battlefield[y][x - 1].hasObject()){
            tileStatus = true;

        }else {
            tileStatus = false;
            battlefield[y][x - 1].placeObject(ship);
            battlefield[y][x - 1].setObjectMarker(shipType);

        }
        return tileStatus;
    }
    Ship getShipFromTile(int x, int y){
        Ship ship = (Ship) battlefield[y][x -1].getObject();
        return ship;
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
    boolean tileHasShip(int x, int y){
        boolean notEmpty;
        notEmpty = battlefield[y][x - 1].hasObject();

        return notEmpty;
    }

    void showGameBoard(){
        for (int i = 0; i < size; i++){
            if ( i == 0){
                System.out.print(" ");
            }
            System.out.print( " " + (i+1));
        }
        System.out.println();

        for (int i = 0; i < size; i++){
            System.out.printf("%c ", 65 + i);
            for (int j = 0; j < size; j++){
                if(gamePhase.equals("setup")) {
                    if (battlefield[i][j].hasObject()) {
                        System.out.print(battlefield[i][j].getObjectMarker()+ " ");
                    } else {
                        System.out.print("- ");
                    }
                }
                if (gamePhase.equalsIgnoreCase("battle")){
//                    if (battlefield[i][j].hasObject()) {
                        if(battlefield[i][j].getObjectMarker().equalsIgnoreCase("X")) {
                            System.out.print("X ");
                        }
                        else if(battlefield[i][j].getObjectMarker().equalsIgnoreCase("0")) {
                            System.out.print("0 ");
                        }else {
                            System.out.print("- ");
                        }
                    }
                }
                System.out.println();
            }
    }

    String setItemOrientation(int orientation){
        String orientationSet;
        if (orientation == 1){
            orientationSet = "Horizontal";
        }else if (orientation == 2){
            orientationSet = "Vertical";
        }else {
            orientationSet = "Not Set";
        }

        return orientationSet;
    }

    static int convertY(String y){
        int yAxis = 0;

        if (y.toUpperCase().charAt(0)< 64 || y.toUpperCase().charAt(0) < 91) {
            yAxis =  y.toUpperCase().charAt(0) - 65;
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

//    public void markBoard(Ship ship, int x, int y) {
    public void markBoard(int x, int y) {

        if (tileHasShip(x, y)){
//            System.out.println(ship.damageShip());
//            System.out.println("HIT from MarkBoard");
            battlefield[y][x-1].setObjectMarker("X");

        }else {
//            System.out.println("\nMISS from MarkBoard");
            battlefield[y][x-1].setObjectMarker("0");

        }
//        System.out.println(battlefield[y][x-1].getObjectMarker());
    }
}
