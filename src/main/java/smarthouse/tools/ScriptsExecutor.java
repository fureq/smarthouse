package smarthouse.tools;

import smarthouse.dth.data.DthData;
import smarthouse.lights.data.Light;

import java.util.List;

public interface ScriptsExecutor {
    public List<Light> getLights();

    public Light getLight(int id);

    public void turnLightOn(int id);

    public void turnLightOf(int id);

    public DthData getDth();
}
