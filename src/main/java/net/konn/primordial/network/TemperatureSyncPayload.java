package net.konn.primordial.network;

import io.netty.buffer.ByteBuf;
import net.konn.primordial.PrimordialMod;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TemperatureSyncPayload(
        int heatExposure
) implements CustomPacketPayload {
    public static final Type<TemperatureSyncPayload> TYPE =
            new Type<>(
                    ResourceLocation.fromNamespaceAndPath(
                            PrimordialMod.MOD_ID,
                            "temperature_sync"
                    )
            );

    public static final StreamCodec<ByteBuf, TemperatureSyncPayload>
            STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            TemperatureSyncPayload::heatExposure,
            TemperatureSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
