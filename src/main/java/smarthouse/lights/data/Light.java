package smarthouse.lights.data;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Light {
    private int id;
    private boolean switchOn;

    public static Light fromGpioPinDigitalOutput(int id, GpioPinDigitalOutput gpioPinDigitalOutput) {
        return Light.builder()
                .id(id)
                .switchOn(gpioPinDigitalOutput.isHigh())
                .build();
    }
}
