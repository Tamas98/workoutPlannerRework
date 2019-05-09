package controll;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modell.Exercise;

import java.net.URL;
import java.time.LocalDate;

import java.util.ResourceBundle;


public class ExerciseController implements Initializable {

    private FileHandler fileHandler = new ExerciseFileHandler();

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
        fileHandler.readFromJson();

        datePicker.setValue(LocalDate.now());
        fileHandler.getDaily(LocalDate.now().toString());

        fileHandler.fillTable(table);
    }

    @FXML
    protected void AddElement() {
        Exercise exercise = new Exercise(nameField.getText(),Integer.parseInt(repsField.getText()));

        table.getItems().add(exercise);

        fileHandler.addNewElement(exercise,datePicker.getValue().toString());

        nameField.setText("");
        repsField.setText("");
    }

    @FXML
    private void deleteElement(){
        fileHandler.delElement(table,datePicker.getValue().toString());
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
        fileHandler.getDaily(datePicker.getValue().toString());
        fileHandler.fillTable(table);
    }


}
