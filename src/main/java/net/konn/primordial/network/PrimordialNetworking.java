package net.konn.primordial.network;

import net.konn.primordial.client.ClientTemperatureState;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class PrimordialNetworking {
    private PrimordialNetworking() {
    }

    public static void registerPayloads(
            RegisterPayloadHandlersEvent event
    ) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                TemperatureSyncPayload.TYPE,
                TemperatureSyncPayload.STREAM_CODEC,
                (payload, context) ->
                        ClientTemperatureState.setHeatExposure(
                                payload.heatExposure()
                        )
        );
    }
}
