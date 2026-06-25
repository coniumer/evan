package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.seinsturg.efac.item.EvansItems;

import java.util.concurrent.CompletableFuture;

public class EvansDataMapProvider extends DataMapProvider {
    protected EvansDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(EvansItems.CLUMBROT.getId(), new Compostable(0.25f), false);
    }
}
