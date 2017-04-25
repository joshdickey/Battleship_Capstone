package edu.weberstate.cs3230.ui;

import edu.weberstate.cs3230.engine.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by jdickey on 4/24/2017.
 */
public class GameController implements Initializable {


    private Player attacker, defender;
    private UIGame game;
    private List<Player> playerList;

    @FXML
    ComboBox<String> player1X, player1Y, player2X, player2Y;
    @FXML
    Button btnFirePlayer1, btnFirePlayer2;
    @FXML
    Label txtTurn, txtPlayerOne, txtPlayerTwo;
    @FXML
    GridPane playerOneGrid, playerTwoGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = UIGame.getInstance();


        for (Player player : game.getPlayers()) {
            player.getGameboard().setGamePhase("battle");
        }

        playerList = game.getPlayers();

        setUpComboBox();
        setActivePlayer();
        txtPlayerOne.setText(playerList.get(0).getName());
        txtPlayerTwo.setText(playerList.get(1).getName());


    }

    private void setActivePlayer() {
        if (attacker == null) {
            attacker = playerList.get(0);
            defender = playerList.get(1);

            btnFirePlayer1.setDisable(false);
            btnFirePlayer2.setDisable(true);

        }else if (attacker == playerList.get(0)){
            attacker = playerList.get(1);
            defender = playerList.get(0);

            btnFirePlayer1.setDisable(true);
            btnFirePlayer2.setDisable(false);

        }else {
            attacker = playerList.get(0);
            defender = playerList.get(1);

            btnFirePlayer1.setDisable(false);
            btnFirePlayer2.setDisable(true);
        }
        txtTurn.setText(attacker.getName() + "'s turn");


    }

    private void setUpComboBox() {
        for (int i = 1; i <=10; i++){
            player1X.getItems().add("" +i);
            player2X.getItems().add("" +i);
        }

        player1Y.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        player2Y.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    }

    public void playerTwoFire(ActionEvent actionEvent) throws IOException {
        String xCord = player2X.getValue();
        String yCord = player2Y.getValue();

        int x = player2X.getItems().indexOf(xCord) + 1;
        int y = player2Y.getItems().indexOf(yCord) + 1;

        markBoard(defender, x, y);
        setActivePlayer();

    }
    public void playerOneFire(ActionEvent actionEvent) throws  IOException{
        String xCord = player1X.getValue();
        String yCord = player1Y.getValue();

        int x = player1X.getItems().indexOf(xCord) + 1;
        int y = player1Y.getItems().indexOf(yCord) + 1;

        markBoard(defender, x, y);
        setActivePlayer();

    }

    private void markBoard(Player defender, int x, int y){
        boolean hit = false;
        hit = game.fireMissile(defender, x, y -1);

        Node cell = null;
        if (defender == game.getPlayers().get(0)){
            cell = getNodeFromGridPane(playerOneGrid, x, y);
        }else if (defender == game.getPlayers().get(1)){
            cell = getNodeFromGridPane(playerTwoGrid, x, y);
        }
        if (cell != null){
            Rectangle rect = (Rectangle) cell;
            if (hit) {
                rect.setFill(Paint.valueOf("#ff0000"));
            }else {
                rect.setFill(Paint.valueOf("#000000"));
            }
        }

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
