package edu.weberstate.cs3230.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jdickey on 3/22/2017.
 */
public class Controller implements Initializable{
    @FXML
    private TextField taskDescription;
    @FXML
    private ComboBox<String> priorities;
    @FXML
    private Spinner<Integer> spinnerProgress;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priorities.getItems().addAll("High", "Medium", "Low");
        spinnerProgress.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,50,0));

        spinnerProgress.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue.intValue() == 50){
                    checkBox.setSelected(true);
                }else{
                    checkBox.setSelected(false);
                }
                progressBar.setProgress(1.0 * newValue / 50);

            }
        });
    }
}
