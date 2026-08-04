package net.konn.temperies.datagen;

import net.konn.temperies.Temperies;
import net.konn.temperies.block.Temperies_Blocks;
import net.konn.temperies.item.Temperies_Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        //ARMOR
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Temperies_Items.WOOL_HELMET.get())
                .define('A', ItemTags.WOOL)
                .pattern("AAA")
                .pattern("A A")
                .unlockedBy("has_wool",has(ItemTags.WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Temperies_Items.WOOL_CHESTPLATE.get())
                .define('A', ItemTags.WOOL)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_wool",has(ItemTags.WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Temperies_Items.WOOL_LEGGINGS.get())
                .define('A', ItemTags.WOOL)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .unlockedBy("has_wool",has(ItemTags.WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Temperies_Items.WOOL_BOOTS.get())
                .define('A', ItemTags.WOOL)
                .pattern("A A")
                .pattern("A A")
                .unlockedBy("has_wool",has(ItemTags.WOOL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Temperies_Blocks.PEAT_BLOCK.get())
                .define('A', Temperies_Items.PEAT.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy("has_peat",has(Temperies_Items.PEAT.get())).save(recipeOutput);
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
                    .save(pRecipeOutput, Temperies.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
