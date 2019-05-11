package controll.fileHandler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

@AllArgsConstructor
@NoArgsConstructor
public class JsonReader implements ReadFile {

    private String path;

    private Type type;

    @Override
    public <T> T readFromJson(T readTo) {
        Reader reader = createReader();

        readTo = gson.fromJson(reader,type);

        closeReader(reader);

        return readTo;
    }

    @Override
    public Reader createReader() {
        InputStream inputStream = FileHandler.class.getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(inputStream);

        return reader;
    }

}
