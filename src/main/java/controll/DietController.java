package controll;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DietController implements Initializable {
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

    }
}
