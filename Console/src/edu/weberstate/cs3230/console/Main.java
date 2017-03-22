package edu.weberstate.cs3230.console;
import edu.weberstate.cs3230.engine.IGame;


public class Main {

    public static final java.lang.String LOGGER_NAME = "edu.weberstate.cs3230.Battleship";

    public static void main(String[] args) {

        ConsoleGame myConsoleGame = new ConsoleGame();
        myConsoleGame.setBoardSize(10);
//        myConsoleGame.startGame();
        myConsoleGame.startConsoleGameFromFile();

    }

}

