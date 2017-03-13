package edu.weberstate.cs3230;


public class Main {

    public static final java.lang.String LOGGER_NAME = "edu.weberstate.cs3230.Battleship";

    public static void main(String[] args) {

        ConsoleGame myConsoleGame = new ConsoleGame();
        myConsoleGame.setBoardSize(10);
//        myConsoleGame.startGame();
        myConsoleGame.startConsoleGameFromFile();

    }

}

