package controll.fileHandler;

import java.io.*;

public class JsonWriter implements WriteFile {

    private String filePath;

    public JsonWriter(String path) {
        this.filePath = path;
    }

    @Override
    public <T> void writeToJson(T toWrite) {

        Writer writer = createWriter();

        gson.toJson(toWrite,writer);
        log.info("Data successfully written to the file!");

        closeWriter(writer);
    }

    @Override
    public Writer createWriter() {

        FileOutputStream outStream = null;
        
        try {
            outStream = new FileOutputStream(System.getProperty("user.home") + this.filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        OutputStreamWriter writer= new OutputStreamWriter(outStream);
        log.info("Writer successfully created");

        return writer;
    }
}
