package controll.interactivity;

import controll.fileHandler.FileHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface interactivity {

    void getDaily(String key);

    <T> void addNewElement(T objectToAdd,String key);

    void delElement(TableView tableView, String key);

    void fillTable(TableView tableView);

    void listSetup(ListView listView, TextField textField);
}
