package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.item.Primordial_Items;
import net.konn.primordial.util.PrimordialTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
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
                .add(Primordial_Items.CORN.get())
                .add(Primordial_Items.PRIMAL_AXE.get())
                .add(Primordial_Items.PRIMAL_HOE.get())
                .add(Primordial_Items.PRIMAL_SWORD.get())
                .add(Primordial_Items.PRIMAL_SHOVEL.get())
                .add(Primordial_Items.PRIMAL_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(Primordial_Items.PRIMAL_AXE.get());
        tag(ItemTags.PICKAXES)
                .add(Primordial_Items.PRIMAL_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(Primordial_Items.PRIMAL_SHOVEL.get());
        tag(ItemTags.SWORDS)
                .add(Primordial_Items.PRIMAL_SWORD.get());
        tag(ItemTags.HOES)
                .add(Primordial_Items.PRIMAL_HOE.get());

        tag(PrimordialTags.Items.PROTECTS_HANDS_FROM_WOOD)
                .addTag(ItemTags.AXES);
        tag(PrimordialTags.Items.PROTECTS_HANDS_FROM_STONE)
                .addTag(ItemTags.PICKAXES);
    }
}
