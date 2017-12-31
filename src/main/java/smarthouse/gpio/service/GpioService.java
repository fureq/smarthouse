package smarthouse.gpio.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.springframework.stereotype.Service;

@Service
public class GpioService {

    private final GpioController gpioController;
    

    public GpioService() {
        gpioController = GpioFactory.getInstance();
    }
}
