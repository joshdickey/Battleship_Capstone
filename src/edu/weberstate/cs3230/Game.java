package edu.weberstate.cs3230;

import edu.weberstate.cs3230.assets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by joshd on 2/5/2017.
 */
public class Game {

    private int boardSize, numPlayers, x, y;
    private Player[] players;
    private List<Ship> ships;
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
        gameboard = new GameBoard(boardSize);
        setupGame();
        generatePlayers();

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
                System.out.println(String.format("You Must enter a letter from A-%c: ", changeYToRowLabel(boardSize)));
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
            System.out.println("press Q to quit or Y to continue playing ");
            String  again = userInput.next();
            if (again.equalsIgnoreCase("q")){
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
            System.out.println("What Ship do you want to place?");

            for (Ship ship : ships) {
                System.out.println(ship.getName() + " = " + count++);
            }

            input = Integer.parseInt(choice.next());

            if (ships.get(input).getPlacedStatus()) {
                System.out.println("This ship has already been placed");
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
        } else if (orientation.equalsIgnoreCase("Vertical") && ship.getShipSize() + y <= boardSize){
            System.out.print("ship is Vertical\n");
            for (int i = 0; i < ship.getShipSize(); i++) {
                gameboard.placeInGameTile(ship, shipXPlacementIndex, shipYPlacementIndex++);
            }
            ship.markAsPlaced();
        } else {
            System.out.print("ship is not placed\n");
        }
    }


}
