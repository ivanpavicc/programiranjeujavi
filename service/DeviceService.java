package service;

import model.Device;
import repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper;

    public Device findByName(String name) {
        return deviceRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Device not found with name: " + name));
    }

    public Device findByIdWithRecords(Long deviceId) {
        return deviceRepository.findByIdWithRecords(deviceId)
                .orElseThrow(() -> new NoSuchElementException("Device not found with ID: " + deviceId));
    }

    public Device saveDevice() {
        try {
            File file = new File("src/main/resources/device.json");
            Device device = objectMapper.readValue(file, Device.class);
            return deviceRepository.save(device);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading device data from device.json and saving to the database.", e);
        }
    }
}
