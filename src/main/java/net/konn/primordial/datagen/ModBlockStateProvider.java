package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrimordialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(Primordial_Blocks.MAGIC_BLOCK);
        blockWithItem(Primordial_Blocks.PEAT_BLOCK);
        blockWithItem(Primordial_Blocks.TIN_BLOCK);
        blockWithItem(Primordial_Blocks.RAW_CASSITERITE_BLOCK);
        blockWithItem(Primordial_Blocks.GNEIS_CASSITERITE_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
