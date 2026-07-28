package net.konn.primordial.temperature;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class TemperatureEquipment {

    private TemperatureEquipment() {
    }

    public static int getTotalModifier(
            LivingEntity entity
    ) {
        int totalModifier = 0;

        for (ItemStack armorStack : entity.getArmorSlots()) {
            if (armorStack.getItem()
                    instanceof TemperatureWearable wearable) {

                totalModifier +=
                        wearable.getTemperatureModifier(
                                armorStack
                        );
            }
        }

        return Mth.clamp(
                totalModifier,
                -TemperatureConstants.MAX_EXPOSURE,
                TemperatureConstants.MAX_EXPOSURE
        );
    }
}
