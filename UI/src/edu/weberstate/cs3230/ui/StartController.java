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

    @FXML
    private Button btnStart, btnQuit;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            root = FXMLLoader.load(getClass().getResource("layout/start.fxml"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void onStartButtonClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        stage = (Stage) btnStart.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("layout/setup.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void onQuitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
