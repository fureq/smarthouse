package smarthouse.lights.controller;

import org.springframework.web.bind.annotation.*;
import smarthouse.lights.data.Light;
import smarthouse.lights.service.LightsService;

import java.util.List;

@RestController
@RequestMapping("/lights")
public class LightsController {

    private final LightsService lightsService;

    public LightsController(LightsService lightsService) {
        this.lightsService = lightsService;
    }

    @GetMapping
    public List<Light> getLights() {
        return this.lightsService.getLights();
    }

    @GetMapping("/{lightId}")
    public Light getLight(@PathVariable("lightId") int lightId) {
        return this.lightsService.getLight(lightId);
    }

    @PostMapping("/{lightId}")
    public void setLight(@PathVariable("lightId") int lightId) {
        this.lightsService.switchLight(lightId);
    }
}
