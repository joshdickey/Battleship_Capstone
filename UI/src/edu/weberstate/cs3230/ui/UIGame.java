package edu.weberstate.cs3230.ui;

import edu.weberstate.cs3230.engine.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdickey on 4/24/2017.
 */
public class UIGame implements IGame{

    private static UIGame gameInstance;
    private List<Player> players;
    private List<Ship> ships;
    private int boardSize;
    private String gameStatus;

    private UIGame() {
        players = new ArrayList<>();
        ships = new ArrayList<>();
    }

    public static UIGame getInstance(){
        if(gameInstance == null){
            gameInstance = new UIGame();
        }

        return gameInstance;
    }


    @Override
    public void startGame() {

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

    public void addPlayer(Player player){
        player.setGameboard(new GameBoard(boardSize));
        player.setPlayerShips(generateShips());
        players.add(player);

    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Ship> getShips() {
        return ships;
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

    public boolean placeShip(Ship ship, int x, int y, String orientation, GameBoard gameBoard){
        int shipXPlacementIndex = x;
        int shipYPlacementIndex = y ;
        boolean placed = false;

        if (orientation.equalsIgnoreCase("horizontal") && ship.getShipSize() + x <= boardSize+1){
            for(int i = 0; i < ship.getShipSize(); i++) {
                gameBoard.placeInGameTile(ship, shipXPlacementIndex++, y);
            }
            ship.markAsPlaced();
            placed = true;
        }else if (orientation.equalsIgnoreCase("vertical") && ship.getShipSize() + y <= boardSize){
            for(int i = 0; i < ship.getShipSize(); i++) {
                gameBoard.placeInGameTile(ship, x, shipYPlacementIndex++);
            }
            ship.markAsPlaced();
            placed = true;
        }else{
            System.out.print("ship has not been placed\n\tship out of bounds");
        }
        gameBoard.showGameBoard();
        return placed;
    }

    @FXML
    GridPane gridPlayer1;

    private StackPane[][] backdrop;

    public Node tiles(){

        GridPane board = new GridPane();
        for (int col = 0; col < boardSize + 1; col++){
            for (int row = 0; row < boardSize + 1; row++){
                StackPane tile = new StackPane();
                tile.setStyle("-fx-background-color: #FFFFFF00");
//                backdrop[row][col] = tile;
                board.add(tile, col, row);
            }
        }
        return board;
    }

    public boolean fireMissile(Player defender, int x, int y){

        boolean hit = false;
        Ship ship = null;
        if (defender.getGameboard().tileHasShip(x, y)){
            ship = defender.getGameboard().getShipFromTile(x, y);
            System.out.println(ship.damageShip());
            hit = true;
            setGameStatus("Hit!");
            if (ship.getStatus().equalsIgnoreCase("destroyed")){
                setGameStatus("You sunk " + defender.getName() + "'s " + ship.getName());
                System.out.println("You sunk " + defender.getName() + "'s " + ship.getName());
                defender.removeDestroyedShip();

            }
//            gameBoard.markBoard(ship, x, y);
        }else {
            System.out.println("\nMISS!");
            setGameStatus("Miss!");
//            gameBoard.markBoard(ship, x, y);
            //for testing
        }
        defender.getGameboard().markBoard(x, y);

        defender.getGameboard().showGameBoard();
        return hit;
    }

    public void setGameStatus(String status){
        gameStatus = status;
    }

    public String getGameStatus(){
        if (gameStatus.isEmpty()){
            gameStatus = "No Status";
        }
        return gameStatus;
    }

    public boolean cantPlaceShip(Ship ship, int x, int y, String orientation, GameBoard gameBoard){
        boolean cantPlace = false;

        if (orientation.equalsIgnoreCase("horizontal") && ship.getShipSize() + x <= boardSize+1){
            for(int i = 0; i < ship.getShipSize(); i++) {
                cantPlace =  gameBoard.tileHasShip(x + i,y);
            }

        }else if (orientation.equalsIgnoreCase("vertical") && ship.getShipSize() + y <= boardSize){
            for(int i = 0; i < ship.getShipSize(); i++) {
                cantPlace =  gameBoard.tileHasShip(x ,y + i);
            }

        }else{
            System.out.print("ship has not been placed");
        }

        return cantPlace;

    }
}
