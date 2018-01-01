package smarthouse.gpio;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smarthouse.dth.data.DthData;
import smarthouse.lights.data.Light;
import smarthouse.lights.data.LightDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CmdExecutor implements ScriptsExecutor {

    private DthData dthData;
    private Map<Integer, Light> lightMap;

    public CmdExecutor() {
        dthData = DthData.builder()
                .temperature(-500)
                .humidity(-1)
                .build();
        lightMap = this.mappLights();
        setMode();
    }

    private Map<Integer, Light> mappLights() {
        Map<Integer, Light> lightMap = new HashMap<>();
        lightMap.put(1, Light.builder()
                .id(1)
                .pinNumber(17)
                .switchOn(false)
                .build());
        lightMap.put(2, Light.builder()
                .id(2)
                .pinNumber(27)
                .switchOn(false)
                .build());
        return lightMap;
    }

    private void setMode() {
        try {
            for (Light light: lightMap.values()) {
                execute(Scripts.setOutputModeScript(light.getPinNumber()));
            }
        } catch (Exception e) {
            System.err.println("Cannot set output mode. Reason: " + e.getMessage());
        }
    }

    @Override
    public DthData getDth() {
        return dthData;
    }

    @Override
    public List<Light> getLights() {
        return new ArrayList<>(lightMap.values());
    }

    @Override
    public Light getLight(int id) {
        return lightMap.get(id);
    }

    @Override
    public void switchState(int id) throws IOException, InterruptedException {
        Light light = getLight(id);
        if (light.isSwitchOn()) {
            execute(Scripts.setLowStateScript(light.getPinNumber()));
            light.setSwitchOn(false);
        } else {
            execute(Scripts.setHighStateScript(light.getPinNumber()));
            light.setSwitchOn(true);
        }
    }

    @Scheduled(fixedRate = 300000)
    public void updateDth() throws Exception {
        String output = execute("python ./pythonscript/script.py");
        if(output.equals("Data Error")) {
            System.err.println("Cannot read dth parameters");
            return;
        }
        String[] data = output.split(",");
        this.dthData.setTemperature(Double.parseDouble(data[0]));
        this.dthData.setHumidity(Double.parseDouble(data[1]));
        System.out.println("Dth data updated");
    }

    private String execute(String command) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(command);
        try (BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            if ((line = bri.readLine()) != null) {
                if (!(line.contains("ERR_CRC") || line.contains("ERR_RNG"))) {
                    return line;
                }
            }
        } finally {
            p.waitFor();
        }
        return "Data Error";
    }
}
