package edu.weberstate.cs3230;

import edu.weberstate.cs3230.assets.*;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by joshd on 2/5/2017.
 */
public class ConsoleGame implements IGame{

    private int boardSize, numPlayers, x, y;
    private List<Player> players;
    private List<Ship> ships;
    private Scanner scanner;
    private String gamePhase;
    private Player attacker, defender;
    private String userInput;

    public ConsoleGame() {
        numPlayers = 2;
        boardSize = 10;
    }

    public void startGame(){
//        gameboard = new GameBoard(boardSize);
        generatePlayers(new Scanner(System.in));
        setupGame();
        startBattle(new Scanner(System.in));
    }

    void startConsoleGameFromFile(){
//        gameboard = new GameBoard(boardSize);
        generatePlayers(new Scanner(System.in));
        setupGameFromFile();
        startBattle(new Scanner(System.in));
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

        //generates ships into an array for each player
        for (Player player : players) {
            player.setPlayerShips(generateShips());
        }
//        generateShips();

//        int whoseTurn = 1;

        boolean isSetup = false;
        boolean isValidInput = true;


        while (!isSetup) {
            System.out.println("press * to quit anytime...");
            Logger logger = Logger.getLogger(Main.LOGGER_NAME);
            logger.setLevel(Level.FINE);

            File file;

            try {
                file = new File("C:\\Users\\jdickey\\IdeaProjects\\CS3230_Battleship\\src\\edu\\weberstate\\cs3230\\resources\\userInput.txt");

                scanner = new Scanner(new FileReader(file));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                break;
            }
            int placedShipCount = 0;
            if (placedShipCount < ships.size()) {
                updateGamePhase("setup");
            }


            while (isValidInput && placedShipCount < 10) {
                if (placedShipCount < 5) {
                    attacker = players.get(0);
                } else {
                    attacker = players.get(1);
                }

                if (getCoordinate()){
                    continue;
                }

                if (attacker.getGameboard().tileHasShip(x, y)) {
                    System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                    continue;
                }

                Ship chosenShip = chooseAShip(scanner, attacker);
                System.out.println(String.format("What orientation do you want to place your %s?", chosenShip.getName()));
                System.out.println("Place Horizontally = 1");
                System.out.println("Place Vertically = 2");

                int input = scanner.nextInt();
                placeShip(chosenShip, attacker.getGameboard().setItemOrientation(input), attacker.getGameboard());

                System.out.println("\n" + attacker.getName() + "'s board\n");
                attacker.getGameboard().showGameBoard();
                placedShipCount++;
//                System.out.println("press * to quit or Y to continue attacker ");


                if( placedShipCount == 10){
                    isSetup = true;
                    break;
                }

                userInput = scanner.next();
                if (userInput.matches("(?i)[*]")) {
                    System.out.println("\nQuitting the game");
                    isSetup = true;
                    break;
                }

            }
        }
    }

    private boolean getCoordinate() {
        System.out.println(String.format("\n" + attacker.getName() + " Enter a letter A-%c: ", changeYToRowLabel(boardSize)));

        userInput = scanner.next();
        System.out.println(userInput);
        y = GameBoard.convertY(userInput);

        //validate letter is in bounds
        if (validateY(y)) return true;

        System.out.println("\n" + attacker.getName() + " Enter a Number 1-" + boardSize + ": ");
        userInput = scanner.next();
        System.out.println(userInput);
        x = Integer.parseInt(userInput);

        if (validateX(x)) return true;

        return false;
    }

    private void startBattle(Scanner scanner){

        divider("==");
        System.out.println("\nStarting Battle");
        divider("==");
        updateGamePhase("battle");


        int roundCount = 0;
        int turnCount = 0;
        while (true) {
            turnCount++;

            if (turnCount % 2 != 0) {
                attacker = players.get(0);
                defender = players.get(1);
            }
            if (turnCount % 2 == 0) {
                attacker = players.get(1);
                defender = players.get(0);
            }
            if(turnCount % 2 != 0){
                roundCount++;
                divider("--");
                System.out.println("ROUND: " + roundCount);
                divider("--");
            }

            System.out.println("\n" + players.get(0).getName() + "'s board");
            divider("+-");
            players.get(0).getGameboard().showGameBoard();
            System.out.println("\n" + players.get(1).getName() + "'s board");
            divider("+-");
            players.get(1).getGameboard().showGameBoard();
            divider("+-");
            System.out.println(attacker.getName() + ": Fire a Missile..");

            int y = getCoordinateY();
            int x = getCoordinateX();

            fireMissile(x, y);

//            attacker.getGameboard().showGameBoard();

            if (defender.getPlayerShips().size() == 0){
                attacker.setWinner(true);
            }

            if(attacker.isWinner()){
                System.out.println(attacker.getName() + " is the winner!");
                break;
            }

        }


    }

    private void divider(String s) {
        System.out.println();
        for(int i = 0; i < 20; i++){
            System.out.print(s);
        }
        System.out.println();
    }

    private void updateGamePhase(String phaseName) {
        gamePhase = phaseName;

        for (Player player: players) {
            player.getGameboard().setGamePhase(gamePhase);
        }
    }

