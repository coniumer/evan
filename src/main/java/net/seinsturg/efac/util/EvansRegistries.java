package net.seinsturg.efac.util;

import net.neoforged.bus.api.IEventBus;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.block.entity.EvansBlockEntities;
import net.seinsturg.efac.component.EvansComponents;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.effect.EvansEffects;
import net.seinsturg.efac.entity.EvansEntities;
import net.seinsturg.efac.entity.poi.EvansPOI;
import net.seinsturg.efac.entity.villager.EvansVillagers;
import net.seinsturg.efac.item.EvansCreativeTabs;
import net.seinsturg.efac.item.EvansItems;
import net.seinsturg.efac.loot.EvansLootModifiers;
import net.seinsturg.efac.potion.EvansPotions;
import net.seinsturg.efac.screen.EvansMenuTypes;
import net.seinsturg.efac.sound.EvansSounds;

public class EvansRegistries {

    public static void registerRegistries(IEventBus eventBus) {
        EvansCreativeTabs.register(eventBus);
        EvansBlocks.register(eventBus);
        EvansItems.register(eventBus);
        EvansPotions.register(eventBus);
        EvansEffects.register(eventBus);
        EvansEntities.register(eventBus);
        EvansBlockEntities.register(eventBus);
        EvansMenuTypes.register(eventBus);
        EvansPOI.register(eventBus);
        EvansVillagers.register(eventBus);
        EvansSounds.register(eventBus);
        EvansData.register(eventBus);
        EvansComponents.register(eventBus);
        EvansLootModifiers.register(eventBus);
    }
}
