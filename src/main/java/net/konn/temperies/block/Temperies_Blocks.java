package net.konn.temperies.block;

import net.konn.temperies.Temperies;
import net.konn.temperies.item.Temperies_Items;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class Temperies_Blocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Temperies.MOD_ID);

    public static final DeferredBlock<Block> PEAT_BLOCK = registerBlock("peat_block",
            ()-> new MudBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f)
                    .speedFactor(0.5f)
                    .sound(SoundType.MUD)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        Temperies_Items.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
