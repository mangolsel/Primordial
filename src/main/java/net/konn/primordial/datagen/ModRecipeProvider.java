package net.konn.primordial.datagen;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.block.Primordial_Blocks;
import net.konn.primordial.item.Primordial_Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
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
        List<ItemLike> cassiteriteSmeltables = List.of(Primordial_Items.RAW_CASSITERITE,
                Primordial_Blocks.GNEIS_CASSITERITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Primordial_Blocks.TIN_BLOCK.get())
                .define('A', Primordial_Items.TIN_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_tin",has(Primordial_Items.TIN_INGOT.get())).save(recipeOutput);
        //ARMOR
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Primordial_Items.WOOL_HELMET.get())
                .define('A', Blocks.WHITE_WOOL)
                .pattern("AAA")
                .pattern("A A")
                .unlockedBy("has_wool",has(Blocks.WHITE_WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Primordial_Items.WOOL_CHESTPLATE.get())
                .define('A', Blocks.WHITE_WOOL)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_wool",has(Blocks.WHITE_WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Primordial_Items.WOOL_LEGGINGS.get())
                .define('A', Blocks.WHITE_WOOL)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .unlockedBy("has_wool",has(Blocks.WHITE_WOOL)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Primordial_Items.WOOL_BOOTS.get())
                .define('A', Blocks.WHITE_WOOL)
                .pattern("A A")
                .pattern("A A")
                .unlockedBy("has_wool",has(Blocks.WHITE_WOOL)).save(recipeOutput);
        //PRIMAL TOOLS
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Primordial_Items.PRIMAL_SWORD.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern(" A")
                .pattern("CB")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Primordial_Items.PRIMAL_PICKAXE.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern(" A ")
                .pattern(" BA")
                .pattern("C  ")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Primordial_Items.PRIMAL_AXE.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern("AB")
                .pattern("C ")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Primordial_Items.PRIMAL_HOE.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern("A  ")
                .pattern(" B ")
                .pattern("C  ")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Primordial_Items.PRIMAL_SHOVEL.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Primordial_Items.STONE_HAMMER.get())
                .define('A', Primordial_Items.SHARPENED_ROCK.get())
                .define('B', Primordial_Items.PLANT_FIBER.get())
                .define('C', Items.STICK)
                .pattern(" AA")
                .pattern(" BA")
                .pattern("C  ")
                .unlockedBy("has_sharpened_rock",has(Primordial_Items.SHARPENED_ROCK.get())).save(recipeOutput);



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Primordial_Blocks.PEAT_BLOCK.get())
                .define('A', Primordial_Items.PEAT.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy("has_peat",has(Primordial_Items.PEAT.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Primordial_Items.TIN_INGOT.get(), 9)
                .requires(Primordial_Blocks.TIN_BLOCK.get())
                .unlockedBy("has_tin_block",has(Primordial_Blocks.TIN_BLOCK.get())).save(recipeOutput);

        oreSmelting(recipeOutput, cassiteriteSmeltables, RecipeCategory.MISC,
                Primordial_Items.TIN_INGOT.get(), 0.25f, 200, "tin");

        oreBlasting(recipeOutput, cassiteriteSmeltables, RecipeCategory.MISC,
                Primordial_Items.TIN_INGOT.get(), 0.25f, 100, "tin");

        //EBONY WOOD
        woodFamilyRecipes(
                recipeOutput,
                new WoodRecipeSet(
                        "ebony",
                        Primordial_Blocks.EBONY_PLANKS.get(),
                        Primordial_Blocks.EBONY_SLAB.get(),
                        Primordial_Blocks.EBONY_STAIRS.get(),
                        Primordial_Blocks.EBONY_PRESSURE_PLATE.get(),
                        Primordial_Blocks.EBONY_BUTTON.get(),
                        Primordial_Blocks.EBONY_FENCE.get(),
                        Primordial_Blocks.EBONY_FENCE_GATE.get(),
                        Primordial_Blocks.EBONY_DOOR.get(),
                        Primordial_Blocks.EBONY_TRAPDOOR.get()
                )
        );

    }

    private record WoodRecipeSet(
            String name,
            ItemLike planks,
            ItemLike slab,
            ItemLike stairs,
            ItemLike pressurePlate,
            ItemLike button,
            ItemLike fence,
            ItemLike fenceGate,
            ItemLike door,
            ItemLike trapdoor){}

    private static void woodFamilyRecipes(
            RecipeOutput recipeOutput,
            WoodRecipeSet wood
    ) {
        Ingredient planksIngredient = Ingredient.of(wood.planks());

        String group = wood.name();
        String unlockCriterion = getHasName(wood.planks());

        slab(
                recipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                wood.slab(),
                wood.planks()
        );

        stairBuilder(wood.stairs(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);

        pressurePlate(
                recipeOutput,
                wood.pressurePlate(),
                wood.planks()
        );

        buttonBuilder(wood.button(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);

        fenceBuilder(wood.fence(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);

        fenceGateBuilder(wood.fenceGate(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);

        doorBuilder(wood.door(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);

        trapdoorBuilder(wood.trapdoor(), planksIngredient)
                .group(group)
                .unlockedBy(unlockCriterion, has(wood.planks()))
                .save(recipeOutput);
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
