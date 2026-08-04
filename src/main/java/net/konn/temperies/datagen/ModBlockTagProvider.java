package net.konn.temperies.datagen;

import net.konn.temperies.Temperies;
import net.konn.temperies.block.Temperies_Blocks;
import net.konn.temperies.util.Temperies_Tags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Temperies.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(Temperies_Blocks.PEAT_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE);

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        tag(BlockTags.WOODEN_FENCES);
        tag(BlockTags.FENCE_GATES);

        tag(Temperies_Tags.Blocks.DOES_NOT_PROVIDE_SHADE)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.FENCES)
                .addTag(BlockTags.WALLS)
                .add(
                        Blocks.IRON_BARS,

                        Blocks.GLASS,
                        Blocks.TINTED_GLASS,

                        Blocks.WHITE_STAINED_GLASS,
                        Blocks.ORANGE_STAINED_GLASS,
                        Blocks.MAGENTA_STAINED_GLASS,
                        Blocks.LIGHT_BLUE_STAINED_GLASS,
                        Blocks.YELLOW_STAINED_GLASS,
                        Blocks.LIME_STAINED_GLASS,
                        Blocks.PINK_STAINED_GLASS,
                        Blocks.GRAY_STAINED_GLASS,
                        Blocks.LIGHT_GRAY_STAINED_GLASS,
                        Blocks.CYAN_STAINED_GLASS,
                        Blocks.PURPLE_STAINED_GLASS,
                        Blocks.BLUE_STAINED_GLASS,
                        Blocks.BROWN_STAINED_GLASS,
                        Blocks.GREEN_STAINED_GLASS,
                        Blocks.RED_STAINED_GLASS,
                        Blocks.BLACK_STAINED_GLASS
                );
        tag(Temperies_Tags.Blocks.DOES_NOT_SEAL_ROOM)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.FENCES)
                .addTag(BlockTags.WALLS)
                .add(Blocks.IRON_BARS);

    }
}
