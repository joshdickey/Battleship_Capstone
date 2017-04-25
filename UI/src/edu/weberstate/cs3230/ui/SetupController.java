package edu.weberstate.cs3230.ui;


import com.sun.prism.paint.Color;
import edu.weberstate.cs3230.engine.Player;
import edu.weberstate.cs3230.engine.Ship;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableArray.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.sun.prism.paint.Color.GREEN;
import static com.sun.prism.paint.Color.RED;

/**
 * Created by jdickey on 4/24/2017.
 */
public class SetupController implements Initializable{

    @FXML
    Button btnNextPlayer, btnDone, btnSaveName1, btnSaveName2, btnPlaceShipPlayer1, btnPlaceShipPlayer2;
    @FXML
    private ComboBox<String> ships1, xCords1, yCords1, orientation1,ships2, xCords2, yCords2, orientation2;

    @FXML
    GridPane gridPlayer1, gridPlayer2;
    @FXML
    TextField player1Name, player2Name;
    @FXML
    Label txtStatus, txtStatusTwo;


    Player player1, player2;
    List<Ship> player1Ships, player2Ships;
    UIGame game;
    private List<String> playerTwoChoice, playerOneChoice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1Ships = new ArrayList<>();
        player2Ships = new ArrayList<>();
        playerTwoChoice = new ArrayList<>();
        playerOneChoice = new ArrayList<>();


    }

    private void setupPlayer2() {
        ships2.getItems().addAll("Carrier", "BattleShip", "Cruiser", "Submarine", "Destroyer");
//        ships2.setPromptText("Ships");

        for (int i = 1; i <=10; i++){
            xCords2.getItems().add("" +i);
        }

        yCords2.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        orientation2.getItems().addAll("Vertical", "Horizontal");
        game.getPlayers().get(1).getGameboard().setGamePhase("setup");
    }

    private void setupPlayer1() {

        ships1.getItems().addAll("Carrier", "BattleShip", "Cruiser", "Submarine", "Destroyer");
//        ships1.setPromptText("Ships");

        for (int i = 1; i <=10; i++){
            xCords1.getItems().add("" +i);

        }
        yCords1.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        orientation1.getItems().addAll("Vertical", "Horizontal");
        game.getPlayers().get(0).getGameboard().setGamePhase("setup");

    }


    public void onPlaceShipPlayerOneButtonClicked(ActionEvent actionEvent) throws IOException{
        boolean placed = false;
        String shipName = ships1.getValue();
        int index = ships1.getItems().indexOf(shipName);

        Ship shipChoice = null;

        for (Ship ship : player1Ships) {
            if (ship.getName().equalsIgnoreCase(shipName)){
                 shipChoice = ship;
            }
        }


        String xCord = xCords1.getValue();
        String yCord = yCords1.getValue();

        String cords = xCord + yCord;

        if(playerOneChoice.contains(cords)){
            status("Choose another Coordinate");
            return;
        }else {
            playerOneChoice.add(cords);
        }


        String orientation = orientation1.getValue();
        int x = xCords1.getItems().indexOf(xCord) + 1;
        int y = yCords1.getItems().indexOf(yCord) + 1;

        placed = game.placeShip(shipChoice, x, y - 1, orientation, player1.getGameboard());


        ships1.getItems().remove(shipName);
        if (ships1.getItems().isEmpty()){
            btnPlaceShipPlayer1.setDisable(true);
            btnNextPlayer.setDisable(false);
        }
//        setGridinGridpane();


        if (placed) {
            int count = 0;
            for (Node node : gridPlayer1.getChildren()) {
                if (orientation.equalsIgnoreCase("Horizontal")) {
                    while(count < shipChoice.getShipSize()) {
                        int col = count + x;
                        Node cell = getNodeFromGridPane(gridPlayer1, col, y);
                        if (cell != null){
                            cell.setUserData(shipChoice);
                            Rectangle rect = (Rectangle) cell;
                            rect.setFill(Paint.valueOf("#00ff00"));
                        }
    //                    cell.setStyle("-fx-background-color: aqua");
    //                    cell.setUserData(shipChoice);
    //                    node.setStyle("-fx-background-color: aqua");
                        count++;
                    }
                }
                if(orientation.equalsIgnoreCase("Vertical")){
                    while(count < shipChoice.getShipSize()) {
                        int row = count + y;
                        Node cell = getNodeFromGridPane(gridPlayer1, x, row);
                        if (cell != null) {
                            cell.setUserData(shipChoice);
                            Rectangle rect = (Rectangle) cell;
                            rect.setFill(Paint.valueOf("#00ff00"));
                        }
                        count++;
                    }
                }
            }
        }


    }

    private void status(String s) {
        txtStatus.setText(s);
    }

    public void onPlaceShipPlayerTwoButtonClicked(ActionEvent actionEvent) throws IOException{
        boolean placed = false;
        String shipName = ships2.getValue();
        int index = ships2.getItems().indexOf(shipName);

        Ship shipChoice = null;

        for (Ship ship : player2Ships) {
            if (ship.getName().equalsIgnoreCase(shipName)){
                shipChoice = ship;
            }
        }

        String xCord = xCords2.getValue();
        String yCord = yCords2.getValue();

        String cords = xCord + yCord;

        if(playerTwoChoice.contains(cords)){
            statusTwo("Choose another Coordinate");
            return;
        }else {
            playerTwoChoice.add(cords);
        }

        String orientation = orientation2.getValue();
        int x = xCords2.getItems().indexOf(xCord) + 1;
        int y = yCords2.getItems().indexOf(yCord) + 1;

        placed = game.placeShip(shipChoice, x, y - 1, orientation, player2.getGameboard());

        ships2.getItems().remove(shipName);
        if (ships2.getItems().isEmpty()){
            btnPlaceShipPlayer2.setDisable(true);
            btnDone.setDisable(false);
        }

//        setGridinGridpane();


        if (placed) {
            int count = 0;
            for (Node node : gridPlayer2.getChildren()) {
                if (orientation.equalsIgnoreCase("Horizontal")) {
                    while(count < shipChoice.getShipSize()) {
                        int col = count + x;
                        Node cell = getNodeFromGridPane(gridPlayer2, col, y);
                        if (cell != null){
                            cell.setUserData(shipChoice);
                            Rectangle rect = (Rectangle) cell;
                            rect.setFill(Paint.valueOf("#00ff00"));
                        }
                        //                    cell.setStyle("-fx-background-color: aqua");
                        //                    cell.setUserData(shipChoice);
                        //                    node.setStyle("-fx-background-color: aqua");
                        count++;
                    }
                }
                if(orientation.equalsIgnoreCase("Vertical")){
                    while(count < shipChoice.getShipSize()) {
                        int row = count + y;
                        Node cell = getNodeFromGridPane(gridPlayer2, x, row);
                        if (cell != null) {
                            cell.setUserData(shipChoice);
                            Rectangle rect = (Rectangle) cell;
                            rect.setFill(Paint.valueOf("#00ff00"));
                        }
                        count++;
                    }
                }
            }
        }


    }

    private void setGridinGridpane() {
        ObservableList<Node> children = gridPlayer1.getChildren();

        gridPlayer1 = (GridPane)game.tiles();


    }


    public void onNextPlayerButtonClicked(ActionEvent actionEvent) throws IOException {

        PageNavigationService pageNavigation = PageNavigationService.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/setup_player_two.fxml"));

        pageNavigation.setParent(loader.load());

    }

    public void onDoneButtonClicked(ActionEvent actionEvent) throws IOException{

        PageNavigationService navigationService = PageNavigationService.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/game.fxml"));

        Stage stage = navigationService.getPrimaryStage();

        stage.setScene(new Scene(loader.load(), 1000, 400));


        navigationService.setPrimaryStage(stage);


    }

    public void onSavePlayerOneNameButtonClicked(ActionEvent actionEvent) throws  IOException{
        player1 = new Player();
        player1.setName(player1Name.getText());

        game = UIGame.getInstance();
        game.addPlayer(player1);

        player1Ships = player1.getPlayerShips();

        btnSaveName1.setDisable(true);
        status("Place Ships");
        btnPlaceShipPlayer1.setDisable(false);
        setupPlayer1();
    }

    public void onSavePlayerTwoNameButtonClicked(ActionEvent actionEvent) throws  IOException{
        player2 = new Player();
        player2.setName(player2Name.getText());

        game = UIGame.getInstance();
        game.addPlayer(player2);

        player2Ships = player2.getPlayerShips();
        statusTwo("Place Ships");
        btnSaveName2.setDisable(true);
        btnPlaceShipPlayer2.setDisable(false);
        setupPlayer2();
    }

    private void statusTwo(String s) {
        txtStatusTwo.setText(s);
    }

    private Node getNodeFromGridPane(GridPane gridPane, Integer col, Integer row) {
        for (Node node : gridPane.getChildren()) {
            if (Objects.equals(gridPane.getColumnIndex(node), col) && Objects.equals(gridPane.getRowIndex(node), row)) {
                return node;
            }
        }
        return null;
    }



}
