package net.konn.primordial;

import com.mojang.blaze3d.systems.RenderSystem;
import net.konn.primordial.client.ClientTemperatureState;
import net.konn.primordial.client.TemperatureHudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = PrimordialMod.MOD_ID, value = Dist.CLIENT)
public class PrimordialModClient {
    private static final ResourceLocation HEAT_OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "textures/gui/heat_overlay.png"
            );
    private static final int HEAT_TEXTURE_WIDTH = 256;
    private static final int HEAT_TEXTURE_HEIGHT = 256;

    private static final ResourceLocation HEAT_OVERLAY_LAYER =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "heat_overlay"
            );

    private static final ResourceLocation TEMPERATURE_INDICATOR_LAYER =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "temperature_indicator"
            );

    public PrimordialModClient(ModContainer container) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                ConfigurationScreen::new
        );
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        PrimordialMod.LOGGER.info(
                "HELLO FROM CLIENT SETUP"
        );

        PrimordialMod.LOGGER.info(
                "MINECRAFT NAME >> {}",
                Minecraft.getInstance().getUser().getName()
        );
    }

    @SubscribeEvent
    static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(
                VanillaGuiLayers.CAMERA_OVERLAYS,
                HEAT_OVERLAY_LAYER,
                (guiGraphics, deltaTracker) ->
                        renderHeatOverlay(guiGraphics)
        );

        event.registerAbove(
                VanillaGuiLayers.HOTBAR,
                TEMPERATURE_INDICATOR_LAYER,
                (guiGraphics, deltaTracker) ->
                        TemperatureHudRenderer.render(guiGraphics)
        );
    }

    private static void renderHeatOverlay(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null || minecraft.level == null) {
            return;
        }

        float heatPercent = Mth.clamp(
                ClientTemperatureState.getHeatPercent(),
                0.0F,
                1.0F
        );

        /*
         * Первые 3% перегрева показываются только на индикаторе.
         * Рамка начинает проявляться немного позже.
         */
        float visibleHeat = Mth.clamp(
                (heatPercent - 0.03F) / 0.97F,
                0.0F,
                1.0F
        );

        if (visibleHeat <= 0.0F) {
            return;
        }

        float opacity = Mth.clamp(
                visibleHeat * visibleHeat * 0.9F,
                0.0F,
                0.9F
        );

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        /*
         * Не полагаемся на состояние, оставленное другими
         * элементами HUD. Явно включаем альфа-смешивание.
         */
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.setColor(
                1.0F,
                1.0F,
                1.0F,
                opacity
        );

        try {
            guiGraphics.blit(
                    HEAT_OVERLAY_TEXTURE,
                    0,
                    0,
                    screenWidth,
                    screenHeight,
                    0.0F,
                    0.0F,
                    HEAT_TEXTURE_WIDTH,
                    HEAT_TEXTURE_HEIGHT,
                    HEAT_TEXTURE_WIDTH,
                    HEAT_TEXTURE_HEIGHT
            );
        } finally {
            guiGraphics.setColor(
                    1.0F,
                    1.0F,
                    1.0F,
                    1.0F
            );

            RenderSystem.disableBlend();
        }
    }

}
