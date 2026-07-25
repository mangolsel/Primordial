package net.konn.primordial.item;

import net.konn.primordial.util.PrimordialTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class Primordial_Tool_Tiers {
    public static final Tier PRIMAL = new SimpleTier(PrimordialTags.Blocks.INCORRECT_FOR_PRIMAL_TOOL,
            20, 1.5f, 0.0f,20,
            ()-> Ingredient.of(Primordial_Items.SHARPENED_ROCK.get()));
}
