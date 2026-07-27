package net.konn.primordial.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.konn.primordial.PrimordialMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public final class TemperatureHudRenderer {

    private static final int TEXTURE_WIDTH = 16;
    private static final int TEXTURE_HEIGHT = 70;

    private static final int LINE_SOURCE_Y = 34;
    private static final int LINE_HEIGHT = 2;

    private static final ResourceLocation THERMOMETER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "textures/gui/termometer.png"
            );

    private static final ResourceLocation TEMPERATURE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "textures/gui/temperature.png"
            );

    private static final ResourceLocation LINE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    PrimordialMod.MOD_ID,
                    "textures/gui/line.png"
            );

    private static final int HOT_LINE_Y = 5;
    private static final int NEUTRAL_LINE_Y = 34;
    private static final int COLD_LINE_Y = 68;

    private static final int REVEAL_SPLIT_Y = 34;

    private static final int RIGHT_MARGIN = 6;

    private TemperatureHudRenderer() {
    }

    public static void render(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null
                || minecraft.level == null
                || minecraft.options.hideGui) {
            return;
        }

        float heatPercent = Mth.clamp(
                ClientTemperatureState.getHeatPercent(),
                0.0F,
                1.0F
        );

        int requiredFreezeTicks =
                minecraft.player.getTicksRequiredToFreeze();

        float coldPercent = requiredFreezeTicks <= 0
                ? 0.0F
                : Mth.clamp(
                minecraft.player.getTicksFrozen()
                        / (float) requiredFreezeTicks,
                0.0F,
                1.0F
        );

        float signedTemperature;

        if (heatPercent >= coldPercent) {
            signedTemperature = heatPercent;
        } else {
            signedTemperature = -coldPercent;
        }

        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        int x = screenWidth - TEXTURE_WIDTH - RIGHT_MARGIN;
        int y = screenHeight / 2 - TEXTURE_HEIGHT / 2;

        int lineY = calculateLineY(signedTemperature);

        renderThermometer(
                guiGraphics,
                x,
                y,
                lineY,
                signedTemperature
        );
    }

    private static int calculateLineY(
            float signedTemperature
    ) {
        signedTemperature = Mth.clamp(
                signedTemperature,
                -1.0F,
                1.0F
        );

        if (signedTemperature > 0.0F) {
            return Math.round(
                    Mth.lerp(
                            signedTemperature,
                            NEUTRAL_LINE_Y,
                            HOT_LINE_Y
                    )
            );
        }

        if (signedTemperature < 0.0F) {
            return Math.round(
                    Mth.lerp(
                            -signedTemperature,
                            NEUTRAL_LINE_Y,
                            COLD_LINE_Y
                    )
            );
        }

        return NEUTRAL_LINE_Y;
    }
    private static void drawTemperatureLine(
            GuiGraphics guiGraphics,
            int x,
            int y
    ) {
        guiGraphics.blit(
                LINE_TEXTURE,

                // Положение на экране
                x,
                y,

                // Размер отображаемой части
                TEXTURE_WIDTH,
                LINE_HEIGHT,

                // Начало области внутри line.png
                0.0F,
                LINE_SOURCE_Y,

                // Размер области внутри line.png
                TEXTURE_WIDTH,
                LINE_HEIGHT,

                // Полный размер line.png
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );
    }

    private static void renderThermometer(
            GuiGraphics guiGraphics,
            int x,
            int y,
            int lineY,
            float signedTemperature
    ) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.pose().pushPose();

        try {
            /*
             * Слой 1: рамка термометра.
             */
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

            blitFullTexture(
                    guiGraphics,
                    THERMOMETER_TEXTURE,
                    x,
                    y
            );

            /*
             * Слой 2: цветной рисунок.
             * Рисуем только ту часть, которая должна быть
             * раскрыта красной линией.
             */
            guiGraphics.pose().translate(0.0F, 0.0F, 1.0F);

            if (signedTemperature > 0.0F) {
                /*
                 * Жара:
                 * раскрываем область от текущей линии
                 * до центральной точки.
                 */
                int revealTop = y + lineY;
                int revealBottom = y + REVEAL_SPLIT_Y;

                if (revealTop < revealBottom) {
                    guiGraphics.enableScissor(
                            x,
                            revealTop,
                            x + TEXTURE_WIDTH,
                            revealBottom
                    );

                    try {
                        blitFullTexture(
                                guiGraphics,
                                TEMPERATURE_TEXTURE,
                                x,
                                y
                        );
                    } finally {
                        guiGraphics.disableScissor();
                    }
                }
            } else if (signedTemperature < 0.0F) {
                /*
                 * Холод:
                 * раскрываем область от центральной точки
                 * вниз до текущей линии.
                 */
                int revealTop = y + REVEAL_SPLIT_Y;
                int revealBottom = y + lineY;

                if (revealTop < revealBottom) {
                    guiGraphics.enableScissor(
                            x,
                            revealTop,
                            x + TEXTURE_WIDTH,
                            revealBottom
                    );

                    try {
                        blitFullTexture(
                                guiGraphics,
                                TEMPERATURE_TEXTURE,
                                x,
                                y
                        );
                    } finally {
                        guiGraphics.disableScissor();
                    }
                }
            }

            /*
             * Слой 3: красная линия поверх всего.
             */
            guiGraphics.pose().translate(0.0F, 0.0F, 1.0F);

            drawTemperatureLine(
                    guiGraphics,
                    x,
                    y + lineY
            );

        } finally {
            guiGraphics.pose().popPose();
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }

    private static void blitFullTexture(
            GuiGraphics guiGraphics,
            ResourceLocation texture,
            int x,
            int y
    ) {
        guiGraphics.blit(
                texture,

                // Положение на экране
                x,
                y,

                // Размер на экране
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT,

                // Начало изображения
                0.0F,
                0.0F,

                // Используемая область изображения
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT,

                // Полный размер PNG
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );
    }
}
