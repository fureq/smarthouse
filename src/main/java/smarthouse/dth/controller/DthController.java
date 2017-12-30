package smarthouse.dth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthouse.dth.data.DthData;
import smarthouse.dth.service.DthService;
import smarthouse.tools.MockedData;
import smarthouse.tools.ScriptsExecutor;

@RestController
@RequestMapping("/dth")
public class DthController {

    private final DthService dthService;

    public DthController(DthService dthService) {
        this.dthService = dthService;
    }

    @GetMapping
    public DthData getDthData() {
        return dthService.getDthData();
    }

    @GetMapping("/temperature")
    public double getTemperature() {
        return dthService.getTemperature();
    }

    @GetMapping("/humidity")
    public double getHumidity() {
        return dthService.getHumditity();
    }
}
