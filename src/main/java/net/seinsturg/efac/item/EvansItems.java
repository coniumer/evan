package net.seinsturg.efac.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;

public class EvansItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EFAC.MOD_ID);

    public static final DeferredItem<Item> CLUMB_MATERIA = ITEMS.register(
            "clumb_materia", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
