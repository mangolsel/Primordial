package net.konn.primordial.util;

import net.konn.primordial.PrimordialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PrimordialTags {
    public static class Blocks{
        public static final TagKey<Block> INJURES_BARE_HANDS_WOOD = createTag("injures_bare_hands/wood");
        public static final TagKey<Block> INJURES_BARE_HANDS_STONE = createTag("injures_bare_hands/stone");
        public static final TagKey<Block> INCORRECT_FOR_PRIMAL_TOOL = createTag("incorrect_for_primal_tool");

        private static TagKey<Block> createTag (String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,name));
        }
    }
    public static class Items {
        public static final TagKey<Item> PROTECTS_HANDS_FROM_WOOD = createTag("protects_hands/wood");
        public static final TagKey<Item> PROTECTS_HANDS_FROM_STONE = createTag("protects_hands/stone");
        public static final TagKey<Item> PRIMORDIAL_ITEMS = createTag("primordial_items");

        private static TagKey<Item> createTag (String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(PrimordialMod.MOD_ID,name));
        }
    }
}
