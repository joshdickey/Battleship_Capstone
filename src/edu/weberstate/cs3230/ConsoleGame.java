package edu.weberstate.cs3230;

import com.sun.org.apache.xpath.internal.SourceTree;
import edu.weberstate.cs3230.assets.*;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by joshd on 2/5/2017.
 */
public class ConsoleGame implements IGame{

    private int boardSize, numPlayers, x, y;
    private Player[] players;
    private List<Ship> ships;
    private GameBoard gameboard;

    public ConsoleGame() {
        numPlayers = 1;
        boardSize = 10;
    }

    public void startGame(){
        gameboard = new GameBoard(boardSize);
        setupGame();
        generatePlayers();
    }
    public void startConsoleGameFromFile(){
        gameboard = new GameBoard(boardSize);
        generatePlayers();
        setupGameFromFile();
    }


    @Override
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public void addPlayer(String name) {

    }

    private void setupGameFromFile() {

        //generates ships into an array
        generateShips();

        boolean isPlaying = true;
        boolean isValidInput = true;
        String userInput;
        while(isPlaying){

            Logger logger = Logger.getLogger(Main.LOGGER_NAME);
            logger.setLevel(Level.FINE);
            Scanner scanner;
            File file;
            try {
                file = new File("C:\\Users\\jdickey\\IdeaProjects\\CS3230_Battleship\\src\\edu\\weberstate\\cs3230\\resources\\userInput.txt");

                scanner = new Scanner(new FileReader(file));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                break;
            }

            while (isValidInput){

                System.out.println(String.format("Enter a letter A-%c: ", changeYToRowLabel(boardSize)));

                userInput = scanner.next();
                System.out.println(userInput);
                y = GameBoard.convertY(userInput);

                //validate letter is in bounds
                if (y == -1 ){
                    continue;
                }
                else if (y > boardSize || y < 0){
                    System.out.println(String.format("You Must enter a letter from A-%c: \n", changeYToRowLabel(boardSize)));
                    continue;
                }

                System.out.println("Enter a Number 1-" + boardSize + ": ");
                userInput = scanner.next();
                System.out.println(userInput);
                x = Integer.parseInt(userInput);

                if (x > boardSize || x < 1) {
                    System.out.println("You Must enter a number from 1-" + boardSize);
                    continue;
                }

                if (gameboard.tileHasShip(y,x)){
                    System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                    continue;
                }

                Ship chosenShip = chooseAShip(scanner);
                System.out.println(String.format("What orientation do you want to place your %s?",  chosenShip.getName()));
                System.out.println("Place Horizontally = 1");
                System.out.println("Place Vertically = 2");

                int input = scanner.nextInt();
                placeShip(chosenShip, gameboard.setItemOrientation(input));

                gameboard.showGameBoard();

                System.out.println("press * to quit or Y to continue playing ");

                userInput = scanner.next();
                if (userInput.matches("(?i)[*]")){
                    System.out.println("\nQuitting the game");
                    isPlaying = false;
                    break;
                }
            }
        }



    }

    private void setupGame(){

        Scanner userInput = new Scanner(System.in);

        gameboard = new GameBoard(boardSize);
        boolean breakout;

        generateShips();

        breakout = false;
        while (!breakout) {

            System.out.println(String.format("Enter a letter A-%c: ", changeYToRowLabel(boardSize)));
            String letter = userInput.next();
            y = GameBoard.convertY(letter);

            //validate letter is in bounds
            if (y == -1 ){
                continue;
            }
            else if (y > boardSize || y < 0){
                System.out.println(String.format("You Must enter a letter from A-%c: \n\tStarting Over", changeYToRowLabel(boardSize)));
                continue;
            }

            System.out.println("Enter a Number 1-" + boardSize + ": ");
            x = Integer.parseInt(userInput.next());

            if (x > boardSize || x < 1) {
                System.out.println("You Must enter a number from 1-" + boardSize);
                continue;
            }

            if (gameboard.tileHasShip(y,x)){
                System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                continue;
            }


            Ship chosenShip = chooseAShip(userInput);
            System.out.println("Place Horizontally = 1");
            System.out.println("Place Vertically = 2");

            int input = userInput.nextInt();
            placeShip(chosenShip, gameboard.setItemOrientation(input));
           // gameboard.placeInGameTile( chosenShip, x, y);
            gameboard.showGameBoard();

//            placeShip(chosenShip, gameboard.setItemOrientation(chosenShip, 1));
            System.out.println("press * to quit or Y to continue playing ");
            String again = userInput.next();
            if (again.equalsIgnoreCase("*")){
                breakout = true;
            }
        }
    }

    private void generatePlayers() {
        players = new Player[numPlayers];
    }

    public static int changeYToRowLabel(int row){
        return  row + 64;
    }

    private void generateShips(){
        ships = new ArrayList<>(5);
        Ship carrier = new Carrier();
        Ship battleship = new Battleship();
        Ship cruiser = new Cruiser();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();

        ships.add(carrier);
        ships.add(battleship);
        ships.add(cruiser);
        ships.add(submarine);
        ships.add(destroyer);

    }

    private Ship chooseAShip(Scanner choice){
        int input = -1;
        int count = 0;
        boolean chooseShip = true;

        while (chooseShip) {
            System.out.println("\nWhat Ship do you want to place?");

            for (Ship ship : ships) {
                System.out.println(ship.getName() + " = " + count++);
            }
            System.out.println();
            input = Integer.parseInt(choice.next());

            if (ships.get(input).getPlacedStatus()) {
                System.out.println("\n\tThis ship has already been placed");
                count = 0;
            }else {
                chooseShip = false;
            }
        }

        return ships.get(input);
    }

    private void placeShip(Ship ship, String orientation){
        int shipXPlacementIndex = x;
        int shipYPlacementIndex = y;

        if (orientation.equalsIgnoreCase("Horizontal") && ship.getShipSize() + x <= boardSize){
            System.out.print("ship is Horizontal\n");
            for (int i = 0; i < ship.getShipSize(); i++) {
                gameboard.placeInGameTile(ship, shipXPlacementIndex++, shipYPlacementIndex);
            }
            ship.markAsPlaced();
            System.out.println("\nPlacing " + ship.getName());
        } else if (orientation.equalsIgnoreCase("Vertical") && ship.getShipSize() + y <= boardSize){
            System.out.print("ship is Vertical\n");
            for (int i = 0; i < ship.getShipSize(); i++) {
                gameboard.placeInGameTile(ship, shipXPlacementIndex, shipYPlacementIndex++);
            }
            ship.markAsPlaced();
            System.out.println("\nPlacing " + ship.getName());
        } else {
            System.out.print("ship has not been placed\n\tship out of bounds");
        }
    }


}
