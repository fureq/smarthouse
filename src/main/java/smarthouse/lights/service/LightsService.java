package smarthouse.lights.service;

import org.springframework.stereotype.Service;
import smarthouse.lights.data.Light;
import smarthouse.tools.MockedData;
import smarthouse.tools.ScriptsExecutor;

import java.util.List;

@Service
public class LightsService {

    private final ScriptsExecutor scriptsExecutor;

    public LightsService(MockedData mockedData) {
        this.scriptsExecutor = mockedData;
    }

    public List<Light> getLights() {
        return scriptsExecutor.getLights();
    }

    public Light getLight(int id) {
        return scriptsExecutor.getLight(id);
    }

    public void switchLight(int id) {
        Light light = scriptsExecutor.getLight(id);
        if (light.isSwitchOn()) {
            scriptsExecutor.turnLightOf(id);
        } else {
            scriptsExecutor.turnLightOn(id);
        }
    }
}
