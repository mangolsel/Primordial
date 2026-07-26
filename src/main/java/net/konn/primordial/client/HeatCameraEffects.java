package net.konn.primordial.client;

import net.konn.primordial.PrimordialMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(
        modid = PrimordialMod.MOD_ID,
        value = Dist.CLIENT)
public class HeatCameraEffects {
    /*
     * При полном перегреве FOV уменьшается примерно на 6%.
     */
    private static final float MAX_FOV_REDUCTION = 0.06F;

    /*
     * Максимальные отклонения камеры в градусах.
     * Значения специально небольшие.
     */
    private static final float MAX_YAW_SHAKE = 0.14F;
    private static final float MAX_PITCH_SHAKE = 0.11F;
    private static final float MAX_ROLL_SHAKE = 0.18F;

    private HeatCameraEffects() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null
                || minecraft.level == null) {
            ClientTemperatureState.reset();
            return;
        }

        ClientTemperatureState.tick();
    }

    @SubscribeEvent
    public static void onComputeFov(
            ComputeFovModifierEvent event
    ) {
        float strength = getEffectStrength();

        if (strength <= 0.0F) {
            return;
        }

        float multiplier = Mth.lerp(
                strength,
                1.0F,
                1.0F - MAX_FOV_REDUCTION
        );

        event.setNewFovModifier(
                event.getNewFovModifier() * multiplier
        );
    }


    @SubscribeEvent
    public static void onComputeCameraAngles(
            ViewportEvent.ComputeCameraAngles event
    ) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null
                || minecraft.level == null) {
            return;
        }

        float strength = getEffectStrength();

        if (strength <= 0.0F) {
            return;
        }

        double time =
                minecraft.player.tickCount
                        + event.getPartialTick();


        float yawWave =
                Mth.sin((float) (time * 0.23D)) * 0.72F
                        + Mth.sin((float) (time * 0.71D))
                        * 0.28F;

        float pitchWave =
                Mth.sin((float) (time * 0.19D + 1.4D))
                        * 0.70F
                        + Mth.sin((float) (time * 0.83D))
                        * 0.30F;

        float rollWave =
                Mth.sin((float) (time * 0.16D + 2.2D))
                        * 0.78F
                        + Mth.sin((float) (time * 0.57D))
                        * 0.22F;

        event.setYaw(
                event.getYaw()
                        + yawWave
                        * MAX_YAW_SHAKE
                        * strength
        );

        event.setPitch(
                event.getPitch()
                        + pitchWave
                        * MAX_PITCH_SHAKE
                        * strength
        );

        event.setRoll(
                event.getRoll()
                        + rollWave
                        * MAX_ROLL_SHAKE
                        * strength
        );
    }

    private static float getEffectStrength() {
        float heat = ClientTemperatureState.getHeatPercent();

        heat = Mth.clamp(
                (heat - 0.05F) / 0.95F,
                0.0F,
                1.0F
        );

        return heat * heat * (3.0F - 2.0F * heat);
    }
}
