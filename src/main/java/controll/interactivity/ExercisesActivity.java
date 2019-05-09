package controll.interactivity;

import controll.fileHandler.ExerciseFileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modell.Exercise;

import java.util.ArrayList;

public class ExercisesActivity extends ExerciseFileHandler implements interactivity{

    public ExercisesActivity(){
        readFromJson();
    }

    @Override
    public void listSetup(ListView listView, TextField textField){
        listView.getItems().addAll(Exercise.exerciseList);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.getSelectionModel().selectedItemProperty().addListener((p,oldval,nwval) ->
                textField.setText(String.valueOf(listView.getSelectionModel().getSelectedItem())));

        log.info("Successfully filled up the given list");

    }

    @Override
    public void getDaily(String key) {
        if(allTimeExercises.get(key) != null)
            dailyExerciseList = allTimeExercises.get(key);
        else
            dailyExerciseList = new ArrayList<>();
    }

    @Override
    public <T> void addNewElement(T objectToAdd,String key) {
        dailyExerciseList.add((Exercise)objectToAdd);

        allTimeExercises.put(key,dailyExerciseList);

        writeToJson();
    }


    @Override
    public void delElement(TableView tableView, String key) {

        ObservableList<Exercise> alldata,selectedData;

        alldata = tableView.getItems();

        selectedData = tableView.getSelectionModel().getSelectedItems();

        Exercise exercise = (Exercise)tableView.getSelectionModel().getSelectedItem();

        dailyExerciseList.remove(exercise);

        allTimeExercises.put(key,dailyExerciseList);

        writeToJson();

        selectedData.forEach(alldata::remove);
    }

    @Override
    public void fillTable(TableView tableView) {
        tableView.getItems().clear();

        ObservableList<Exercise> current = FXCollections.observableArrayList(dailyExerciseList);

        tableView.setItems(current);

        log.info("Table successfully filled");

    }
}
