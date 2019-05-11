package controll;

import com.google.gson.reflect.TypeToken;
import controll.fileHandler.JsonReader;
import controll.fileHandler.ReadFile;
import controll.interactivity.FoodActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modell.Food;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DietController extends FoodActivity implements Initializable {

    private Type token = new TypeToken<Map<String, ArrayList<Food>>>(){}.getType();

    private ReadFile jsonReader = new JsonReader("/Assets/food.json",token);


    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView foodTable;

    @FXML
    private ListView<String> foodList;

    @FXML
    private ChoiceBox unitSelector;

    @FXML
    private RadioMenuItem addNewItem;

    @FXML
    private RadioMenuItem chooseItem;

    @FXML
    private Button calcAndAddButton;

    @FXML
    private Button addButton;

    @FXML
    private Label message;

    @FXML
    private HBox chooseBox;

    @FXML
    private HBox newFoodBox;

    @FXML
    private  TextField newName;
    @FXML
    private TextField nameField;
    @FXML
    private TextField eatField;
    @FXML
    private TextField sugarField;
    @FXML
    private TextField calorieField;
    @FXML
    private TextField carboField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField fatField;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allTimeMenu = jsonReader.readFromJson(allTimeMenu);

        readInBasicFoods();

        ifNull();

        datePicker.setValue(LocalDate.now());
        getDaily(LocalDate.now().toString());

        fillTable(foodTable);

        listSetup(foodList,nameField,Food.basicFoodsArrayList.toArray());
    }

    @FXML
    private void datePicked(){}

    @FXML
    private void calculateAndAdd(){}

    @FXML
    private void addElement(){}

    public void showChooseBox() {
        if(chooseItem.isSelected()){
            newFoodBox.setVisible(false);
            addButton.setVisible(false);
            chooseBox.setVisible(true);
            calcAndAddButton.setVisible(true);
            message.setText("Choose the element from the list!");
        }
    }

    public void showAddElement() {
        if(addNewItem.isSelected()) {
            chooseBox.setVisible(false);
            calcAndAddButton.setVisible(false);
            newFoodBox.setVisible(true);
            addButton.setVisible(true);
            message.setText("Enter the foods propeties ");
        }
    }

    private void ifNull(){
        if(allTimeMenu == null)
            allTimeMenu=new HashMap<>();
    }
}
