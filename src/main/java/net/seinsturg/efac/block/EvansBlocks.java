package net.seinsturg.efac.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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

    /// ores & resource blocks
    // philosophers, todo: tex, recipes, smithing upgrade
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

    /// resources
    // gelwood, todo: planks, variants, recipes
    public static final DeferredBlock<Block> GELWOOD_ORE = registerBlock(
            "gelwood_ore", () -> new DropExperienceBlock(UniformInt.of(0, 4), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .sound(SoundType.BAMBOO) // todo: sounds
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
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
