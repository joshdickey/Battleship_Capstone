package edu.weberstate.cs3230;

import java.util.Scanner;

/**
 * Created by joshd on 2/5/2017.
 */
public class Game {

    private int boardSize, numPlayers;
    private Player[] players;
    private GameBoard gameboard;

    public Game() {
        numPlayers = 1;
        boardSize = 10;
    }

    public Game(int boardSize){
        this.boardSize = boardSize;
        this.numPlayers = 1;
    }


    public void startGame(){
        makeGameBoard(boardSize);
        setupGame();
        generatePlayers();

    }


    private void setupGame(){

        Scanner coordinates = new Scanner(System.in);
        int Y;
        int X;
        GameBoard gameboard = new GameBoard(boardSize);
        boolean breakout;

        breakout = false;
        while (!breakout) {
            System.out.println(String.format("Enter a letter A-%c: ", boardSize+64));
            String letter = coordinates.next();
            Y = GameBoard.convertY(letter);

            //validate letter is in bounds
            if (Y == -1 ){
                continue;
            }
            else if (Y > boardSize || Y < 0){
                System.out.println(String.format("You Must enter a letter from A-%c: ", boardSize+64));
                continue;
            }

            System.out.println("Enter a Number 1-" + boardSize + ": ");
            X = Integer.parseInt(coordinates.next());

            if (X > boardSize || X < 1) {
                System.out.println("You Must enter a number from 1-" + boardSize);
                continue;
            }

            if (!gameboard.CheckIfEmpty(Y,X)){
                System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                continue;
            }
            gameboard.markGameboard(Y, X);
            gameboard.showGameboard();

            System.out.println("press Q to quit or Y to continue playing ");
            String  again = coordinates.next();
            if (again.equalsIgnoreCase("q")){
                breakout = true;
            }
        }
    }

    private void generatePlayers() {
        players = new Player[numPlayers];

    }

    private GameBoard makeGameBoard(int size){

        gameboard = new GameBoard(size);
        return gameboard;
    }


}
