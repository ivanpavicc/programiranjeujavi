package controller;

import service.RecordService;
import model.Record;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @PostMapping("/{deviceId}")
    public Record addRecord(@PathVariable Long deviceId) {
        return recordService.addRecord(deviceId);
    }
}
