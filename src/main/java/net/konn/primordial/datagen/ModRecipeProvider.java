package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> CASSITERITE_SMELTABLES = List.of(Primordial_Items.RAW_CASSITERITE,
                Primordial_Blocks.GNEIS_CASSITERITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Primordial_Blocks.TIN_BLOCK.get())
                .define('A', Primordial_Items.TIN_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_tin",has(Primordial_Items.TIN_INGOT.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Primordial_Blocks.PEAT_BLOCK.get())
                .define('A', Primordial_Items.PEAT.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy("has_peat",has(Primordial_Items.PEAT.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Primordial_Items.TIN_INGOT.get(), 9)
                .requires(Primordial_Blocks.TIN_BLOCK.get())
                .unlockedBy("has_tin_block",has(Primordial_Blocks.TIN_BLOCK.get())).save(recipeOutput);

        oreSmelting(recipeOutput, CASSITERITE_SMELTABLES, RecipeCategory.MISC,
                Primordial_Items.TIN_INGOT.get(), 0.25f, 200, "tin");

        oreBlasting(recipeOutput, CASSITERITE_SMELTABLES, RecipeCategory.MISC,
                Primordial_Items.TIN_INGOT.get(), 0.25f, 100, "tin");

    }




    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, PrimordialMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
