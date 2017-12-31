package smarthouse.gpio;

import smarthouse.dth.data.DthData;
import smarthouse.lights.data.Light;
import smarthouse.lights.data.LightDTO;

import java.io.IOException;
import java.util.List;

public interface ScriptsExecutor {
    public DthData getDth();
    public List<Light> getLights();
    public Light getLight(int id);
    public void switchState(int id) throws IOException, InterruptedException;
}
