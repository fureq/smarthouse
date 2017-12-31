package smarthouse.lights.service;

import org.springframework.stereotype.Service;
import smarthouse.gpio.service.GpioService;
import smarthouse.lights.data.Light;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LightsService {

    private final GpioService gpioService;

    public LightsService(GpioService gpioService) {
        this.gpioService = gpioService;
    }

    public List<Light> getLights() {
        return gpioService.getLights().entrySet().stream()
                .map(entry -> Light.fromGpioPinDigitalOutput(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Light getLight(int id) {
        return Light.fromGpioPinDigitalOutput(id, gpioService.getLights().get(id));
    }

    public void switchLight(int id) {
        gpioService.changeState(id);
    }
}
