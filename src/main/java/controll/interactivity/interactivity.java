package controll.interactivity;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface interactivity {

    Logger log = LoggerFactory.getLogger(interactivity.class);

    void fillContainer(Object object);

    void getDaily(String key);

    <T> void addNewElement(T objectToAdd,String key);

    void delElement(TableView tableView, String key);

    void fillTable(TableView tableView);

    void listSetup(ListView listView, TextField textField);

}
