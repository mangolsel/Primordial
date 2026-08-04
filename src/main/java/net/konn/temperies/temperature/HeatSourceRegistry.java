package net.konn.temperies.temperature;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class HeatSourceRegistry {
    private static final Map<Block, HeatSource> SOURCES =
            new ConcurrentHashMap<>();

    private static final AtomicInteger MAX_REGISTERED_RADIUS =
            new AtomicInteger();

    private HeatSourceRegistry() {
    }

    public static void register(
            Block block,
            HeatSource source
    ) {
        Objects.requireNonNull(block, "block");
        Objects.requireNonNull(source, "source");

        HeatSource previous = SOURCES.putIfAbsent(
                block,
                source
        );

        if (previous != null) {
            throw new IllegalStateException(
                    "Heat source is already registered for block: "
                            + BuiltInRegistries.BLOCK.getKey(block)
            );
        }

        MAX_REGISTERED_RADIUS.accumulateAndGet(
                source.radius(),
                Math::max
        );
    }


    public static void register(
            Block block,
            int targetExposure,
            int changePerUpdate,
            int radius
    ) {
        register(
                block,
                HeatSource.alwaysActive(
                        targetExposure,
                        changePerUpdate,
                        radius
                )
        );
    }

    public static void registerLit(
            Block block,
            int targetExposure,
            int changePerUpdate,
            int radius
    ) {
        register(
                block,
                HeatSource.lit(
                        targetExposure,
                        changePerUpdate,
                        radius
                )
        );
    }


    public static void replace(
            Block block,
            HeatSource source
    ) {
        Objects.requireNonNull(block, "block");
        Objects.requireNonNull(source, "source");

        SOURCES.put(block, source);

        MAX_REGISTERED_RADIUS.accumulateAndGet(
                source.radius(),
                Math::max
        );
    }

    @Nullable
    public static HeatSource getActive(
            BlockState state
    ) {
        HeatSource source = SOURCES.get(
                state.getBlock()
        );

        if (source == null || !source.isActive(state)) {
            return null;
        }

        return source;
    }

    public static int getMaximumRadius() {
        return MAX_REGISTERED_RADIUS.get();
    }
}
