package controller;

import service.DeviceService;
import model.Device;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/{name}")
    public Device getDeviceByName(@PathVariable String name) {
        return deviceService.findByName(name);
    }

    @GetMapping("/{deviceId}/records")
    public Device getDeviceWithRecords(@PathVariable Long deviceId) {
        return deviceService.findByIdWithRecords(deviceId);
    }

    @PostMapping
    public Device saveDevice() {
        return deviceService.saveDevice();
    }
}
