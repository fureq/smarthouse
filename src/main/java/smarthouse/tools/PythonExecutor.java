package smarthouse.tools;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smarthouse.dth.data.DthData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class PythonExecutor implements ScriptsExecutor {

    private DthData dthData;
    private static String line;
    private static String[] data;
    static int humidity=0;
    static int temperature=0;

    public PythonExecutor() {
        dthData = DthData.builder()
                .temperature(-500)
                .humidity(-1)
                .build();
    }

    @Override
    public DthData getDth() {
        return null;
    }

    @Scheduled(fixedRate = 5000)
    public void updateDth() throws Exception{
        System.err.println("Start");
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("pwd");
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        if((line = bri.readLine()) != null){
            if(!(line.contains("ERR_CRC") || line.contains("ERR_RNG"))){

//                data=line.split("ABC");
                System.out.println(line);
//                temperature=Integer.parseInt(data[0]);
//                humidity=Integer.parseInt(data[1]);
            }
            else
                System.out.println("Data Error");
        }

        bri.close();
        p.waitFor();
//        System.out.println("Temperature is : "+temperature+" 'C Humidity is :"+ humidity+" %RH");
        System.out.println("Done.");
    }
}
