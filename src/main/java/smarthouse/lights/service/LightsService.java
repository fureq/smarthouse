package smarthouse.lights.service;

import org.springframework.stereotype.Service;
import smarthouse.lights.data.Light;
import smarthouse.lights.data.LightDTO;
import smarthouse.gpio.CmdExecutor;
import smarthouse.gpio.ScriptsExecutor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LightsService {

    private final ScriptsExecutor scriptsExecutor;

    public LightsService(CmdExecutor cmdExecutor) {
        this.scriptsExecutor = cmdExecutor;
    }

    public List<LightDTO> getLights() {
        return scriptsExecutor.getLights().stream().map(Light::toLightDTO).collect(Collectors.toList());
    }

    public LightDTO getLight(int id) {
        return scriptsExecutor.getLight(id).toLightDTO();
    }

    public void switchLight(int id) {
        try {
            this.scriptsExecutor.switchState(id);
        } catch (Exception e) {
            System.err.println("Cannot switch state. Reason: " + e.getMessage());
        }

    }
}
