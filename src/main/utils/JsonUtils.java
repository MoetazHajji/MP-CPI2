package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void deleteById(int id, Class<T[]> clazz) throws IOException {
        List<T> data = new ArrayList<>(readData(clazz));
        boolean removed = data.removeIf(item -> {
            try {
                return objectMapper.convertValue(item, Map.class).get("id").equals(id);
            } catch (Exception e) {
                System.out.printf("Error checking ID: %s%n", e.getMessage());
                return false;
            }
        });
        if (removed) {
            writeData(data);
            System.out.println("Deleted item with ID: " + id);
        } else {
            System.out.println("No item found with ID: " + id);
        }
    }

    public T getById(int id, Class<T[]> clazz) throws IOException {
        List<T> data = readData(clazz);
        for (T item : data) {
            try {
                if (objectMapper.convertValue(item, Map.class).get("id").equals(id)) {
                    return item;
                }
            } catch (Exception e) {
                System.out.printf("Error fetching ID: %s%n", e.getMessage());
            }
        }
        System.out.println("No item found with ID: " + id);
        return null;
    }

    public void modify(String id, T newData, Class<T[]> clazz) throws IOException {
        List<T> data = new ArrayList<>(readData(clazz));
        boolean updated = false;
        for (int i = 0; i < data.size(); i++) {
            T item = data.get(i);
            try {
                if (objectMapper.convertValue(item, Map.class).get("id").equals(id)) {
                    data.set(i, newData);
                    updated = true;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error modifying ID: %s%n", e.getMessage());
            }
        }
        if (updated) {
            writeData(data);
            System.out.println("Updated item with ID: " + id);
        } else {
            System.out.println("No item found with ID: " + id);
        }
    }
}
