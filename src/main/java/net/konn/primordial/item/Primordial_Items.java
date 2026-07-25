package net.konn.primordial.item;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.item.custom.ChainsawItem;
import net.konn.primordial.item.custom.FuelItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Primordial_Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrimordialMod.MOD_ID);

    public static final DeferredItem<Item> PEAT = ITEMS.registerSimpleItem("peat");
    public static final DeferredItem<Item> SHARPENED_ROCK = ITEMS.registerSimpleItem("sharpened_rock");
    public static final DeferredItem<Item> DRY_PEAT_BRICK =
            ITEMS.registerItem("dry_peat_brick",properties -> new FuelItem(properties,800), new Item.Properties());
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerSimpleItem("tin_ingot");
    public static final DeferredItem<Item> RAW_CASSITERITE = ITEMS.registerSimpleItem("raw_cassiterite");
    public static final DeferredItem<Item> CHAINSAW =
            ITEMS.registerItem("chainsaw", ChainsawItem::new, new Item.Properties().durability(32));
    public static final DeferredItem<Item> CORN =
            ITEMS.registerItem("corn",Item::new, new Item.Properties().food(Primordial_FoodProperties.CORN));

    public static final DeferredItem <Item> PRIMAL_SWORD = ITEMS.register("primal_sword",
            ()-> new SwordItem(Primordial_Tool_Tiers.PRIMAL,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(Primordial_Tool_Tiers.PRIMAL, 2,-2.4f))));
    public static final DeferredItem <Item> PRIMAL_AXE = ITEMS.register("primal_axe",
            ()-> new AxeItem(Primordial_Tool_Tiers.PRIMAL,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(Primordial_Tool_Tiers.PRIMAL, 4,-3.2f))));
    public static final DeferredItem <Item> PRIMAL_PICKAXE = ITEMS.register("primal_pickaxe",
            ()-> new PickaxeItem(Primordial_Tool_Tiers.PRIMAL,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(Primordial_Tool_Tiers.PRIMAL, 0,-3.2f))));
    public static final DeferredItem <Item> PRIMAL_SHOVEL = ITEMS.register("primal_shovel",
            ()-> new ShovelItem(Primordial_Tool_Tiers.PRIMAL,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(Primordial_Tool_Tiers.PRIMAL, 0,-3.2f))));
    public static final DeferredItem <Item> PRIMAL_HOE = ITEMS.register("primal_hoe",
            ()-> new HoeItem(Primordial_Tool_Tiers.PRIMAL,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(Primordial_Tool_Tiers.PRIMAL, 0,-3.2f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
