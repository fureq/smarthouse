package smarthouse.dth.service;

import org.springframework.stereotype.Service;
import smarthouse.dth.data.DthData;
import smarthouse.gpio.CmdExecutor;
import smarthouse.gpio.ScriptsExecutor;

@Service
public class DthService {
    private final ScriptsExecutor scriptsExecutor;

    public DthService(CmdExecutor cmdExecutor) {
        this.scriptsExecutor = cmdExecutor;
    }

    public DthData getDthData() {
        return scriptsExecutor.getDth();
    }

    public double getTemperature() {
        return scriptsExecutor.getDth().getTemperature();
    }

    public double getHumditity() {
        return scriptsExecutor.getDth().getHumidity();
    }
}
