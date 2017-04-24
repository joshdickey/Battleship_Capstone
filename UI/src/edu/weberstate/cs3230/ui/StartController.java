package edu.weberstate.cs3230.ui;

import javafx.application.Platform;
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
 * Created by joshd on 3/27/2017.
 */
public class StartController implements Initializable{

    private PageNavigationService pageNavigation;

    @FXML
    private Button btnStart, btnQuit;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void onStartButtonClicked(ActionEvent actionEvent) throws IOException {

        UIGame game = UIGame.getInstance();
        game.setBoardSize(11);

        pageNavigation = PageNavigationService.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/setup.fxml"));

        pageNavigation.setParent(loader.load());

    }

    public void onQuitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
