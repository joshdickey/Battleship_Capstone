package edu.weberstate.cs3230.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jdickey on 4/24/2017.
 */
public class SetupController implements Initializable {

    @FXML
    Button btnNextPlayer, btnDone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/game.fxml"));

//        navigationService.setParent(loader.load());
        navigationService.setPrimaryStage(stage);


    }
}
