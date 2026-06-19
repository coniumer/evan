package net.seinsturg.efac.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
        blockWithItem(EvansBlocks.PHILOSOPHERS_ORE);
        blockWithItem(EvansBlocks.PHILOSOPHERS_BLOCK);
        blockWithItem(EvansBlocks.CITRY_ORE);
        blockWithItem(EvansBlocks.CITRY_BLOCK);
        blockWithItem(EvansBlocks.RUBY_ORE);
        blockWithItem(EvansBlocks.RUBY_BLOCK);
        blockWithItem(EvansBlocks.GEUMB_BLOCK);
        blockWithItem(EvansBlocks.BUDDING_GEUMB);
        blockWithItem(EvansBlocks.GELWOOD_ORE);

        blockWithItem(EvansBlocks.GELWOOD_PLANKS);
        stairsBlock(EvansBlocks.GELWOOD_STAIRS.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        slabBlock(EvansBlocks.GELWOOD_SLAB.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        buttonBlock(EvansBlocks.GELWOOD_BUTTON.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        pressurePlateBlock(EvansBlocks.GELWOOD_PRESSURE_PLATE.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        fenceBlock(EvansBlocks.GELWOOD_FENCE.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        fenceGateBlock(EvansBlocks.GELWOOD_FENCE_GATE.get(), blockTexture(EvansBlocks.GELWOOD_PLANKS.get()));
        doorBlockWithRenderType(EvansBlocks.GELWOOD_DOOR.get(), modLoc("block/gelwood_door_bottom"), modLoc("block/gelwood_door_top"), "cutout");
        trapdoorBlockWithRenderType(EvansBlocks.GELWOOD_TRAPDOOR.get(), modLoc("block/gelwood_trapdoor"), true, "cutout");

        blockItem(EvansBlocks.GELWOOD_STAIRS);
        blockItem(EvansBlocks.GELWOOD_SLAB);
        blockItem(EvansBlocks.GELWOOD_PRESSURE_PLATE);
        blockItem(EvansBlocks.GELWOOD_FENCE_GATE);
        blockItem(EvansBlocks.GELWOOD_TRAPDOOR, "_bottom");

        blockWithItem(EvansBlocks.SLIPULON_ORE);
        blockWithItem(EvansBlocks.SLIPULON_BLOCK);
        blockWithItem(EvansBlocks.BUTTER);
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("efac:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("efac:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
