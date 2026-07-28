package net.konn.primordial;

import net.konn.primordial.attachment.PrimordialAttachments;
import net.konn.primordial.block.Primordial_Blocks;
import net.konn.primordial.event.BareHandMiningHandler;
import net.konn.primordial.event.TemperatureHandler;
import net.konn.primordial.item.Primordial_ArmorMaterials;
import net.konn.primordial.item.Primordial_CreativeModeTabs;
import net.konn.primordial.item.Primordial_Items;
import net.konn.primordial.network.PrimordialNetworking;
import net.konn.primordial.temperature.PrimordialHeatSources;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(PrimordialMod.MOD_ID)
public class PrimordialMod {
    public static final String MOD_ID = "primordial";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PrimordialMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(PrimordialNetworking::registerPayloads);

        Primordial_CreativeModeTabs.register(modEventBus);
        Primordial_Items.register(modEventBus);
        Primordial_Blocks.register(modEventBus);
        PrimordialAttachments.register(modEventBus);
        Primordial_ArmorMaterials.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new BareHandMiningHandler());
        NeoForge.EVENT_BUS.register(new TemperatureHandler());


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(
                PrimordialHeatSources::registerDefaults
        );
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
