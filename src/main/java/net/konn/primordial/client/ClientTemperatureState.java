package net.konn.primordial.client;

public final class ClientTemperatureState {
    public static final int MAX_HEAT = 140;
    private static int heatExposure;

    private ClientTemperatureState() {
    }

    public static void setHeatExposure(int value) {
        heatExposure = Math.clamp(value, 0, MAX_HEAT);
    }

    public static float getHeatPercent() {
        return heatExposure / (float) MAX_HEAT;
    }

    public static void reset() {
        heatExposure = 0;
    }
}
