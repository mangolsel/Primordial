package net.konn.primordial.item;

import net.konn.primordial.PrimordialMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Primordial_Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrimordialMod.MOD_ID);

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerSimpleItem("tin_ingot");
    public static final DeferredItem<Item> RAW_CASSITERITE = ITEMS.registerSimpleItem("raw_cassiterite");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
