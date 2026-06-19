package net.seinsturg.efac.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;

public class EvansBlockStateProvider extends BlockStateProvider {
    public EvansBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EFAC.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(EvansBlocks.GRONE);
        blockWithItem(EvansBlocks.COMPACT_DIRT);
        blockWithItem(EvansBlocks.ALBY_ORE);
        blockWithItem(EvansBlocks.ALBY_BLOCK);
        blockWithItem(EvansBlocks.CITRY_ORE);
        blockWithItem(EvansBlocks.CITRY_BLOCK);
        blockWithItem(EvansBlocks.RUBY_ORE);
        blockWithItem(EvansBlocks.RUBY_BLOCK);
        blockWithItem(EvansBlocks.GEUMB_BLOCK);
        blockWithItem(EvansBlocks.BUDDING_GEUMB);
        blockWithItem(EvansBlocks.GELWOOD_ORE);
        blockWithItem(EvansBlocks.SLIPULON_ORE);
        blockWithItem(EvansBlocks.SLIPULON_BLOCK);
        blockWithItem(EvansBlocks.BUTTER);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
