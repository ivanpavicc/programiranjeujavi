package waterflow.sensor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeserializationTest {

    @Test
    public void testDeserializeValidJson() {
        String jsonFilePath = "src/main/resources/deserialization.json";
        Device device = Deserialization.deserialize(jsonFilePath);

        assertNotNull(device);
        assertEquals("ivan", device.getTopic());
        assertEquals("tcp://localhost:1883", device.getAddress());
        assertEquals(3, device.getSensors().size());

        Sensor sensor1 = device.getSensors().get(0);
        assertEquals("water temperature", sensor1.getName());
        assertEquals(-3266.8, sensor1.getLowerRange());
        assertEquals(3266.8, sensor1.getHigherRange());
        assertEquals(10, sensor1.getFactor());
        assertEquals("C", sensor1.getUnit());

        Sensor sensor2 = device.getSensors().get(1);
        assertEquals("water pressure", sensor2.getName());
        assertEquals(0, sensor2.getLowerRange());
        assertEquals(65.36, sensor2.getHigherRange());
        assertEquals(1000, sensor2.getFactor());
        assertEquals("Bar", sensor2.getUnit());

        Sensor sensor3 = device.getSensors().get(2);
        assertEquals("water usage", sensor3.getName());
        assertEquals(0, sensor3.getLowerRange());
        assertEquals(6533.6, sensor3.getHigherRange());
        assertEquals(10, sensor3.getFactor());
        assertEquals("m^3", sensor3.getUnit());
    }

    @Test
    public void testDeserializeInvalidJson() {
        String invalidFilePath = "src/main/resources/invalid.json";
        assertThrows(RuntimeException.class, () -> Deserialization.deserialize(invalidFilePath));
    }

    @Test
    public void testDeserializeMissingFile() {
        String missingFilePath = "src/main/resources/nonexistent.json";
        assertThrows(RuntimeException.class, () -> Deserialization.deserialize(missingFilePath));
    }
}
