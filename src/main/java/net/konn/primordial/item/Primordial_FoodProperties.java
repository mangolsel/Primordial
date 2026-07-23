package net.konn.primordial.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Primordial_FoodProperties {
    public static final FoodProperties CORN = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 600), 1.0f)
            .build();
}
