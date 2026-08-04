package net.konn.temperies.temperature;

import net.minecraft.world.level.block.Blocks;

public final class TemperiesHeatSources {
    private TemperiesHeatSources() {
    }

    public static void registerDefaults() {
        HeatSourceRegistry.registerLit(
                Blocks.FURNACE,
                0,
                4,
                2
        );

        HeatSourceRegistry.registerLit(
                Blocks.BLAST_FURNACE,
                0,
                5,
                2
        );

        HeatSourceRegistry.registerLit(
                Blocks.SMOKER,
                0,
                5,
                2
        );

        HeatSourceRegistry.registerLit(
                Blocks.CAMPFIRE,
                60,
                5,
                3
        );

        HeatSourceRegistry.registerLit(
                Blocks.SOUL_CAMPFIRE,
                40,
                4,
                3
        );

        HeatSourceRegistry.register(
                Blocks.FIRE,
                100,
                6,
                2
        );

        HeatSourceRegistry.register(
                Blocks.SOUL_FIRE,
                80,
                5,
                2
        );

        HeatSourceRegistry.register(
                Blocks.MAGMA_BLOCK,
                100,
                4,
                2
        );

        HeatSourceRegistry.register(
                Blocks.LAVA,
                TemperatureConstants.MAX_EXPOSURE,
                8,
                2
        );
    }
}
