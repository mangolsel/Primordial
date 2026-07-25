package net.konn.primordial.block;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.custom.MagicBlock;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class Primordial_Blocks {
    static float woodStrength = 2f;

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PrimordialMod.MOD_ID);

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            ()-> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()));

    //EBONY WOOD
    public static final DeferredBlock<Block> EBONY_PLANKS = registerBlock("ebony_planks",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_STAIRS = registerBlock("ebony_stairs",
            ()-> new StairBlock(Primordial_Blocks.EBONY_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_SLAB = registerBlock("ebony_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.of()
                            .strength(woodStrength)
                            .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_PRESSURE_PLATE = registerBlock("ebony_pressure_plate",
            ()-> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_FENCE = registerBlock("ebony_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_FENCE_GATE = registerBlock("ebony_fence_gate",
            ()-> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_DOOR = registerBlock("ebony_door",
            ()-> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.of()
                    .strength(woodStrength).noOcclusion()
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_TRAPDOOR = registerBlock("ebony_trapdoor",
            ()-> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.of()
                    .strength(woodStrength).noOcclusion()
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> EBONY_BUTTON = registerBlock("ebony_button",
            ()-> new ButtonBlock(BlockSetType.OAK,10, BlockBehaviour.Properties.of()
                    .strength(woodStrength)
                    .noCollission()
                    .sound(SoundType.WOOD)));




    public static final DeferredBlock<Block> PEAT_BLOCK = registerBlock("peat_block",
            ()-> new MudBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f)
                    .speedFactor(0.5f)
                    .sound(SoundType.MUD)));
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
