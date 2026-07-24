package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.item.Primordial_Items;
import net.konn.primordial.util.PrimordialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PrimordialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PrimordialTags.Items.PRIMORDIAL_ITEMS)
                .add(Primordial_Items.CHAINSAW.get())
                .add(Primordial_Items.CORN.get());
    }
}
