package net.seinsturg.efac.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;

import java.util.function.Supplier;

public class EvansCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EFAC.MOD_ID);

    //Vanilla tabs
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EvansBlocks.GRONE);
            event.accept(EvansBlocks.COMPACT_DIRT);
            event.accept(EvansBlocks.CLUMB_BLOCK);
        }
    }

    // for items related to clumbing
    public static final Supplier<CreativeModeTab> CLUMB_ITEM_TAB = CREATIVE_MODE_TAB.register("clumb_item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EvansItems.CLUMB_MATERIA.get()))
                    .title(Component.translatable("creativetab.efac.clumb_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //blocks
                        output.accept(EvansBlocks.CLUMB_BLOCK);
                        //items
                        output.accept(EvansItems.CLUMB_MATERIA);
                        output.accept(EvansItems.GEUMB_SHARD);
                        output.accept(EvansItems.BLOOD_CANISTER);
                        output.accept(EvansItems.RANDOM_SAUCE);
                        //wands & swords
                        output.accept(EvansItems.CLUMBY_WAND);
                        output.accept(EvansItems.CLUMBY_PULSAR);
                        output.accept(EvansItems.ALBY_WAND);
                        output.accept(EvansItems.ALBY_PULSAR);
                        output.accept(EvansItems.CITRY_WAND);
                        output.accept(EvansItems.CITRY_PULSAR);
                        output.accept(EvansItems.RUBIED_WAND);
                        output.accept(EvansItems.RUBIED_PULSAR);
                        output.accept(EvansItems.PHILOSOPHERS_WAND);
                        output.accept(EvansItems.PHILOSOPHERS_PULSAR);
                        //philosophers tools
                        output.accept(EvansItems.PHILOSOPHERS_SHOVEL);
                        output.accept(EvansItems.PHILOSOPHERS_PICKAXE);
                        output.accept(EvansItems.PHILOSOPHERS_AXE);
                        output.accept(EvansItems.PHILOSOPHERS_HOE);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> CHARM_TAB = CREATIVE_MODE_TAB.register("charm_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EvansItems.PHILOSOPHERS_CHARM.get()))
                    .title(Component.translatable("creativetab.efac.charm_tab"))
                    .withTabsBefore(EFAC.res("clumb_item_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(EvansItems.DASH_CHARM);
                        output.accept(EvansItems.LIGHTNING_CHARM);
                        output.accept(EvansItems.PHILOSOPHERS_CHARM);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
