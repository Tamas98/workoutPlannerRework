package controll.interactivity;

import com.google.gson.reflect.TypeToken;
import controll.fileHandler.JsonReader;
import controll.fileHandler.JsonWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modell.Exercise;
import modell.Food;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class FoodActivity implements interactivity{

    public Map<String, ArrayList<Food>> allTimeMenu;

    public ArrayList<Food> dailyFoodList;

    public ArrayList<Food> basicFoodList;

    private JsonWriter jsonWriter = new JsonWriter("/Assets/food.json");

    @Override
    public void getDaily(String key) {
        if(allTimeMenu.get(key) != null)
            dailyFoodList = allTimeMenu.get(key);
        else
            dailyFoodList = new ArrayList<>();
    }

    @Override
    public <T> void addNewElement(T objectToAdd, String key) {
        dailyFoodList.add((Food)objectToAdd);

        allTimeMenu.put(key,dailyFoodList);

        jsonWriter.writeToJson(allTimeMenu);
    }

    @Override
    public void delElement(TableView tableView, String key) {
        ObservableList<Exercise> alldata,selectedData;

        alldata = tableView.getItems();

        selectedData = tableView.getSelectionModel().getSelectedItems();

        Exercise exercise = (Exercise)tableView.getSelectionModel().getSelectedItem();

        dailyFoodList.remove(exercise);

        allTimeMenu.put(key,dailyFoodList);

        jsonWriter.writeToJson(allTimeMenu);

        selectedData.forEach(alldata::remove);
    }

    @Override
    public <T> void fillTable(TableView tableView) {
        tableView.getItems().clear();

        ObservableList<Food> current = FXCollections.observableArrayList(dailyFoodList);

        tableView.setItems(current);

        log.info("Table successfully filled");
    }

    public void readInBasicFoods(){
        Type type = new TypeToken<ArrayList<Food>>(){}.getType();

        JsonReader jsonReader = new JsonReader("/Assets/basic_food.json",type);

        basicFoodList = jsonReader.readFromJson(basicFoodList);
    }
}
