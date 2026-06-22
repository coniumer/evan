package net.seinsturg.efac.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class EvansFoodProperties {
    public static final FoodProperties CLUMB_MATERIA = new FoodProperties.Builder().nutrition(1).saturationModifier(0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400), 1.0f).alwaysEdible().build();
    public static final FoodProperties SAUCE = new FoodProperties.Builder().nutrition(2).saturationModifier(0f).build();
    public static final FoodProperties GEUMB = new FoodProperties.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().fast().build();
    public static final FoodProperties BUTTER = new FoodProperties.Builder().nutrition(4).saturationModifier(0f).fast().build();

    public static final FoodProperties CLUMBELON = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(2).fast().build();
    public static final FoodProperties CLUMBKIE = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(2).fast().build();
    public static final FoodProperties BLUMB = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(6).fast().build();
    public static final FoodProperties CLUMBROT = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(4).fast().build();
    public static final FoodProperties GOLD_CLUMBROT = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(6).fast()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1f)
            //todo: absorption???
            .build();
    public static final FoodProperties CLUMBLE = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(4).fast().build();
    public static final FoodProperties GOLD_CLUMBLE = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(6).fast()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1f)
            //todo: absorption???
            .build();
    public static final FoodProperties CLUMBURGER = new FoodProperties.Builder().alwaysEdible().saturationModifier(0f)
            .nutrition(8).build();

    public static final FoodProperties BURNT_CHICKEN = new FoodProperties.Builder().saturationModifier(0f)
            .nutrition(2).build();

    public static final FoodProperties BLOOD_CANISTER = new FoodProperties.Builder().nutrition(2).saturationModifier(0f).alwaysEdible().fast().build();
}
