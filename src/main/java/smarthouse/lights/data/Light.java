package smarthouse.lights.data;

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
    private int pinNumber;

    public LightDTO toLightDTO() {
        return LightDTO.builder()
                .id(id)
                .switchOn(switchOn)
                .build();
    }
}
