package controll.fileHandler;

import java.io.*;

public class AlternateWriter implements WriteFile{

    private String path;

    private File file;

    public AlternateWriter(File selectedFile){
        this.path = selectedFile.getPath();
        this.file = selectedFile;
    }

    @Override
    public <T> void writeToJson(T toWrite) {
        Writer writer = createWriter();

        gson.toJson(toWrite,writer);

        closeWriter(writer);
    }

    @Override
    public Writer createWriter() {
        System.out.println(file);
        Writer writer = null;
        if(this.file == null){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer;
    }
}
