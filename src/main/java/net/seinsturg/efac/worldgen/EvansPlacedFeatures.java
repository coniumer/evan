package net.seinsturg.efac.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.seinsturg.efac.EFAC;

import java.util.List;

public class EvansPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GELWOOD_ORE_PLACED_KEY = registerKey("gelwood_ore_placed");

    public static final ResourceKey<PlacedFeature> SLIPULON_ORE_PLACED_KEY = registerKey("slipulon_ore_placed");

    public static final ResourceKey<PlacedFeature> ALBY_ORE_PLACED_KEY = registerKey("alby_ore_placed");
    public static final ResourceKey<PlacedFeature> CITRY_ORE_PLACED_KEY = registerKey("citry_ore_placed");
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");

    public static final ResourceKey<PlacedFeature> GEUMB_ORE_PLACED_KEY = registerKey("geumb_ore_placed");

    public static final ResourceKey<PlacedFeature> BUTTER_ORE_PLACED_KEY = registerKey("butter_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BUTTER_ORE_PLACED_KEY = registerKey("nether_butter_ore_placed");

    public static final ResourceKey<PlacedFeature> END_PHILOSOPHERS_ORE_PLACED_KEY = registerKey("end_philosophers_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //gelwood
        register(context, GELWOOD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.GELWOOD_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(96))));

        //slipulon
        register(context, SLIPULON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.SLIPULON_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        //gems
        register(context, ALBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.ALBY_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(96))));
        register(context, CITRY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.CITRY_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.RUBY_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(8))));

        //geumbs
        register(context, GEUMB_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.GEUMB_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        //butter
        register(context, BUTTER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.BUTTER_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(96))));
        register(context, NETHER_BUTTER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.NETHER_BUTTER_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        //philosophers
        register(context, END_PHILOSOPHERS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(EvansConfiguredFeatures.END_PHILOSOPHERS_ORE_KEY),
                EvansOrePlacement.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(70))));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
