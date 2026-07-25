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
                        output.accept(Primordial_Items.CHAINSAW);
                        output.accept(Primordial_Items.CORN);
                        output.accept(Primordial_Items.PEAT);
                        output.accept(Primordial_Items.DRY_PEAT_BRICK);
                        output.accept(Primordial_Items.SHARPENED_ROCK);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> PRIMORDIAL_INSTRUMENTS_TAB =
            CREATIVE_MODE_TABS.register("primordial_instruments_tab",() -> CreativeModeTab.builder()
                    .title(Component.translatable("itemsGroup.primordial.primordial_instruments_tab"))
                    .icon(()->new ItemStack(Primordial_Items.PRIMAL_SWORD.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,"primordial_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Primordial_Items.PRIMAL_SWORD);
                        output.accept(Primordial_Items.PRIMAL_SHOVEL);
                        output.accept(Primordial_Items.PRIMAL_PICKAXE);
                        output.accept(Primordial_Items.PRIMAL_AXE);
                        output.accept(Primordial_Items.PRIMAL_HOE);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> PRIMORDIAL_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("primordial_blocks_tab",() -> CreativeModeTab.builder()
                    .title(Component.translatable("itemsGroup.primordial.primordial_blocks_tab"))
                    .icon(()->new ItemStack(Primordial_Blocks.TIN_BLOCK.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Primordial_Blocks.MAGIC_BLOCK);
                        //EBONY WOOD
                        output.accept(Primordial_Blocks.EBONY_PLANKS);
                        output.accept(Primordial_Blocks.EBONY_STAIRS);
                        output.accept(Primordial_Blocks.EBONY_SLAB);
                        output.accept(Primordial_Blocks.EBONY_FENCE);
                        output.accept(Primordial_Blocks.EBONY_FENCE_GATE);
                        output.accept(Primordial_Blocks.EBONY_DOOR);
                        output.accept(Primordial_Blocks.EBONY_TRAPDOOR);
                        output.accept(Primordial_Blocks.EBONY_PRESSURE_PLATE);
                        output.accept(Primordial_Blocks.EBONY_BUTTON);

                        output.accept(Primordial_Blocks.PEAT_BLOCK);
                        output.accept(Primordial_Blocks.TIN_BLOCK);
                        output.accept(Primordial_Blocks.RAW_CASSITERITE_BLOCK);
                        output.accept(Primordial_Blocks.GNEIS_CASSITERITE_ORE);
                    })
                    .build());

    public static void  register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
