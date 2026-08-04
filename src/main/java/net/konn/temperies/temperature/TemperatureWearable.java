package net.konn.temperies.temperature;

import net.minecraft.world.item.ItemStack;

public interface TemperatureWearable {

    int getTemperatureModifier(ItemStack stack);
}
