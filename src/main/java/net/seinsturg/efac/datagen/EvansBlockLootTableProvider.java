package net.seinsturg.efac.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.seinsturg.efac.block.EvansBlocks;

import java.util.Set;

public class EvansBlockLootTableProvider extends BlockLootSubProvider {
    protected EvansBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(EvansBlocks.CLUMB_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EvansBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
