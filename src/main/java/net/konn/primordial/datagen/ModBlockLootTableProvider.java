package net.konn.primordial.datagen;

import net.konn.primordial.block.Primordial_Blocks;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),registries);
    }

    @Override
    protected void generate() {
        dropSelf(Primordial_Blocks.MAGIC_BLOCK.get());
        dropSelf(Primordial_Blocks.TIN_BLOCK.get());
        dropSelf(Primordial_Blocks.RAW_CASSITERITE_BLOCK.get());

        this.add(Primordial_Blocks.GNEIS_CASSITERITE_ORE.get(),
                block -> createMultipleDrops(
                        Primordial_Blocks.GNEIS_CASSITERITE_ORE.get(),
                        Primordial_Items.RAW_CASSITERITE.get(),2,5));

        this.add(Primordial_Blocks.PEAT_BLOCK.get(),
                block -> createMultipleDrops(
                        Primordial_Blocks.PEAT_BLOCK.get(),
                        Primordial_Items.PEAT.get(),1,4));

    }
    protected LootTable.Builder createMultipleDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                (LootPoolEntryContainer.Builder)this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.
                                        addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Primordial_Blocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
