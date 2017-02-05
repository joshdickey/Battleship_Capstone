package edu.weberstate.cs3230;

import org.jetbrains.annotations.Contract;

/**
 * Created by joshd on 1/19/2017.
 */
public class Gameboard {

    private String[][] battlefield;

    public Gameboard (int boardSize){
        battlefield = new String[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                battlefield[i][j] = "-";
            }
        }
    }

    public void markGameboard(int Y, int X){

        battlefield[Y][X-1] = "X";
    }

    @Contract(pure = true)
    public boolean CheckIfEmpty(int Y, int X){
        boolean isEmpty;
        isEmpty = battlefield[Y][X-1] == "-";


        return isEmpty;
    }

    public void showGameboard(){
        for (String[] i :
                battlefield){
            for (String j :
                    i) {
                System.out.print(j);
            }
            System.out.println("");
        }
    }

}
