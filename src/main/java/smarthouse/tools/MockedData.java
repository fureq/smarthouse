package smarthouse.tools;

import org.springframework.stereotype.Service;
import smarthouse.dth.data.DthData;
import smarthouse.lights.data.Light;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MockedData implements ScriptsExecutor {

    private Map<Integer, Light> lights;

    public MockedData() {
        lights = new HashMap<>();
        lights.put(1, Light.builder().id(1).switchOn(false).build());
        lights.put(2, Light.builder().id(2).switchOn(true).build());
    }

//    public List<Light> getLights() {
//        return new ArrayList<>(lights.values());
//    }
//
//    public Light getLight(int id) {
//        return lights.get(id);
//    }
//
//    public void turnLightOn(int id) {
//        lights.get(id).setSwitchOn(true);
//    }
//
//    public void turnLightOf(int id) {
//        lights.get(id).setSwitchOn(false);
//    }

    @Override
    public DthData getDth() {
        return DthData.builder()
                .humidity(Math.random() * 100)
                .temperature(Math.random() * 100)
                .build();
    }
}
