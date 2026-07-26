package net.konn.primordial.attachment;

import com.mojang.serialization.Codec;
import net.konn.primordial.PrimordialMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class PrimordialAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.ATTACHMENT_TYPES,
                    PrimordialMod.MOD_ID
            );

    public static final Supplier<AttachmentType<Integer>> HEAT_EXPOSURE =
            ATTACHMENT_TYPES.register(
                    "heat_exposure",
                    () -> AttachmentType
                            .builder(() -> 0)
                            .serialize(Codec.INT)
                            .build()
            );

    private PrimordialAttachments() {
    }

    public static void register(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
