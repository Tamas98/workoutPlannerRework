package controll.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DietEvaulationController extends DietController implements Initializable {

    @FXML
    private Label calorieSum;

    @FXML
    private Label carboSum;

    @FXML
    private Label sugarSum;

    @FXML
    private Label fatSum;

    @FXML
    private Label proteinSum;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  null if the location is not known.
     * @param resources The resources used to localize the root object, or null
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calorieSum.setText(String.valueOf(todaysFoods.getCalories()));
        carboSum.setText(String.valueOf(todaysFoods.getCarbo()));
        sugarSum.setText(String.valueOf(todaysFoods.getSugar()));
        fatSum.setText(String.valueOf(todaysFoods.getFat()));
        proteinSum.setText(String.valueOf(todaysFoods.getProtein()));
    }
}
