package net.seinsturg.efac.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.block.custom.ClumbrotCropBlock;
import net.seinsturg.efac.item.EvansItems;

import java.util.Set;

public class EvansBlockLootTableProvider extends BlockLootSubProvider {
    protected EvansBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(EvansBlocks.CLUMB_HARVESTER.get());
        dropSelf(EvansBlocks.CACHER.get());
        dropSelf(EvansBlocks.GEOCACHE.get());

        dropSelf(EvansBlocks.CLUMB_BLOCK.get());
        dropSelf(EvansBlocks.NILENE_BLOCK.get());

        dropSelf(EvansBlocks.GRONE.get());

        dropSelf(EvansBlocks.GEUMB_TILES.get());
        dropSelf(EvansBlocks.GEUMB_TILE_STAIRS.get());
        add(EvansBlocks.GEUMB_TILE_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.GEUMB_TILE_SLAB.get()));
        dropSelf(EvansBlocks.GEUMB_TILE_WALL.get());

        dropSelf(EvansBlocks.CLUMBY_GEUMB_TILES.get());
        dropSelf(EvansBlocks.CLUMBY_GEUMB_TILE_STAIRS.get());
        add(EvansBlocks.CLUMBY_GEUMB_TILE_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.CLUMBY_GEUMB_TILE_SLAB.get()));
        dropSelf(EvansBlocks.CLUMBY_GEUMB_TILE_WALL.get());

        dropSelf(EvansBlocks.ALBY_GEUMB_TILES.get());
        dropSelf(EvansBlocks.ALBY_GEUMB_TILE_STAIRS.get());
        add(EvansBlocks.ALBY_GEUMB_TILE_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.ALBY_GEUMB_TILE_SLAB.get()));
        dropSelf(EvansBlocks.ALBY_GEUMB_TILE_WALL.get());

        dropSelf(EvansBlocks.CITRY_GEUMB_TILES.get());
        dropSelf(EvansBlocks.CITRY_GEUMB_TILE_STAIRS.get());
        add(EvansBlocks.CITRY_GEUMB_TILE_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.CITRY_GEUMB_TILE_SLAB.get()));
        dropSelf(EvansBlocks.CITRY_GEUMB_TILE_WALL.get());

        dropSelf(EvansBlocks.RUBY_GEUMB_TILES.get());
        dropSelf(EvansBlocks.RUBY_GEUMB_TILE_STAIRS.get());
        add(EvansBlocks.RUBY_GEUMB_TILE_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.RUBY_GEUMB_TILE_SLAB.get()));
        dropSelf(EvansBlocks.RUBY_GEUMB_TILE_WALL.get());

        dropSelf(EvansBlocks.GRONE_BRICKS.get());
        dropSelf(EvansBlocks.GRONE_BRICK_STAIRS.get());
        add(EvansBlocks.GRONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.GRONE_BRICK_SLAB.get()));
        dropSelf(EvansBlocks.GRONE_BRICK_WALL.get());

        dropSelf(EvansBlocks.RONE_BRICKS.get());
        dropSelf(EvansBlocks.RONE_BRICK_STAIRS.get());
        add(EvansBlocks.RONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.RONE_BRICK_SLAB.get()));
        dropSelf(EvansBlocks.RONE_BRICK_WALL.get());

        dropSelf(EvansBlocks.BLONE_BRICKS.get());
        dropSelf(EvansBlocks.BLONE_BRICK_STAIRS.get());
        add(EvansBlocks.BLONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.BLONE_BRICK_SLAB.get()));
        dropSelf(EvansBlocks.BLONE_BRICK_WALL.get());

        dropSelf(EvansBlocks.PLONE_BRICKS.get());
        dropSelf(EvansBlocks.PLONE_BRICK_STAIRS.get());
        add(EvansBlocks.PLONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.PLONE_BRICK_SLAB.get()));
        dropSelf(EvansBlocks.PLONE_BRICK_WALL.get());

        dropSelf(EvansBlocks.COMPACT_DIRT.get());
        dropSelf(EvansBlocks.YES.get());
        dropSelf(EvansBlocks.SLIPULON_BLOCK.get());
        dropSelf(EvansBlocks.PHILOSOPHERS_BLOCK.get());
        dropSelf(EvansBlocks.ALBY_BLOCK.get());
        dropSelf(EvansBlocks.CITRY_BLOCK.get());
        dropSelf(EvansBlocks.RUBY_BLOCK.get());
        dropSelf(EvansBlocks.GELWOOD_PLANKS.get());

        dropSelf(EvansBlocks.GELWOOD_STAIRS.get());
        add(EvansBlocks.GELWOOD_SLAB.get(),
                block -> createSlabItemTable(EvansBlocks.GELWOOD_SLAB.get()));
        dropSelf(EvansBlocks.GELWOOD_PRESSURE_PLATE.get());
        dropSelf(EvansBlocks.GELWOOD_BUTTON.get());
        dropSelf(EvansBlocks.GELWOOD_FENCE.get());
        dropSelf(EvansBlocks.GELWOOD_FENCE_GATE.get());
        dropSelf(EvansBlocks.GELWOOD_TRAPDOOR.get());

        add(EvansBlocks.GELWOOD_DOOR.get(),
                block -> createDoorTable(EvansBlocks.GELWOOD_DOOR.get()));

        dropWhenSilkTouch(EvansBlocks.BUDDING_GEUMB.get());
        dropWhenSilkTouch(EvansBlocks.LARGE_GEUMB_BUD.get());
        dropWhenSilkTouch(EvansBlocks.MEDIUM_GEUMB_BUD.get());
        dropWhenSilkTouch(EvansBlocks.SMALL_GEUMB_BUD.get());

        add(EvansBlocks.PHILOSOPHERS_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.PHILOSOPHERS_ORE.get(), EvansItems.PHILOSOPHERS_STONE.get(), 1, 2));
        add(EvansBlocks.ALBY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.ALBY_ORE.get(), EvansItems.ALBY_GEM.get(), 1, 5));
        add(EvansBlocks.CITRY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.CITRY_ORE.get(), EvansItems.CITRY_GEM.get(), 1, 5));
        add(EvansBlocks.RUBY_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.RUBY_ORE.get(), EvansItems.RUBY_GEM.get(), 1, 5));
        add(EvansBlocks.GEUMB_BLOCK.get(),
                block -> createCustomOreDrop(EvansBlocks.GEUMB_BLOCK.get(), EvansItems.GEUMB_SHARD.get(), 3, 9));
        add(EvansBlocks.GEUMB_CLUSTER.get(),
                block -> createCustomOreDrop(EvansBlocks.GEUMB_CLUSTER.get(), EvansItems.GEUMB_SHARD.get(), 3, 9));
        add(EvansBlocks.GELWOOD_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.GELWOOD_ORE.get(), EvansItems.GELWOOD_ORB.get(), 4, 4));
        add(EvansBlocks.SLIPULON_ORE.get(),
                block -> createCustomOreDrop(EvansBlocks.SLIPULON_ORE.get(), EvansItems.RAW_SLIPULON.get(), 3, 5));
        add(EvansBlocks.BUTTER.get(),
                block -> createCustomOreDrop(EvansBlocks.BUTTER.get(), EvansItems.BUTTER_STICK.get(), 1, 7));

        LootItemCondition.Builder clumbrotCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(EvansBlocks.CLUMBROT_CROP_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ClumbrotCropBlock.AGE, 3));
        add(EvansBlocks.CLUMBROT_CROP_BLOCK.get(), createCropDrops(EvansBlocks.CLUMBROT_CROP_BLOCK.get(),
                EvansItems.CLUMBROT.get(), EvansItems.CLUMBROT.get(), clumbrotCondition));
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
