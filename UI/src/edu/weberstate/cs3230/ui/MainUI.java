package edu.weberstate.cs3230.ui;
/**
 * Created by jdickey on 3/22/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("layout/start.fxml"));
        stage.setTitle("Battleship");
        stage.setScene(new Scene(root, 600, 400));
        stage.setAlwaysOnTop(true);
        stage.setResizable(true);


        PageNavigationService.getInstance().setPrimaryStage(stage);
        stage.show();

    }
}
