package controll.fileHandler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.*;
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
        log.info("Successfully readed data from file");

        closeReader(reader);

        return readTo;
    }

    @Override
    public Reader createReader() {
        InputStream inputStream = null;
        File file = new File(System.getProperty("user.home") + this.path);

        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if(!file.exists()){
            try {
                file.createNewFile();
                log.info("File successfully created");
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Cannot create file!");
            }
        }

        try {
            inputStream = new FileInputStream(System.getProperty("user.home") + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(inputStream);

        log.info("Reader successfully created");

        return reader;
    }

}
