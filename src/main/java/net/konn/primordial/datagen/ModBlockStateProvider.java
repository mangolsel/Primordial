package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrimordialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(Primordial_Blocks.MAGIC_BLOCK);
        //EBONY WOOD
        blockWithItem(Primordial_Blocks.EBONY_PLANKS);
        stairsBlock(((StairBlock) Primordial_Blocks.EBONY_STAIRS.get()), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        slabBlock(((SlabBlock) Primordial_Blocks.EBONY_SLAB.get()), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        blockItem(Primordial_Blocks.EBONY_STAIRS);
        blockItem(Primordial_Blocks.EBONY_SLAB);
        pressurePlateBlock(((PressurePlateBlock) Primordial_Blocks.EBONY_PRESSURE_PLATE.get()), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        buttonBlock(((ButtonBlock) Primordial_Blocks.EBONY_BUTTON.get()), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        blockItem(Primordial_Blocks.EBONY_PRESSURE_PLATE);
        fenceBlock((FenceBlock) Primordial_Blocks.EBONY_FENCE.get(), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) Primordial_Blocks.EBONY_FENCE_GATE.get(), blockTexture(Primordial_Blocks.EBONY_PLANKS.get()));
        blockItem(Primordial_Blocks.EBONY_FENCE_GATE);
        doorBlockWithRenderType(((DoorBlock) Primordial_Blocks.EBONY_DOOR.get()), modLoc("block/ebony_door_bottom"), modLoc ("block/ebony_door_top"),"cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) Primordial_Blocks.EBONY_TRAPDOOR.get()), modLoc("block/ebony_trapdoor"), true,"cutout");
        blockItem(Primordial_Blocks.EBONY_TRAPDOOR, "_bottom");
        itemModels().basicItem(Primordial_Blocks.EBONY_DOOR.asItem());

        blockWithItem(Primordial_Blocks.PEAT_BLOCK);
        blockWithItem(Primordial_Blocks.TIN_BLOCK);
        blockWithItem(Primordial_Blocks.RAW_CASSITERITE_BLOCK);
        blockWithItem(Primordial_Blocks.GNEIS_CASSITERITE_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("primordial:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("primordial:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
