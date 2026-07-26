package net.konn.primordial.client;

public final class ClientTemperatureState {
    public static final int MAX_HEAT = 140;

    private static int targetHeatExposure;

    private static float displayedHeatExposure;

    private static final float SMOOTHING = 0.08F;

    private ClientTemperatureState() {
    }

    public static void setHeatExposure(int value) {
        targetHeatExposure = Math.clamp(value, 0, MAX_HEAT);
    }

    public static void updateDisplayedHeat() {
        displayedHeatExposure +=
                (targetHeatExposure - displayedHeatExposure) * SMOOTHING;

        if (Math.abs(targetHeatExposure - displayedHeatExposure) < 0.01F) {
            displayedHeatExposure = targetHeatExposure;
        }
    }

    public static float getHeatPercent() {
        return displayedHeatExposure / MAX_HEAT;
    }

    public static void reset() {
        targetHeatExposure = 0;
        displayedHeatExposure = 0.0F;
    }
}
