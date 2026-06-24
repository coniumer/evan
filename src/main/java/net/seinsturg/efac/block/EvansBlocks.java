package net.seinsturg.efac.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.custom.BuddingGeumbBlock;
import net.seinsturg.efac.block.custom.ClumbBlock;
import net.seinsturg.efac.block.custom.GeumbBlock;
import net.seinsturg.efac.block.custom.GeumbClusterBlock;
import net.seinsturg.efac.item.EvansItems;

import java.util.function.Supplier;

public class EvansBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(EFAC.MOD_ID);


    public static final DeferredBlock<Block> CLUMB_BLOCK = registerBlock(
            "clumb_block", () -> new ClumbBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .destroyTime(0.4f)
                    .explosionResistance(0.4f)
                    .sound(SoundType.SLIME_BLOCK)));

    public static final DeferredBlock<Block> NILENE_BLOCK = registerBlock(
            "nilene_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .destroyTime(0.4f)
                    .explosionResistance(0.4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE))); //todo: custom sounds

    /// ores & resource blocks
    // philosophers, todo: smithing upgrade
    public static final DeferredBlock<Block> PHILOSOPHERS_ORE = registerBlock(
            "philosophers_ore", () -> new DropExperienceBlock(UniformInt.of(4, 16), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(15.0F, 1200.0F)));
    public static final DeferredBlock<Block> PHILOSOPHERS_BLOCK = registerBlock(
            "philosophers_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F)));
    // gems
    public static final DeferredBlock<Block> ALBY_ORE = registerBlock(
            "alby_ore", () -> new DropExperienceBlock(UniformInt.of(2, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> ALBY_BLOCK = registerBlock(
            "alby_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F, 4.0F)));
    public static final DeferredBlock<Block> CITRY_ORE = registerBlock(
            "citry_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> CITRY_BLOCK = registerBlock(
            "citry_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F, 4.0F)));
    public static final DeferredBlock<Block> RUBY_ORE = registerBlock(
            "ruby_ore", () -> new DropExperienceBlock(UniformInt.of(4, 12), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock(
            "ruby_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F, 4.0F)));

    // geumbs
    public static final DeferredBlock<Block> GEUMB_BLOCK = registerBlock(
            "geumb_block", () -> new GeumbBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.CHIME)
                    .requiresCorrectToolForDrops()
                    .strength(2.5F, 2.0F)));
    public static final DeferredBlock<Block> BUDDING_GEUMB = registerBlock(
            "budding_geumb", () -> new BuddingGeumbBlock(BlockBehaviour.Properties.of()
                    .randomTicks()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.CHIME)
                    .requiresCorrectToolForDrops()
                    .strength(2.5F, 2.0F)));
    public static final DeferredBlock<Block> GEUMB_CLUSTER = registerBlock(
            "geumb_cluster", () -> new GeumbClusterBlock(7, 3.0F, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> LARGE_GEUMB_BUD = registerBlock(
            "large_geumb_bud", () -> new GeumbClusterBlock(5, 3.0F, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> MEDIUM_GEUMB_BUD = registerBlock(
            "medium_geumb_bud", () -> new GeumbClusterBlock(4, 3.0F, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> SMALL_GEUMB_BUD = registerBlock(
            "small_geumb_bud", () -> new GeumbClusterBlock(3, 4.0F, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> GEUMB_TILES = registerBlock(
            "geumb_tiles", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_TILES)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> GEUMB_TILE_STAIRS = registerBlock("geumb_tile_stairs",
            () -> new StairBlock(EvansBlocks.GEUMB_TILES.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_ORANGE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> GEUMB_TILE_SLAB = registerBlock("geumb_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> GEUMB_TILE_WALL = registerBlock("geumb_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CLUMBY_GEUMB_TILES = registerBlock(
            "clumby_geumb_tiles", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_TILES)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> CLUMBY_GEUMB_TILE_STAIRS = registerBlock("clumby_geumb_tile_stairs",
            () -> new StairBlock(EvansBlocks.CLUMBY_GEUMB_TILES.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.TERRACOTTA_WHITE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> CLUMBY_GEUMB_TILE_SLAB = registerBlock("clumby_geumb_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> CLUMBY_GEUMB_TILE_WALL = registerBlock("clumby_geumb_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALBY_GEUMB_TILES = registerBlock(
            "alby_geumb_tiles", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_TILES)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> ALBY_GEUMB_TILE_STAIRS = registerBlock("alby_geumb_tile_stairs",
            () -> new StairBlock(EvansBlocks.ALBY_GEUMB_TILES.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> ALBY_GEUMB_TILE_SLAB = registerBlock("alby_geumb_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> ALBY_GEUMB_TILE_WALL = registerBlock("alby_geumb_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CITRY_GEUMB_TILES = registerBlock(
            "citry_geumb_tiles", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_TILES)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> CITRY_GEUMB_TILE_STAIRS = registerBlock("citry_geumb_tile_stairs",
            () -> new StairBlock(EvansBlocks.CITRY_GEUMB_TILES.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> CITRY_GEUMB_TILE_SLAB = registerBlock("citry_geumb_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> CITRY_GEUMB_TILE_WALL = registerBlock("citry_geumb_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RUBY_GEUMB_TILES = registerBlock(
            "ruby_geumb_tiles", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_TILES)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> RUBY_GEUMB_TILE_STAIRS = registerBlock("ruby_geumb_tile_stairs",
            () -> new StairBlock(EvansBlocks.RUBY_GEUMB_TILES.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.TERRACOTTA_RED)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> RUBY_GEUMB_TILE_SLAB = registerBlock("ruby_geumb_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> RUBY_GEUMB_TILE_WALL = registerBlock("ruby_geumb_tile_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    /// resources
    // gelwood
    public static final DeferredBlock<Block> GELWOOD_ORE = registerBlock(
            "gelwood_ore", () -> new DropExperienceBlock(UniformInt.of(0, 4), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.BAMBOO_WOOD)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2f)));
    public static final DeferredBlock<Block> GELWOOD_PLANKS = registerBlock(
            "gelwood_planks", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2f)));
    public static final DeferredBlock<StairBlock> GELWOOD_STAIRS = registerBlock("gelwood_stairs",
            () -> new StairBlock(EvansBlocks.GELWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f)));
    public static final DeferredBlock<SlabBlock> GELWOOD_SLAB = registerBlock("gelwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f)));
    public static final DeferredBlock<PressurePlateBlock> GELWOOD_PRESSURE_PLATE = registerBlock("gelwood_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f)));
    public static final DeferredBlock<ButtonBlock> GELWOOD_BUTTON = registerBlock("gelwood_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f).noCollission()));
    public static final DeferredBlock<FenceBlock> GELWOOD_FENCE = registerBlock("gelwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f)));
    public static final DeferredBlock<FenceGateBlock> GELWOOD_FENCE_GATE = registerBlock("gelwood_fence_gate",
            () -> new FenceGateBlock(WoodType.BAMBOO, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f)));
    public static final DeferredBlock<DoorBlock> GELWOOD_DOOR = registerBlock("gelwood_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> GELWOOD_TRAPDOOR = registerBlock("gelwood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.BAMBOO_WOOD)
                    .strength(2f).noOcclusion()));

    public static final DeferredBlock<Block> SLIPULON_ORE = registerBlock(
            "slipulon_ore", () -> new DropExperienceBlock(UniformInt.of(0, 6), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> SLIPULON_BLOCK = registerBlock(
            "slipulon_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.COPPER)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 4.0F)));

    public static final DeferredBlock<Block> BUTTER = registerBlock(
            "butter", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.SLIME_BLOCK)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(0.5F, 0.5F)));

    /// environment blocks
    public static final DeferredBlock<Block> GRONE = registerBlock(
            "grone", () -> new FallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(0.5F)
                    .sound(SoundType.SAND)
            ) {
                @Override
                protected MapCodec<? extends FallingBlock> codec() {
                    return null;
                }
    });

    public static final DeferredBlock<Block> GRONE_BRICKS = registerBlock(
            "grone_bricks", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> GRONE_BRICK_STAIRS = registerBlock("grone_brick_stairs",
            () -> new StairBlock(EvansBlocks.GRONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> GRONE_BRICK_SLAB = registerBlock("grone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> GRONE_BRICK_WALL = registerBlock("grone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RONE_BRICKS = registerBlock(
            "rone_bricks", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> RONE_BRICK_STAIRS = registerBlock("rone_brick_stairs",
            () -> new StairBlock(EvansBlocks.RONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> RONE_BRICK_SLAB = registerBlock("rone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> RONE_BRICK_WALL = registerBlock("rone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLONE_BRICKS = registerBlock(
            "blone_bricks", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> BLONE_BRICK_STAIRS = registerBlock("blone_brick_stairs",
            () -> new StairBlock(EvansBlocks.BLONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> BLONE_BRICK_SLAB = registerBlock("blone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> BLONE_BRICK_WALL = registerBlock("blone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PLONE_BRICKS = registerBlock(
            "plone_bricks", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> PLONE_BRICK_STAIRS = registerBlock("plone_brick_stairs",
            () -> new StairBlock(EvansBlocks.PLONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> PLONE_BRICK_SLAB = registerBlock("plone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> PLONE_BRICK_WALL = registerBlock("plone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE_BRICKS)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> COMPACT_DIRT = registerBlock(
            "compact_dirt", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.5F)
                    .sound(SoundType.GRAVEL)
    ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        EvansItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
