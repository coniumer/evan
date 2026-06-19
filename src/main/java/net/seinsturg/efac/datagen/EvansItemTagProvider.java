package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;
import net.seinsturg.efac.util.EvansTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EvansItemTagProvider extends ItemTagsProvider {
    public EvansItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EFAC.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EvansTags.Items.BYPASS_CHARGE_COST)
                .add(EvansItems.PHILOSOPHERS_CHARM.get());

        tag(ItemTags.SWORDS)
                .add(EvansItems.CLUMBY_PULSAR.get())
                .add(EvansItems.ALBY_PULSAR.get())
                .add(EvansItems.CITRY_PULSAR.get())
                .add(EvansItems.RUBIED_PULSAR.get())
                .add(EvansItems.PHILOSOPHERS_PULSAR.get());
        tag(ItemTags.PICKAXES)
                .add(EvansItems.PHILOSOPHERS_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(EvansItems.PHILOSOPHERS_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(EvansItems.PHILOSOPHERS_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(EvansItems.PHILOSOPHERS_HOE.get());

        tag(ItemTags.PLANKS)
                .add(EvansBlocks.GELWOOD_PLANKS.asItem());
    }
}
