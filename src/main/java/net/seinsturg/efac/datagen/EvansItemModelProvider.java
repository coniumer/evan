package net.seinsturg.efac.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.seinsturg.efac.EFAC;
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
        basicItem(EvansItems.GELWOOD_ORB.get());
        basicItem(EvansItems.RAW_SLIPULON.get());
        basicItem(EvansItems.SLIPULON_INGOT.get());
        basicItem(EvansItems.BUTTER_STICK.get());
        basicItem(EvansItems.BLOOD_CANISTER.get());

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
    }
}
