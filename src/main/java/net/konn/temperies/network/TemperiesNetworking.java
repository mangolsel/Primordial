package net.konn.temperies.network;

import net.konn.temperies.client.ClientTemperatureState;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class TemperiesNetworking {
    private TemperiesNetworking() {
    }

    public static void registerPayloads(
            RegisterPayloadHandlersEvent event
    ) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                TemperatureSyncPayload.TYPE,
                TemperatureSyncPayload.STREAM_CODEC,
                (payload, context) ->
                        ClientTemperatureState.setExposure(
                                payload.heatExposure(),
                                payload.coldExposure()
                        )
        );
    }
}
