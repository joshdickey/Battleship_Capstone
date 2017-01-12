package edu.weberstate.cs3230;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        // write your code here.


        int[][] battlefield = new int[10][10];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j<10; j++) {
                if ((i + j)%5 ==0){
                    battlefield[i][j] = 1;
                }else {
                    battlefield[i][j] = 0;
                }
            }
        }
        System.out.println(CheckIfEmpty("j",9,battlefield));
        printBattlefield(battlefield);

    }

    private  static boolean CheckIfEmpty(String Y, int X, int[][] battlefield){
        boolean isEmpty = false;
        int yAxis = 0;
        int xAxis = X - 1;

       // int[] yConverter = new int[] {1,2,3,4,5,6,7,8,9,10};
        if (Y.equalsIgnoreCase("a")){
            yAxis = 0;
        }else if (Y.equalsIgnoreCase("b")){
            yAxis = 1;
        }else if (Y.equalsIgnoreCase("c")){
            yAxis = 2;
        }else if (Y.equalsIgnoreCase("d")){
            yAxis = 3;
        }else if (Y.equalsIgnoreCase("e")){
            yAxis = 4;
        }else if (Y.equalsIgnoreCase("f")){
            yAxis = 5;
        }else if (Y.equalsIgnoreCase("g")){
            yAxis = 6;
        }else if (Y.equalsIgnoreCase("h")){
            yAxis = 7;
        }else if (Y.equalsIgnoreCase("i")){
            yAxis = 8;
        }else if (Y.equalsIgnoreCase("j")){
            yAxis = 9;
        }else{
            System.out.println("Invalid input for letter A-J");
        }

        isEmpty = battlefield[xAxis][yAxis] == 0;

        return isEmpty;
    }

    private static void printBattlefield(int[][] battlefield){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j<10; j++) {
                System.out.print(battlefield[i][j]);
                if (j == 9){
                    System.out.println("");
                }
            }
        }
    }


}

