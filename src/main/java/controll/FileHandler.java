package controll;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.TableView;
import java.io.Reader;
import java.io.Writer;

public interface FileHandler {

    Logger log = LoggerFactory.getLogger(FileHandler.class);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    void writeToJson();

    void closeWriter(Writer writer);

    void readFromJson();

    void closeReader(Reader reader);

    void getDaily(String key);

    <T> void addNewElement(T objectToAdd,String key);

    void delElement(TableView tableView,String key);

    void fillTable(TableView tableView);


}
