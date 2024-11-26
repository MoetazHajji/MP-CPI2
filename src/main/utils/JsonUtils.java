package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;

    public JsonUtils(String filePath) {
        this.filePath = filePath;
    }

    public List<T> readData(Class<T[]> clazz) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("Reading data from file: " + file.getAbsolutePath());
            System.out.println("File exists and is readable: " + file.canRead());

            T[] dataArray = objectMapper.readValue(file, clazz);
            return List.of(dataArray);
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
        return List.of();
    }


    public void writeData(List<T> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}
