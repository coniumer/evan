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
        blockWithItem(EvansBlocks.CLUMB_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
