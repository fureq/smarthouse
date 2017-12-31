package smarthouse.gpio.service;

import com.pi4j.io.gpio.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GpioService {

    private final GpioController gpioController;
    @Getter
    private Map<Integer, GpioPinDigitalOutput> lights;

    public GpioService() {
        gpioController = GpioFactory.getInstance();
        this.lights = new HashMap<>();
        mapLights();
    }

    public void changeState(int id) {
        GpioPinDigitalOutput light = lights.get(id);
        if (light.isHigh()) {
            light.low();
        } else {
            light.high();
        }
    }

    private void mapLights() {
        this.lights.put(1, gpioController.provisionDigitalOutputPin(
                        RaspiPin.GPIO_17,
                        PinState.LOW));
        this.lights.put(2, gpioController.provisionDigitalOutputPin(
                RaspiPin.GPIO_27,
                PinState.LOW));
    }
}
