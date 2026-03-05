package net.seinsturg.efac.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class EvansFoodProperties {
    public static final FoodProperties CLUMB_MATERIA = new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400), 1.0f).alwaysEdible().build();
    public static final FoodProperties RANDOM_SAUCE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();
    //add .usingConvertsTo when tiers are created
    public static final FoodProperties GEUMB = new FoodProperties.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().fast().build();
}
