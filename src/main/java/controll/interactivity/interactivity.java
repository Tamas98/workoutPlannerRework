package controll.interactivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modell.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface interactivity {

    Logger log = LoggerFactory.getLogger(interactivity.class);

    void getDaily(String key);

    <T> void addNewElement(T objectToAdd,String key);

    void delElement(TableView tableView, String key);

    <T> void fillTable(TableView tableView);

    default <T> void listSetup(ListView listView, TextField textField, T[] fillWith){
        listView.getItems().addAll(fillWith);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.getSelectionModel().selectedItemProperty().addListener((p,oldval,nwval) ->
                textField.setText(String.valueOf(listView.getSelectionModel().getSelectedItem())));

        log.info("Successfully filled up the given list");
    }

    default void emptyFields(TextField... fields){
        for(TextField field:fields){
            field.setText("");
        }
    }

}
