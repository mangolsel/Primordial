package net.konn.primordial.item;

import net.konn.primordial.PrimordialMod;
import net.konn.primordial.item.custom.ChainsawItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Primordial_Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrimordialMod.MOD_ID);

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerSimpleItem("tin_ingot");
    public static final DeferredItem<Item> RAW_CASSITERITE = ITEMS.registerSimpleItem("raw_cassiterite");
    public static final DeferredItem<Item> CHAINSAW =
            ITEMS.registerItem("chainsaw", ChainsawItem::new, new Item.Properties().durability(32));
    public static final DeferredItem<Item> CORN =
            ITEMS.registerItem("corn",Item::new, new Item.Properties().food(Primordial_FoodProperties.CORN));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
