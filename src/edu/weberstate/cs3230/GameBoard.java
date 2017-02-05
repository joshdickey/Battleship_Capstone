package edu.weberstate.cs3230;

import org.jetbrains.annotations.Contract;

/**
 * Created by joshd on 1/19/2017.
 */
public class GameBoard {

    private String[][] battlefield;
    int size;

    public GameBoard(int boardSize){
        size = boardSize;
        battlefield = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                battlefield[i][j] = "-";
            }
        }
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
