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
    Button btnNextPlayer, btnDone, btnSaveName1, btnSavename2;
    @FXML
    private ComboBox<String> ships1, xCords1, yCords1, orientation1,ships2, xCords2, yCords2, orientation2;

    @FXML
    GridPane gridPlayer1, gridPlayer2;
    @FXML
    TextField player1Name, player2Name;

    Player player1, player2;
    List<Ship> player1Ships, player2Ships;
    UIGame game;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1Ships = new ArrayList<>();
        player2Ships = new ArrayList<>();

        setupPlayer1();


    }

    private void setupPlayer2() {
        ships2.getItems().addAll("Carrier", "BattleShip", "Cruiser", "Submarine", "Destroyer");
        ships2.setPromptText("Ships");

        for (int i = 1; i <=10; i++){
            xCords2.getItems().add("" +i);
        }

        yCords2.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        orientation2.getItems().addAll("Vertical", "Horizontal");
    }

    private void setupPlayer1() {

        ships1.getItems().addAll("Carrier", "BattleShip", "Cruiser", "Submarine", "Destroyer");
        ships1.setPromptText("Ships");

        for (int i = 1; i <=10; i++){
            xCords1.getItems().add("" +i);

        }
        yCords1.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        orientation1.getItems().addAll("Vertical", "Horizontal");

    }


    public void onPlaceShipButtonClicked(ActionEvent actionEvent) throws IOException{

        String shipName = ships1.getValue();
        int index = ships1.getItems().indexOf(shipName);

        Ship shipChoice = player1Ships.get(index);

        ships1.getItems().remove(shipName);

        String xCord = xCords1.getValue();
        String yCord = yCords1.getValue();
        String orientation = orientation1.getValue();
        int x = xCords1.getItems().indexOf(xCord) + 1;
        int y = yCords1.getItems().indexOf(yCord) + 1;

        game.placeShip(shipChoice, x +1, y, orientation, player1.getGameboard());

//        setGridinGridpane();


        int count = 0;
        for (Node node : gridPlayer1.getChildren()) {
            if (orientation.equalsIgnoreCase("Horizontal")) {
                while(count < shipChoice.getShipSize()) {
                    int col = count + x;
                    Node cell = getNodeFromGridPane(gridPlayer1, col, y);
                    if (cell != null){
                        cell.setUserData(shipChoice);
                    }
//                    cell.setStyle("-fx-background-color: aqua");
//                    cell.setUserData(shipChoice);
//                    node.setStyle("-fx-background-color: aqua");
                    count++;
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
        setupPlayer2();

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
    }

    private Node getNodeFromGridPane(GridPane gridPane, Integer col, Integer row) {
        for (Node node : gridPane.getChildren()) {
            if (Objects.equals(gridPlayer1.getColumnIndex(node), col) && Objects.equals(gridPlayer1.getRowIndex(node), row)) {
                Rectangle rect = (Rectangle) node;
                rect.setFill(Paint.valueOf("#00ff00"));

                return node;
            }
        }
        return null;
    }



}
