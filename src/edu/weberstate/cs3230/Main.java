package edu.weberstate.cs3230;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[][] battlefield = new int[10][10];
        Scanner letterInput = new Scanner(System.in);
        Scanner numInput = new Scanner(System.in);
        Scanner cont = new Scanner(System.in);

        for (int i = 0; i < 10; i++){
            for (int j = 0; j<10; j++) {
                battlefield[i][j] = 0;
            }
        }

        boolean breakout = false;
        while (!breakout) {

            System.out.println("Enter a letter(A-J): ");
            String letter = letterInput.nextLine();
            int Y = convertY(letter);

            System.out.println("Enter a number(1-10): ");
            int X = Integer.parseInt(numInput.nextLine());


            if((CheckIfEmpty(Y, X, battlefield)) == true){
                System.out.println("This cell is empty\n");
            }else{
                System.out.println("This cell has already been filled\n");
            }

            markBattlefield(Y, X, battlefield);

            printBattlefield(battlefield);

            System.out.println("press Q to quit or Y to continue playing ");
            String  again = cont.nextLine();
            if (again.equalsIgnoreCase("q")){
                breakout = true;
            }


        }

        Scanner coordinates = new Scanner(System.in);
        int Y;
        int X;
        Gameboard game = new Gameboard(10);

        breakout = false;
        while (!breakout) {
            System.out.println("Enter a letter A-J: ");
            String letter = coordinates.next();
            Y = convertY(letter);

            //validate letter is in bounds
            if (Y > 10 || Y < 0){
                System.out.println("You Must enter a letter from A-J");
                continue;
            }

            System.out.println("Enter a Number 1-10: ");
            X = Integer.parseInt(coordinates.next());

            if (X > 10 || X < 1) {
                System.out.println("You Must enter a number from 1-10");
                continue;
            }

            if (!game.CheckIfEmpty(Y,X)){
                System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                continue;
            }
            game.markGameboard(Y, X);
            game.showGameboard();

            System.out.println("press Q to quit or Y to continue playing ");
            String  again = cont.nextLine();
            if (again.equalsIgnoreCase("q")){
                breakout = true;
            }
        }

        char row = 'A';
        Coordinate.changeToRowIndex(row);

        Coordinate coord = Coordinate.createInstance();

    }

    private static void markBattlefield(int Y, int X, int[][] battlefield) {
        battlefield[Y][X-1] = 1;
    }

    private static boolean CheckIfEmpty(int Y, int X, int[][] battlefield){
        boolean isEmpty;
        isEmpty = battlefield[Y][X-1] == 0;

        return isEmpty;
    }

    private static int convertY(String Y){
        int yAxis = 0;

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
        return yAxis;
    }

    private static void printBattlefield(int[][] battlefield){

        for (int[] i : battlefield){

            for (int j : i){
                System.out.print(j);
            }
            System.out.println("");
        }
    }


}

