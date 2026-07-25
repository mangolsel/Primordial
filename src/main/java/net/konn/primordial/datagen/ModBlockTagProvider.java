package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PrimordialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Primordial_Blocks.GNEIS_CASSITERITE_ORE.get())
                .add(Primordial_Blocks.RAW_CASSITERITE_BLOCK.get())
                .add(Primordial_Blocks.TIN_BLOCK.get())
                .add(Primordial_Blocks.MAGIC_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(Primordial_Blocks.PEAT_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(Primordial_Blocks.EBONY_PLANKS.get())
                .add(Primordial_Blocks.EBONY_SLAB.get())
                .add(Primordial_Blocks.EBONY_STAIRS.get())
                .add(Primordial_Blocks.EBONY_BUTTON.get())
                .add(Primordial_Blocks.EBONY_FENCE_GATE.get())
                .add(Primordial_Blocks.EBONY_FENCE.get())
                .add(Primordial_Blocks.EBONY_TRAPDOOR.get())
                .add(Primordial_Blocks.EBONY_DOOR.get())
                .add(Primordial_Blocks.EBONY_PRESSURE_PLATE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Primordial_Blocks.GNEIS_CASSITERITE_ORE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(Primordial_Blocks.EBONY_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(Primordial_Blocks.EBONY_FENCE_GATE.get());

    }
}
