package waterflow.sensor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Deserialization {

    public static Device deserialize(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), Device.class);
        } catch (IOException e) {
            System.err.println("Error during JSON deserialization: " + e.getMessage());
            throw new RuntimeException("Failed to deserialize JSON file.", e);
        }
    }
}
