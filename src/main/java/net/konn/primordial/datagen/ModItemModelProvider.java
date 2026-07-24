package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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

    }
}
