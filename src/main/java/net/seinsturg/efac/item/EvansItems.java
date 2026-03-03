package net.seinsturg.efac.item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;

public class EvansItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EFAC.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
