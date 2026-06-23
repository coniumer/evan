package net.seinsturg.efac.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.seinsturg.efac.EFAC;

public class EvansBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_GELWOOD_ORE = registerKey("add_gelwood_ore");

    public static final ResourceKey<BiomeModifier> ADD_SLIPULON_ORE = registerKey("add_slipulon_ore");

    public static final ResourceKey<BiomeModifier> ADD_ALBY_ORE = registerKey("add_alby_ore");
    public static final ResourceKey<BiomeModifier> ADD_CITRY_ORE = registerKey("add_citry_ore");
    public static final ResourceKey<BiomeModifier> ADD_RUBY_ORE = registerKey("add_ruby_ore");

    public static final ResourceKey<BiomeModifier> ADD_GEUMB_ORE = registerKey("add_geumb_ore");

    public static final ResourceKey<BiomeModifier> ADD_BUTTER_ORE = registerKey("add_butter_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_BUTTER_ORE = registerKey("add_nether_butter_ore");

    public static final ResourceKey<BiomeModifier> ADD_GRONE_ORE = registerKey("add_grone_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_GRONE_ORE = registerKey("add_end_grone_ore");

    public static final ResourceKey<BiomeModifier> ADD_END_PHILOSOPHERS_ORE = registerKey("add_end_philosophers_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        //gelwood
        context.register(ADD_GELWOOD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.GELWOOD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //slipulon
        context.register(ADD_SLIPULON_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.SLIPULON_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //gems
        context.register(ADD_ALBY_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.ALBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_CITRY_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.CITRY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_RUBY_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.RUBY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //geumb
        context.register(ADD_GEUMB_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.GEUMB_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //butter
        context.register(ADD_BUTTER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.BUTTER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NETHER_BUTTER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.NETHER_BUTTER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //butter
        context.register(ADD_GRONE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.GRONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_END_GRONE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.END_GRONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //philosophers
        context.register(ADD_END_PHILOSOPHERS_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_END_CITY),
                HolderSet.direct(placedFeatures.getOrThrow(EvansPlacedFeatures.END_PHILOSOPHERS_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID, name));
    }
}
