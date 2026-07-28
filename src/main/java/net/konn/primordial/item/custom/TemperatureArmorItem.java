package net.konn.primordial.item.custom;

import net.konn.primordial.temperature.TemperatureWearable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TemperatureArmorItem extends ArmorItem implements TemperatureWearable {

    private final int temperatureModifier;

    public TemperatureArmorItem(
            Holder<ArmorMaterial> material,
            Type type,
            int temperatureModifier,
            Item.Properties properties
    ) {
        super(material, type, properties);
        this.temperatureModifier = temperatureModifier;
    }

    @Override
    public int getTemperatureModifier(ItemStack stack) {
        return temperatureModifier;
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack,
            Item.@NotNull TooltipContext context,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag flag
    ) {
        super.appendHoverText(
                stack,
                context,
                tooltip,
                flag
        );

        if (temperatureModifier > 0) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.primordial.temperature_modifier.positive",
                            temperatureModifier
                    ).withStyle(ChatFormatting.GOLD)
            );
        } else if (temperatureModifier < 0) {
            tooltip.add(
                    Component.translatable(
                            "tooltip.primordial.temperature_modifier.negative",
                            Math.abs(temperatureModifier)
                    ).withStyle(ChatFormatting.AQUA)
            );
        }
    }
}
