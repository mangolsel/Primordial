package net.konn.primordial.block;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.custom.MagicBlock;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Primordial_Blocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PrimordialMod.MOD_ID);

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            ()-> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_CASSITERITE_BLOCK = registerBlock("raw_cassiterite_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GNEIS_CASSITERITE_ORE = registerBlock("gneis_cassiterite_ore",
            ()-> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        Primordial_Items.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
