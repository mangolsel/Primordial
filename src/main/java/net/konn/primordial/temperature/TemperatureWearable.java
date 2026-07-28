package net.konn.primordial.temperature;

import net.minecraft.world.item.ItemStack;

public interface TemperatureWearable {

    int getTemperatureModifier(ItemStack stack);
}
