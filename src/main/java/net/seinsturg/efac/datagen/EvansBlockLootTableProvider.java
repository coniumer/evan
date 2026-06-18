package net.seinsturg.efac.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

import java.util.Set;

public class EvansBlockLootTableProvider extends BlockLootSubProvider {
    protected EvansBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(EvansBlocks.CLUMB_BLOCK.get());
        dropSelf(EvansBlocks.GRONE.get());
        dropSelf(EvansBlocks.COMPACT_DIRT.get());

        dropWhenSilkTouch(EvansBlocks.BUDDING_GEUMB.get());

        add(EvansBlocks.ALBY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.ALBY_ORE.get(), EvansItems.ALBY_GEM.get(), 1, 5));
        add(EvansBlocks.CITRY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.CITRY_ORE.get(), EvansItems.CITRY_GEM.get(), 1, 5));
        add(EvansBlocks.RUBY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.RUBY_ORE.get(), EvansItems.RUBY_GEM.get(), 1, 5));
        add(EvansBlocks.GEUMB_BLOCK.get(),
                block -> createCustomOreDrop(EvansBlocks.GEUMB_BLOCK.get(), EvansItems.GEUMB_SHARD.get(), 3, 9));
        add(EvansBlocks.GELWOOD_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.GELWOOD_ORE.get(), EvansItems.GELWOOD_ORB.get(), 4, 4));
        add(EvansBlocks.SLIPULON_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.SLIPULON_ORE.get(), EvansItems.RAW_SLIPULON.get(), 3, 5));
        add(EvansBlocks.BUTTER.get(),
                block -> createCustomOreDrop(EvansBlocks.BUTTER.get(), EvansItems.BUTTER_STICK.get(), 1, 7));
    }

    protected LootTable.Builder createCustomOreDrop(Block block, Item item, int min, int max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EvansBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
