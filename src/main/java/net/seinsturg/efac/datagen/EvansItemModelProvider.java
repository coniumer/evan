package net.seinsturg.efac.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

public class EvansItemModelProvider extends ItemModelProvider {
    public EvansItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EFAC.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //ingredients
        basicItem(EvansItems.CLUMB_MATERIA.get());
        basicItem(EvansItems.ALBY_CLUMB_MATERIA.get());
        basicItem(EvansItems.CITRY_CLUMB_MATERIA.get());
        basicItem(EvansItems.RUBIED_CLUMB_MATERIA.get());
        basicItem(EvansItems.RANDOM_SAUCE.get());
        basicItem(EvansItems.YUMMY_SAUCE.get());
        basicItem(EvansItems.AWESOME_SAUCE.get());
        basicItem(EvansItems.EPIC_SAUCE.get());
        basicItem(EvansItems.GEUMB_SHARD.get());
        basicItem(EvansItems.CLUMBY_GEUMB_SHARD.get());
        basicItem(EvansItems.ALBY_GEUMB_SHARD.get());
        basicItem(EvansItems.CITRY_GEUMB_SHARD.get());
        basicItem(EvansItems.RUBIED_GEUMB_SHARD.get());
        basicItem(EvansItems.ALBY_GEM.get());
        basicItem(EvansItems.CITRY_GEM.get());
        basicItem(EvansItems.RUBY_GEM.get());
        basicItem(EvansItems.PHILOSOPHERS_STONE.get());
        basicItem(EvansItems.GELWOOD_ORB.get());
        basicItem(EvansItems.RAW_SLIPULON.get());
        basicItem(EvansItems.SLIPULON_INGOT.get());
        basicItem(EvansItems.BUTTER_STICK.get());
        basicItem(EvansItems.BLOOD_CANISTER.get());
        basicItem(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE.get());

        //tools
        handheldItem(EvansItems.CLUMBY_WAND.get());
        handheldItem(EvansItems.CLUMBY_PULSAR.get());
        handheldItem(EvansItems.ALBY_WAND.get());
        handheldItem(EvansItems.ALBY_PULSAR.get());
        handheldItem(EvansItems.CITRY_WAND.get());
        handheldItem(EvansItems.CITRY_PULSAR.get());
        handheldItem(EvansItems.RUBIED_WAND.get());
        handheldItem(EvansItems.RUBIED_PULSAR.get());
        handheldItem(EvansItems.PHILOSOPHERS_WAND.get());
        handheldItem(EvansItems.PHILOSOPHERS_PULSAR.get());
        handheldItem(EvansItems.PHILOSOPHERS_SHOVEL.get());
        handheldItem(EvansItems.PHILOSOPHERS_PICKAXE.get());
        handheldItem(EvansItems.PHILOSOPHERS_AXE.get());
        handheldItem(EvansItems.PHILOSOPHERS_HOE.get());

        //charms
        handheldItem(EvansItems.DASH_CHARM.get());
        handheldItem(EvansItems.LIGHTNING_CHARM.get());
        handheldItem(EvansItems.PHILOSOPHERS_CHARM.get());

        //wood items
        buttonItem(EvansBlocks.GELWOOD_BUTTON, EvansBlocks.GELWOOD_PLANKS);
        fenceItem(EvansBlocks.GELWOOD_FENCE, EvansBlocks.GELWOOD_PLANKS);
        basicItem(EvansBlocks.GELWOOD_DOOR.asItem());

        //walls
        //wallItem(EvansBlocks.GEUMB_TILE_WALL, EvansBlocks.GEUMB_TILES);
        //wallItem(EvansBlocks.CLUMBY_GEUMB_TILE_WALL, EvansBlocks.CLUMBY_GEUMB_TILES);
        //wallItem(EvansBlocks.ALBY_GEUMB_TILE_WALL, EvansBlocks.ALBY_GEUMB_TILES);
        //wallItem(EvansBlocks.CITRY_GEUMB_TILE_WALL, EvansBlocks.CITRY_GEUMB_TILES);
        //wallItem(EvansBlocks.RUBY_GEUMB_TILE_WALL, EvansBlocks.RUBY_GEUMB_TILES);
        //wallItem(EvansBlocks.GRONE_BRICK_WALL, EvansBlocks.GRONE_BRICKS);
        //wallItem(EvansBlocks.RONE_BRICK_WALL, EvansBlocks.RONE_BRICKS);
        //wallItem(EvansBlocks.BLONE_BRICK_WALL, EvansBlocks.BLONE_BRICKS);
        //wallItem(EvansBlocks.PLONE_BRICK_WALL, EvansBlocks.PLONE_BRICKS);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
