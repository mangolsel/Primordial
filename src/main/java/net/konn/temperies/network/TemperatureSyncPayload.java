package net.konn.temperies.network;

import io.netty.buffer.ByteBuf;
import net.konn.temperies.Temperies;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record TemperatureSyncPayload(
        int heatExposure,
        int coldExposure
) implements CustomPacketPayload {

    public static final Type<TemperatureSyncPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Temperies.MOD_ID,
                            "temperature_sync"
                    )
            );

    public static final StreamCodec<
            ByteBuf,
            TemperatureSyncPayload
            > STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            TemperatureSyncPayload::heatExposure,

            ByteBufCodecs.VAR_INT,
            TemperatureSyncPayload::coldExposure,

            TemperatureSyncPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
