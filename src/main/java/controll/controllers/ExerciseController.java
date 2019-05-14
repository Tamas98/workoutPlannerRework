package controll.controllers;

import com.google.gson.reflect.TypeToken;
import controll.Window;
import controll.fileHandler.JsonReader;
import controll.fileHandler.ReadFile;
import controll.interactivity.ExercisesActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modell.Exercise;
import modell.Logic;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ExerciseController extends ExercisesActivity implements Initializable {

    private Type token = new TypeToken<Map<String, ArrayList<Exercise>>>(){}.getType();

    private ReadFile jsonReader = new JsonReader("/Assets/exercises.json",token);

    private Logic logic = new Logic();

    @FXML
    private ListView<String> list;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField repsField;

    @FXML
    private TextField nameField;

    @FXML
    private HBox editTable;

    @FXML
    private CheckMenuItem editTableButton;

    @FXML
    private TableView table;

    static int todayExercises;

    static double caloreisBurnt;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  null if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allTimeExercises = jsonReader.readFromJson(allTimeExercises);

        ifNull();

        editTableButton.setSelected(true);

        datePicker.setValue(LocalDate.now());
        getDaily(LocalDate.now().toString());

        fillTable(table);

        listSetup(list,nameField,Exercise.exerciseList);

    }

    @FXML
    protected void AddElement() {
        Exercise exercise = new Exercise(nameField.getText(),Integer.parseInt(repsField.getText()));

        table.getItems().add(exercise);

        addNewElement(exercise,datePicker.getValue().toString());

        nameField.setText("");
        repsField.setText("");
    }

    @FXML
    private void deleteElement(){
        delElement(table,datePicker.getValue().toString());
    }

    @FXML
    protected void setEditable() {
        if (editTableButton.isSelected()) {
            editTable.setVisible(true);
        } else {
            editTable.setVisible(false);
        }
    }


    @FXML
    public void setDate(){
        getDaily(datePicker.getValue().toString());
        fillTable(table);
    }

    private void ifNull(){
        if(allTimeExercises == null)
            allTimeExercises=new HashMap<>();
    }

    @FXML
    private void evaulateDay(){
        todayExercises = logic.sumDailyReps(dailyExerciseList);
        caloreisBurnt = todayExercises*0.75;

        Window window = new Window();

        Stage stage = window.createWindow("/GUI/ExerciseEvaulationWindow.fxml","Exercises on " + datePicker.getValue().toString(),600,400);

        stage.setResizable(false);

        stage.show();
    }


}
