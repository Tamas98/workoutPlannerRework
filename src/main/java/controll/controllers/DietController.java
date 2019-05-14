package controll.controllers;

import com.google.gson.reflect.TypeToken;
import controll.Window;
import controll.fileHandler.JsonReader;
import controll.fileHandler.ReadFile;
import controll.interactivity.FoodActivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modell.Food;
import modell.Logic;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class DietController extends FoodActivity implements Initializable {

    private Type token = new TypeToken<Map<String, ArrayList<Food>>>(){}.getType();

    private ReadFile jsonReader = new JsonReader("/Assets/food.json",token);

    private Logic logic = new Logic();

    static Food todaysFoods;

    @FXML
    private Button delButton;

    @FXML
    private Button evaulateButton;

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
     *                  null if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allTimeMenu = jsonReader.readFromJson(allTimeMenu);

        Food.basicFoodsArrayList = readInBasicFoods();

        if(Food.basicFoodsArrayList == null){
            Food.basicFoodsArrayList = new ArrayList<>(Arrays.asList(
                    new Food("Fish",1,1,1,1,1,1),
                    new Food("Chicken",1,1,1,1,1,1)
            ));
        }

        ifNull();

        datePicker.setValue(LocalDate.now());
        getDaily(LocalDate.now().toString());

        fillTable(foodTable);

        listSetup(foodList,nameField);

        unitSelector.getItems().add("g");
        unitSelector.getItems().add("dkg");
        unitSelector.setValue("g");
    }

    @FXML
    public void delClicked(){
        delElement(foodTable,datePicker.getValue().toString());
    }

    @FXML
    private void datePicked(){
        getDaily(datePicker.getValue().toString());
        fillTable(foodTable);
    }

    @FXML
    private void calculateAndAdd(){

        int unit = logic.getUnit(unitSelector.getValue().toString());

        Food choosenFood = logic.findFood(nameField.getText());
        choosenFood = logic.calculateFood(choosenFood,logic.converter(eatField.getText())*unit);

        addNewElement(choosenFood,datePicker.getValue().toString());

        foodTable.getItems().add(choosenFood);

        emptyFields(nameField,eatField);
    }

    @FXML
    private void addElement(){

        Food newFood = Food.builder().name(newName.getText())
                .calories(logic.converter(calorieField.getText())/100)
                .carbo(logic.converter(carboField.getText())/100)
                .sugar(logic.converter(sugarField.getText())/100)
                .fat(logic.converter(fatField.getText())/100)
                .protein(logic.converter(proteinField.getText())/100)
                .build();

        foodList.getItems().add(newName.getText());
        Food.basicFoodsArrayList.add(newFood);

        emptyFields(newName,calorieField,carboField,sugarField,fatField,proteinField);

        saveBasicFoods();
    }

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
            message.setText("Enter the foods macros in 100g ");
        }
    }

    private void ifNull(){
        if(allTimeMenu == null)
            allTimeMenu=new HashMap<>();
    }

    @FXML
    private void closeWindow(){

    }

    @FXML
    private void evaulateDay(){

        Window window = new Window();

        todaysFoods = logic.evaulateDay(dailyFoodList);

        Stage stage = window.createWindow("/GUI/DietEvaulationWindow.fxml","Exercises on " + datePicker.getValue().toString(),600,400);

        stage.setResizable(false);

        stage.setOnCloseRequest(e-> evaulateButton.setDisable(false));

        stage.show();
    }

    @FXML
    private void showAbout(){}
}
