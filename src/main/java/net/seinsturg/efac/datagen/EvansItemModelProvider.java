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
        basicItem(EvansItems.CLUMB_MATERIA.get());
    }
}
