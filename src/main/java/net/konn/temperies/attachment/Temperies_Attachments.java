package net.konn.temperies.attachment;

import com.mojang.serialization.Codec;
import net.konn.temperies.Temperies;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class Temperies_Attachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.ATTACHMENT_TYPES,
                    Temperies.MOD_ID
            );

    public static final Supplier<AttachmentType<Integer>> HEAT_EXPOSURE =
            ATTACHMENT_TYPES.register(
                    "heat_exposure",
                    () -> AttachmentType
                            .builder(() -> 0)
                            .serialize(Codec.INT)
                            .build()
            );
    public static final Supplier<AttachmentType<Integer>> COLD_EXPOSURE =
            ATTACHMENT_TYPES.register(
                    "cold_exposure",
                    () -> AttachmentType
                            .builder(() -> 0)
                            .serialize(Codec.INT)
                            .build()
            );

    private Temperies_Attachments() {
    }

    public static void register(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
