package controll.interactivity;

import controll.fileHandler.JsonWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;
import modell.Exercise;

import java.util.ArrayList;
import java.util.Map;

public class ExercisesActivity implements interactivity{

    public Map<String,ArrayList<Exercise>> allTimeExercises;

    public ArrayList<Exercise> dailyExerciseList;

    private JsonWriter jsonWriter = new JsonWriter("/Assets/exercises.json");

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

        jsonWriter.writeToJson(allTimeExercises);
    }


    @Override
    public void delElement(TableView tableView, String key) {

        ObservableList<Exercise> alldata,selectedData;

        alldata = tableView.getItems();

        selectedData = tableView.getSelectionModel().getSelectedItems();

        Exercise exercise = (Exercise)tableView.getSelectionModel().getSelectedItem();

        dailyExerciseList.remove(exercise);

        allTimeExercises.put(key,dailyExerciseList);

        jsonWriter.writeToJson(allTimeExercises);

        selectedData.forEach(alldata::remove);
    }

    @Override
    public void fillTable(TableView tableView) {
        tableView.getItems().clear();

        ObservableList<Exercise> current = FXCollections.observableArrayList(dailyExerciseList);

        tableView.setItems(current);

        log.info("Table successfully filled");

    }

    @Override
    public void fillContainer(Object allTimeExercises) {
        this.allTimeExercises = (Map<String, ArrayList<Exercise>>) allTimeExercises;
    }
}
