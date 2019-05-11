package controll.fileHandler;

import java.io.Writer;

public interface WriteFile extends FileHandler {

    <T> void writeToJson(T toWrite);

    Writer createWriter();

}
