package controll.interactivity;

import controll.fileHandler.AlternateWriter;
import controll.fileHandler.JsonWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import modell.Exercise;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class ExercisesActivity implements interactivity{

    public Map<String,ArrayList<Exercise>> allTimeExercises;

    public ArrayList<Exercise> dailyExerciseList;

    private JsonWriter jsonWriter = new JsonWriter("/Assets/exercises.json");

    @Override
    public void getDaily(String key) {
        if(allTimeExercises.get(key) != null) {
            dailyExerciseList = allTimeExercises.get(key);
            log.info("Successfully retrieved daily exercises");
        }else {
            dailyExerciseList = new ArrayList<>();
            log.info("A new exercises list is created.");
        }
    }

    @Override
    public <T> void addNewElement(T objectToAdd,String key) {
        dailyExerciseList.add((Exercise)objectToAdd);

        allTimeExercises.put(key,dailyExerciseList);

        jsonWriter.writeToJson(allTimeExercises);

        log.info("Successfully added new element!");
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

        log.info("Element successfully removed");
    }

    @Override
    public <T> void fillTable(TableView tableView) {
        tableView.getItems().clear();

        ObservableList<Exercise> current = FXCollections.observableArrayList(dailyExerciseList);

        tableView.setItems(current);

        log.info("Table successfully filled");
    }
}
