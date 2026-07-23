package net.konn.primordial.item;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Primordial_CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrimordialMod.MOD_ID);

    public static final Supplier<CreativeModeTab> PRIMORDIAL_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("primordial_items_tab",() -> CreativeModeTab.builder()
                    .title(Component.translatable("itemsGroup.primordial.primordial_items_tab"))
                    .icon(()->new ItemStack(Primordial_Items.TIN_INGOT.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,"primordial_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Primordial_Items.TIN_INGOT);
                        output.accept(Primordial_Items.RAW_CASSITERITE);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> PRIMORDIAL_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("primordial_blocks_tab",() -> CreativeModeTab.builder()
                    .title(Component.translatable("itemsGroup.primordial.primordial_blocks_tab"))
                    .icon(()->new ItemStack(Primordial_Blocks.TIN_BLOCK.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Primordial_Blocks.TIN_BLOCK);
                        output.accept(Primordial_Blocks.RAW_CASSITERITE_BLOCK);
                        output.accept(Primordial_Blocks.GNEIS_CASSITERITE_ORE);
                    })
                    .build());

    public static void  register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
