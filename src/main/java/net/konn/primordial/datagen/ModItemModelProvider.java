package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PrimordialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(Primordial_Items.RAW_CASSITERITE.get());
        basicItem(Primordial_Items.PEAT.get());
        basicItem(Primordial_Items.CHAINSAW.get());
        basicItem(Primordial_Items.CORN.get());
        basicItem(Primordial_Items.DRY_PEAT_BRICK.get());
        basicItem(Primordial_Items.TIN_INGOT.get());
        basicItem(Primordial_Items.SHARPENED_ROCK.get());
        basicItem(Primordial_Items.PLANT_FIBER.get());
        //PRIMAL TOOLS
        handheldItem(Primordial_Items.PRIMAL_SWORD.get());
        handheldItem(Primordial_Items.PRIMAL_AXE.get());
        handheldItem(Primordial_Items.PRIMAL_PICKAXE.get());
        handheldItem(Primordial_Items.PRIMAL_SHOVEL.get());
        handheldItem(Primordial_Items.PRIMAL_HOE.get());
        handheldItem(Primordial_Items.STONE_HAMMER.get());
        //ARMOR
        basicItem(Primordial_Items.WOOL_BOOTS.get());
        basicItem(Primordial_Items.WOOL_CHESTPLATE.get());
        basicItem(Primordial_Items.WOOL_LEGGINGS.get());
        basicItem(Primordial_Items.WOOL_HELMET.get());
        //EBONY WOOD
        basicItem(Primordial_Blocks.EBONY_TRAPDOOR.asItem());
        buttonItem(Primordial_Blocks.EBONY_BUTTON, Primordial_Blocks.EBONY_PLANKS);
        fenceItem(Primordial_Blocks.EBONY_FENCE, Primordial_Blocks.EBONY_PLANKS);

    }
    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
