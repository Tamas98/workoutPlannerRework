package controll;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.NoArgsConstructor;
import modell.Exercise;

import javafx.scene.control.TableView;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseFileHandler implements FileHandler {

    private Map<String, ArrayList<Exercise>> allTimeExercises;

    private ArrayList<Exercise> dailyExerciseList;

    private Type type = new TypeToken<Map<String, ArrayList<Exercise>>>(){}.getType();

    public ExerciseFileHandler(){
        this.dailyExerciseList = new ArrayList<>();
    }

    @Override
    public void writeToJson() {

        Writer writer = null;

        try{
            writer = new FileWriter(Exercise.class.getResource("/Assets/exercises.json").getFile());
            log.info("Reader successfully created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("A problem has occurred while opening the reader");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("A problem has occurred while opening the reader");
        }

        gson.toJson(allTimeExercises,writer);

        closeWriter(writer);

    }

   /* @Override
    public Writer writeExceptionHandler(Writer writer) {
        try{
            writer = new FileWriter(Exercise.class.getResource("/Assets/exercises.json").getFile());
            log.info("Reader successfully created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("A problem has occurred while opening the reader");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("A problem has occurred while opening the reader");
        }

        return writer;
    }*/

    @Override
    public void closeWriter(Writer writer) {
        try {
            writer.flush();
            writer.close();
            log.info("Writer successfully closed");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("A problem has occurred while closing the writer");
        }
    }

    @Override
    public void readFromJson() {
        Reader reader = null;

        try{
            reader = new FileReader(Exercise.class.getResource("/Assets/exercises.json").getFile());
            log.info("Reader successfully created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("A problem has occurred while opening the reader");
        }

        this.allTimeExercises = gson.fromJson(reader,type);

        this.allTimeExercises = ifNullSetup(this.allTimeExercises);

        closeReader(reader);
    }


    @Override
    public void closeReader(Reader reader) {
        try {
            reader.close();
            log.info("Reader successfully closed");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("A problem has occurred while closing the reader");
        }
    }

    @Override
    public void getDaily(String key) {
        if(this.allTimeExercises.get(key) != null)
            dailyExerciseList = this.allTimeExercises.get(key);
        else
            dailyExerciseList = new ArrayList<>();
    }

    @Override
    public <T> void addNewElement(T objectToAdd,String key) {
        this.dailyExerciseList.add((Exercise)objectToAdd);

        this.allTimeExercises.put(key,this.dailyExerciseList);

        System.out.println(this.allTimeExercises);

        writeToJson();
    }


    @Override
    public void delElement(TableView tableView,String key) {

        ObservableList<Exercise> alldata,selectedData;

        alldata = tableView.getItems();

        selectedData = tableView.getSelectionModel().getSelectedItems();

        Exercise exerc = (Exercise)tableView.getSelectionModel().getSelectedItem();

        dailyExerciseList.remove(exerc);

        this.allTimeExercises.put(key, this.dailyExerciseList);

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

    private Map<String, ArrayList<Exercise>> ifNullSetup(Map<String, ArrayList<Exercise>> value){

        if(value == null){
            log.info("The file was empty...Initializing");
            value = new HashMap<>();
            return value;
        }else{
            log.info("Data was found in the file");
            return value;
        }

    }

    public void createNewInstance(String name,String reps) {}
}
