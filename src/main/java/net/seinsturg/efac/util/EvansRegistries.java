package net.seinsturg.efac.util;

import net.neoforged.bus.api.IEventBus;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

public class EvansRegistries {

    public static void registerRegistries(IEventBus eventBus) {
        EvansBlocks.register(eventBus);
        EvansItems.register(eventBus);
    }
}
