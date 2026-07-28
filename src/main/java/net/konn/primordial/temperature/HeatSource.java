package net.konn.primordial.temperature;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Objects;
import java.util.function.Predicate;

public record HeatSource(
        int targetExposure,
        int changePerUpdate,
        int radius,
        Predicate<BlockState> activeCondition
) {
    public HeatSource {
        if (targetExposure < 0
                || targetExposure > TemperatureConstants.MAX_EXPOSURE) {
            throw new IllegalArgumentException(
                    "targetExposure must be between 0 and "
                            + TemperatureConstants.MAX_EXPOSURE
            );
        }

        if (changePerUpdate <= 0) {
            throw new IllegalArgumentException(
                    "changePerUpdate must be greater than zero"
            );
        }

        if (radius < 1 || radius > 16) {
            throw new IllegalArgumentException(
                    "Heat source radius must be between 1 and 16"
            );
        }

        Objects.requireNonNull(
                activeCondition,
                "activeCondition"
        );
    }

    public boolean isActive(BlockState state) {
        return activeCondition.test(state);
    }

    public static HeatSource alwaysActive(
            int targetExposure,
            int changePerUpdate,
            int radius
    ) {
        return new HeatSource(
                targetExposure,
                changePerUpdate,
                radius,
                state -> true
        );
    }


    public static HeatSource lit(
            int targetExposure,
            int changePerUpdate,
            int radius
    ) {
        return new HeatSource(
                targetExposure,
                changePerUpdate,
                radius,
                state -> state.hasProperty(BlockStateProperties.LIT)
                        && state.getValue(BlockStateProperties.LIT)
        );
    }
}
