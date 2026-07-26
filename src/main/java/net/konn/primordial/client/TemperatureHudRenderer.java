package net.konn.primordial.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public final class TemperatureHudRenderer {
    private static final int BAR_WIDTH = 8;
    private static final int BAR_HEIGHT = 52;

    private static final int BORDER_COLOR = 0xD0000000;
    private static final int BACKGROUND_COLOR = 0xA0181818;

    private static final int HOT_COLOR = 0xFFE45A3B;
    private static final int COLD_COLOR = 0xFF65BCEB;
    private static final int NEUTRAL_COLOR = 0xFFE2D49A;

    private TemperatureHudRenderer() {
    }

    public static void render(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null
                || minecraft.options.hideGui) {
            return;
        }

        int screenWidth =
                minecraft.getWindow().getGuiScaledWidth();

        int screenHeight =
                minecraft.getWindow().getGuiScaledHeight();


        int x = screenWidth - BAR_WIDTH - 6;
        int y = screenHeight / 2 - BAR_HEIGHT / 2;

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

        drawBackground(guiGraphics, x, y);


        if (heatPercent >= coldPercent) {
            drawHeat(guiGraphics, x, y, heatPercent);
        } else {
            drawCold(guiGraphics, x, y, coldPercent);
        }
    }

    private static void drawBackground(
            GuiGraphics guiGraphics,
            int x,
            int y
    ) {
        guiGraphics.fill(
                x,
                y,
                x + BAR_WIDTH,
                y + BAR_HEIGHT,
                BORDER_COLOR
        );

        guiGraphics.fill(
                x + 1,
                y + 1,
                x + BAR_WIDTH - 1,
                y + BAR_HEIGHT - 1,
                BACKGROUND_COLOR
        );

        int centerY = y + BAR_HEIGHT / 2;


        guiGraphics.fill(
                x + 1,
                centerY,
                x + BAR_WIDTH - 1,
                centerY + 1,
                NEUTRAL_COLOR
        );
    }

    private static void drawHeat(
            GuiGraphics guiGraphics,
            int x,
            int y,
            float heatPercent
    ) {
        if (heatPercent <= 0.0F) {
            return;
        }

        int centerY = y + BAR_HEIGHT / 2;
        int topY = y + 2;

        int availablePixels = centerY - topY;

        int filledPixels = Math.max(
                1,
                Mth.ceil(heatPercent * availablePixels)
        );

        guiGraphics.fill(
                x + 2,
                centerY - filledPixels,
                x + BAR_WIDTH - 2,
                centerY,
                HOT_COLOR
        );
    }

    private static void drawCold(
            GuiGraphics guiGraphics,
            int x,
            int y,
            float coldPercent
    ) {
        if (coldPercent <= 0.0F) {
            return;
        }

        int centerY = y + BAR_HEIGHT / 2;
        int bottomY = y + BAR_HEIGHT - 2;

        int availablePixels = bottomY - centerY;

        int filledPixels = Math.max(
                1,
                Mth.ceil(coldPercent * availablePixels)
        );

        guiGraphics.fill(
                x + 2,
                centerY + 1,
                x + BAR_WIDTH - 2,
                centerY + 1 + filledPixels,
                COLD_COLOR
        );
    }
}
