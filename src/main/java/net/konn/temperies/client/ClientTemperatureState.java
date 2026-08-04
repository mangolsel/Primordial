package net.konn.temperies.client;

import net.konn.temperies.temperature.TemperatureConstants;
import net.minecraft.util.Mth;

public final class ClientTemperatureState {
    private static final float SMOOTHING = 0.08F;

    private static int targetHeatExposure;
    private static int targetColdExposure;

    private static float displayedHeatExposure;
    private static float displayedColdExposure;

    private ClientTemperatureState() {
    }

    public static void setExposure(
            int heatExposure,
            int coldExposure
    ) {
        targetHeatExposure = Mth.clamp(
                heatExposure,
                0,
                TemperatureConstants.MAX_EXPOSURE
        );

        targetColdExposure = Mth.clamp(
                coldExposure,
                0,
                TemperatureConstants.MAX_EXPOSURE
        );
    }

    public static void tick() {
        displayedHeatExposure +=
                (targetHeatExposure
                        - displayedHeatExposure)
                        * SMOOTHING;

        displayedColdExposure +=
                (targetColdExposure
                        - displayedColdExposure)
                        * SMOOTHING;

        if (Math.abs(
                targetHeatExposure
                        - displayedHeatExposure
        ) < 0.01F) {
            displayedHeatExposure =
                    targetHeatExposure;
        }

        if (Math.abs(
                targetColdExposure
                        - displayedColdExposure
        ) < 0.01F) {
            displayedColdExposure =
                    targetColdExposure;
        }
    }

    public static float getHeatPercent() {
        return Mth.clamp(
                displayedHeatExposure
                        / TemperatureConstants
                        .MAX_EXPOSURE,
                0.0F,
                1.0F
        );
    }

    public static float getColdPercent() {
        return Mth.clamp(
                displayedColdExposure
                        / TemperatureConstants
                        .MAX_EXPOSURE,
                0.0F,
                1.0F
        );
    }

    public static void reset() {
        targetHeatExposure = 0;
        targetColdExposure = 0;

        displayedHeatExposure = 0.0F;
        displayedColdExposure = 0.0F;
    }
}
