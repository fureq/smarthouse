package smarthouse.dth.service;

import org.springframework.stereotype.Service;
import smarthouse.dth.data.DthData;
import smarthouse.tools.MockedData;
import smarthouse.tools.ScriptsExecutor;

@Service
public class DthService {
    private final ScriptsExecutor scriptsExecutor;

    public DthService(MockedData mockedData) {
        this.scriptsExecutor = mockedData;
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
