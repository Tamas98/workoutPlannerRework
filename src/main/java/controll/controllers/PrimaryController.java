package controll.controllers;

import controll.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryController {

    private Window window = new Window();

    @FXML
    public Button workoutButton;

    @FXML
    public Button dietButton;

    @FXML
    public Button exitButton;

    @FXML
    public void exitClicked(){

        exitButton.setDisable(true);

        window.confirmBox("EXIT");

        exitButton.setDisable(false);
    }

    @FXML
    private void openWorkout(){
        workoutButton.setDisable(true);

        Stage stage = window.createWindow("/GUI/ExerciseWindow.fxml","Workout Planner",600,450);

        stage.setResizable(false);

        stage.setOnCloseRequest(e-> workoutButton.setDisable(false));

        stage.show();
    }

    @FXML
    private void openDiet(){
        dietButton.setDisable(true);

        Stage stage = window.createWindow("/GUI/DietWindow.fxml","Diet Planner",765,550);

        stage.setResizable(false);

        stage.setOnCloseRequest(e-> dietButton.setDisable(false));

        stage.show();
    }
}
