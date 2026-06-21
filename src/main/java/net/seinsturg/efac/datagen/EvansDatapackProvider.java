package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.worldgen.EvansBiomeModifiers;
import net.seinsturg.efac.worldgen.EvansConfiguredFeatures;
import net.seinsturg.efac.worldgen.EvansPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EvansDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, EvansConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, EvansPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, EvansBiomeModifiers::bootstrap);

    public EvansDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EFAC.MOD_ID));
    }
}
