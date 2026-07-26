package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.util.PrimordialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public final class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, PrimordialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PrimordialTags.Biomes.COLD)
                .add(
                        Biomes.SNOWY_PLAINS,
                        Biomes.ICE_SPIKES,
                        Biomes.SNOWY_TAIGA,
                        Biomes.GROVE,
                        Biomes.SNOWY_SLOPES,
                        Biomes.FROZEN_PEAKS,
                        Biomes.JAGGED_PEAKS,
                        Biomes.FROZEN_RIVER
                );

        tag(PrimordialTags.Biomes.HOT)
                .add(
                        Biomes.DESERT,
                        Biomes.BADLANDS,
                        Biomes.ERODED_BADLANDS,
                        Biomes.WOODED_BADLANDS,
                        Biomes.SAVANNA,
                        Biomes.SAVANNA_PLATEAU,
                        Biomes.WINDSWEPT_SAVANNA,
                        Biomes.JUNGLE,
                        Biomes.SPARSE_JUNGLE,
                        Biomes.BAMBOO_JUNGLE
                );

        tag(PrimordialTags.Biomes.NEUTRAL)
                .addTag(BiomeTags.IS_OCEAN);
    }
}