    private void setupGame(){

        scanner = new Scanner(System.in);

//        gameboard = new GameBoard(boardSize);
        boolean breakout;

        //generates ships into an array for each player
        for (Player player: players) {
            player.setPlayerShips(generateShips());
        }

        breakout = false;
        boolean isSetup = false;

        int placedShipCount = 0;
        if (placedShipCount < ships.size()) {
            updateGamePhase("setup");
            players.get(0).getGameboard().showGameBoard();
        }


        while (!isSetup) {

            if (placedShipCount < 5){
                attacker = players.get(0);
            }else {
                attacker = players.get(1);
            }

//            attacker.getGameboard().showGameBoard();

            if (getCoordinate()){
                continue;
            }
//            System.out.println(String.format("Enter a letter A-%c: ", changeYToRowLabel(boardSize)));
//            String letter = userInput.next();
//            y = GameBoard.convertY(letter);
//
//            if (y == -1 ){
//                continue;
//            }
//            else if (y > boardSize || y < 0){
//                System.out.println(String.format("You Must enter a letter from A-%c: \n\tStarting Over", changeYToRowLabel(boardSize)));
//                continue;
//            }
//
//            System.out.println("Enter a Number 1-" + boardSize + ": ");
//            x = Integer.parseInt(userInput.next());
//
//            if (x > boardSize || x < 1) {
//                System.out.println("You Must enter a number from 1-" + boardSize);
//                continue;
//            }

            if (attacker.getGameboard().tileHasShip(x,y)){
                System.out.print("This cell has already been hit,\n\tchose again!\n\n");
                continue;
            }


            Ship chosenShip = chooseAShip(scanner, attacker);

            System.out.println(String.format("What orientation do you want to place your %s?", chosenShip.getName()));

            System.out.println("Place Horizontally = 1");
            System.out.println("Place Vertically = 2");

            int input = scanner.nextInt();
            placeShip(chosenShip, attacker.getGameboard().setItemOrientation(input), attacker.getGameboard());
            placedShipCount++;

            System.out.println("\n" + attacker.getName() + "'s board\n");
           // gameboard.placeInGameTile( chosenShip, x, y);
            attacker.getGameboard().showGameBoard();

//            placeShip(chosenShip, gameboard.setItemOrientation(chosenShip, 1));
//            System.out.println("press * to quit or Y to continue ");

            if( placedShipCount == 10){
                isSetup = true;
                break;
            }

//            String again = scanner.next();
//            if (equalsIgnoreCase("*")){
//                isSetup = true;
//                break;
//            }
        }
    }

    private void generatePlayers(Scanner scanner) {
        players = new ArrayList<>(2);

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
            System.out.println("\nEnter name for Player " + (i+1) + ": ");
            String name = scanner.nextLine();
            players.get(i).setName(name);
            players.get(i).setGameboard(new GameBoard(boardSize));
        }
    }

    private static int changeYToRowLabel(int row){
        return  row + 64;
    }

    private List generateShips(){
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

        return ships;
    }

    private Ship chooseAShip(Scanner choice, Player player){
        int input = -1;
        int count = 0;
        boolean chooseShip = true;
        ships = player.getPlayerShips();

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

    private void placeShip(Ship ship, String orientation, GameBoard gameboard){
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

    private void fireMissile(int x, int y){

        if (defender.getGameboard().tileHasShip(x, y)){
            Ship ship = defender.getGameboard().getShipFromTile(x, y);
            System.out.println(ship.damageShip());
            if (ship.getStatus().equalsIgnoreCase("destroyed")){
                System.out.println("You sunk " + defender.getName() + "'s " + ship.getName());
                defender.removeDestroyedShip();
            }
//            gameBoard.markBoard(ship, x, y);
        }else {
            System.out.println("\nMISS!");
//            gameBoard.markBoard(ship, x, y);
            //for testing
        }
        defender.getGameboard().markBoard(x, y);


    }

    private int getCoordinateY() {
        int coordinateY;

        while (true) {
            System.out.println(String.format("\n" + attacker.getName() + " Enter a letter A-%c: ", changeYToRowLabel(boardSize)));

            userInput = scanner.next();
            System.out.println(userInput);
            coordinateY = GameBoard.convertY(userInput);

            //validate letter is in bounds
            if (!validateY(coordinateY)) break ;
        }

        return coordinateY;
    }

    private boolean validateY(int coordinateY) {
        if (coordinateY == -1) {
            return true;
        } else if (coordinateY > boardSize || coordinateY < 0) {
            System.out.println(String.format("You Must enter a letter from A-%c: \n", changeYToRowLabel(boardSize)));
            return true;
        }
        return false;
    }

    private boolean validateX(int coordinateX) {
        if (coordinateX > boardSize || coordinateX < 1) {
            System.out.println("You Must enter a number from 1-" + boardSize);
            return true;
        }
        return false;
    }

    private int getCoordinateX() {
        int coordinateX;

        while (true){
            try {
                System.out.println("\n" + attacker.getName() + " Enter a Number 1-" + boardSize + ": ");
                userInput = scanner.next();
                System.out.println(userInput);
                coordinateX = Integer.parseInt(userInput);

                if (!validateX(coordinateX)) break;
            } catch (NumberFormatException | InputMismatchException e) {
                e.printStackTrace();
            }

        }
        return coordinateX;
    }

}
