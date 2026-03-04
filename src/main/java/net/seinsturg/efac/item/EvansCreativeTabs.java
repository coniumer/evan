package net.seinsturg.efac.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;

import java.util.function.Supplier;

public class EvansCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EFAC.MOD_ID);

    // for items related to clumbing
    public static final Supplier<CreativeModeTab> CLUMB_ITEM_TAB = CREATIVE_MODE_TAB.register("clumb_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EvansItems.CLUMB_MATERIA.get()))
                    .title(Component.translatable("creativetab.efac.clumb_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //blocks
                        output.accept(EvansBlocks.CLUMB_BLOCK);
                        //items
                        output.accept(EvansItems.CLUMB_MATERIA);

                    })
                    .build());

    //to add second, use .withTabsBefore(ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID, "clumb_item_tab"))

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
