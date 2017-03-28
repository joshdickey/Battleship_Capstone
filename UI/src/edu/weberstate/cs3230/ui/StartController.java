package edu.weberstate.cs3230.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by joshd on 3/27/2017.
 */
public class StartController implements Initializable{

    @FXML
    private Button btnStart, btnQuit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("setup.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void onStartButtonClicked(ActionEvent actionEvent) {

        Logger.getAnonymousLogger().fine("Start Button Clicked");


    }

    public void onQuitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
