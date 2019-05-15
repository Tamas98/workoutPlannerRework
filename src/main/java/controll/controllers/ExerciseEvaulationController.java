package controll.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;


public class ExerciseEvaulationController extends ExerciseController implements Initializable {

    @FXML
    private Label caloriesBurn;

    @FXML
    private Label exercisesDone;

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
        caloriesBurn.setText(String.valueOf(caloreisBurnt));
        exercisesDone.setText(String.valueOf(todayExercises));
    }
}
