package controll.fileHandler;

import com.google.gson.reflect.TypeToken;

import lombok.Data;
import modell.Exercise;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExerciseFileHandler implements FileHandler {

    protected Map<String, ArrayList<Exercise>> allTimeExercises = new HashMap<>();

    protected ArrayList<Exercise> dailyExerciseList;

    private Type type = new TypeToken<Map<String, ArrayList<Exercise>>>(){}.getType();

    public ExerciseFileHandler(){
        this.dailyExerciseList = new ArrayList<>();
    }

    @Override
    public void writeToJson() {

        Writer writer = null;

        try{
            writer = new FileWriter(Exercise.class.getResource("/Assets/exercises.json").getFile());
            log.info("Writer successfully created");
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

  /*  public Map<String, ArrayList<Exercise>> getAllTimeExercises() {
        return allTimeExercises;
    }

    public void setAllTimeExercises(Map<String, ArrayList<Exercise>> allTimeExercises) {
        this.allTimeExercises = allTimeExercises;
    }

    public ArrayList<Exercise> getDailyExerciseList() {
        return dailyExerciseList;
    }

    public void setDailyExerciseList(ArrayList<Exercise> dailyExerciseList) {
        this.dailyExerciseList = dailyExerciseList;
    }*/
}
