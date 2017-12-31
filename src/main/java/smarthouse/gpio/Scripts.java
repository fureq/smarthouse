package smarthouse.gpio;

class Scripts {

    public static String setHighStateScript(int pin) {
        return "gpio -g write " + pin + " 1";
    }

    public static String setLowStateScript(int pin) {
        return "gpio -g write " + pin + " 0";
    }

    public static String setOutputModeScript(int pin) {
        return "gpio -g mode " + pin + " out";
    }

    public static String getStateScript(int pin) {
        return "gpio -g read " + pin;
    }
}
