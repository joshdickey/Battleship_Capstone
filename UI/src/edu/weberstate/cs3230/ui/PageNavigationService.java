package edu.weberstate.cs3230.ui;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Created by jdickey on 4/24/2017.
 */
public class PageNavigationService {

    private  static PageNavigationService mNavigationService;
    private Stage primaryStage;
    private Parent parent;


    public static PageNavigationService getInstance() {
        if (mNavigationService == null){
            mNavigationService = new PageNavigationService();
        }

        return mNavigationService;
    }

    public PageNavigationService() {
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setParent(Parent root){

        primaryStage.getScene().setRoot(root);

    }

    public Parent getParent() {
        return parent;
    }
}
