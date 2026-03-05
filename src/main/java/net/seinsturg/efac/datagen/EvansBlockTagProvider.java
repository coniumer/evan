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
        tag(EvansTags.Blocks.PROVIDES_CHARGE_RARE)
                .add(EvansBlocks.GRONE.get())
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
                .add(Blocks.END_STONE);

        tag(EvansTags.Blocks.PROVIDES_CHARGE_ALWAYS)
                .add(EvansBlocks.CLUMB_BLOCK.get());

        tag(EvansTags.Blocks.CONSUMES_CHARGE)
                .add(Blocks.SOUL_SAND)
                .add(Blocks.SOUL_SOIL);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(EvansBlocks.CLUMB_BLOCK.get())
                .add(EvansBlocks.COMPACT_DIRT.get())
                .add(EvansBlocks.GRONE.get());
    }
}
