package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.util.EvansTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EvansBlockTagProvider extends BlockTagsProvider {
    public EvansBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EFAC.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ///charge tags
        tag(EvansTags.Blocks.PROVIDES_CHARGE_RARE)
                .add(EvansBlocks.GRONE.get())
                .add(EvansBlocks.ALBY_ORE.get())
                .add(EvansBlocks.CITRY_ORE.get())
                .add(EvansBlocks.RUBY_ORE.get())
                .add(EvansBlocks.SLIPULON_ORE.get())
                .add(EvansBlocks.SLIPULON_BLOCK.get())
                .add(Blocks.GRAVEL)
                .add(Blocks.SAND)
                .add(Blocks.RED_SAND)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.STONE)
                .add(Blocks.DIORITE)
                .add(Blocks.ANDESITE)
                .add(Blocks.GRANITE)
                .add(Blocks.TUFF);

        tag(EvansTags.Blocks.PROVIDES_CHARGE_COMMON)
                .add(EvansBlocks.COMPACT_DIRT.get())
                .add(EvansBlocks.GEUMB_BLOCK.get())
                .add(EvansBlocks.BUDDING_GEUMB.get())
                .add(EvansBlocks.GEUMB_CLUSTER.get())
                .add(EvansBlocks.LARGE_GEUMB_BUD.get())
                .add(EvansBlocks.MEDIUM_GEUMB_BUD.get())
                .add(EvansBlocks.SMALL_GEUMB_BUD.get())
                .add(Blocks.END_STONE);

        tag(EvansTags.Blocks.PROVIDES_CHARGE_ALWAYS)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get())
                .add(EvansBlocks.CLUMB_BLOCK.get());

        tag(EvansTags.Blocks.CONSUMES_CHARGE)
                .add(Blocks.SOUL_SAND)
                .add(Blocks.SOUL_SOIL);

        ///tool tags
        //tool type
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get())
                .add(EvansBlocks.ALBY_ORE.get())
                .add(EvansBlocks.ALBY_BLOCK.get())
                .add(EvansBlocks.CITRY_ORE.get())
                .add(EvansBlocks.CITRY_BLOCK.get())
                .add(EvansBlocks.RUBY_ORE.get())
                .add(EvansBlocks.RUBY_BLOCK.get())
                .add(EvansBlocks.GELWOOD_ORE.get())
                .add(EvansBlocks.SLIPULON_ORE.get())
                .add(EvansBlocks.SLIPULON_BLOCK.get())
                .add(EvansBlocks.GEUMB_BLOCK.get())
                .add(EvansBlocks.GEUMB_CLUSTER.get())
                .add(EvansBlocks.LARGE_GEUMB_BUD.get())
                .add(EvansBlocks.MEDIUM_GEUMB_BUD.get())
                .add(EvansBlocks.SMALL_GEUMB_BUD.get())
                .add(EvansBlocks.BUDDING_GEUMB.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(EvansBlocks.CLUMB_BLOCK.get())
                .add(EvansBlocks.COMPACT_DIRT.get())
                .add(EvansBlocks.BUTTER.get())
                .add(EvansBlocks.GRONE.get());

        //tool tier
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(EvansBlocks.ALBY_ORE.get())
                .add(EvansBlocks.ALBY_BLOCK.get())
                .add(EvansBlocks.GELWOOD_ORE.get())
                .add(EvansBlocks.SLIPULON_ORE.get())
                .add(EvansBlocks.SLIPULON_BLOCK.get())
                .add(EvansBlocks.GEUMB_BLOCK.get())
                .add(EvansBlocks.GEUMB_CLUSTER.get())
                .add(EvansBlocks.LARGE_GEUMB_BUD.get())
                .add(EvansBlocks.MEDIUM_GEUMB_BUD.get())
                .add(EvansBlocks.SMALL_GEUMB_BUD.get())
                .add(EvansBlocks.BUDDING_GEUMB.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(EvansBlocks.CITRY_ORE.get())
                .add(EvansBlocks.CITRY_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(EvansBlocks.RUBY_ORE.get())
                .add(EvansBlocks.RUBY_BLOCK.get());

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get());
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get());
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get());
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get());
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add(EvansBlocks.PHILOSOPHERS_ORE.get())
                .add(EvansBlocks.PHILOSOPHERS_BLOCK.get());
    }
}
