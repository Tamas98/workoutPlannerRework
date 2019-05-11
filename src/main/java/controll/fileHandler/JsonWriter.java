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

        closeWriter(writer);
    }

    @Override
    public Writer createWriter() {



        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(FileHandler.class.getResource(this.filePath).getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        OutputStreamWriter writer= new OutputStreamWriter(outStream);

        return writer;
    }
}
