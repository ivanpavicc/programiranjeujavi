package waterflow.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    private Sensor sensor;


    @BeforeEach
    public void setUp() {
        sensor = new Sensor("Test Sensor", 0, 50, 1, "unit");
    }

    @Test
    public void testGetSensorData() {
        // Očekivana vrijednost na temelju trenutnog stanja senzora
        String expected = "Device: Test Sensor value: " + sensor.getValue() + " factor: " + sensor.getFactor() + " unit: " + sensor.getUnit();
        String actual = sensor.getSensorData();
        // Provjera je li stvarna vrijednost jednaka očekivanoj
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValueWithinRange() {
        // Provjera da li je vrijednost unutar zadanog opsega
        double lowerRange = sensor.getLowerRange();
        double higherRange = sensor.getHigherRange();
        double value = sensor.getValue();

        // Testiranje da li je vrijednost unutar raspona
        assertTrue(value >= lowerRange && value <= higherRange);
    }

    @Test
    public void testHigherLower() {
        // Provjera da li je viša vrijednost zaista veća od niže
        double lowerRange = sensor.getLowerRange();
        double higherRange = sensor.getHigherRange();
        double value = sensor.getValue();

        // Testiranje da li je viša vrijednost veća od niže
        assertTrue(higherRange > lowerRange);
    }
}
