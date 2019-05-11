package controll.fileHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modell.Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface FileHandler {

    Logic logic = new Logic();

    Logger log = LoggerFactory.getLogger(FileHandler.class);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    default void closeReader(Reader reader){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void closeWriter(Writer writer){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
