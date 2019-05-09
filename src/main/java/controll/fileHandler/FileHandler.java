package controll.fileHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

}
