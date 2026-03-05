package net.seinsturg.efac.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.item.custom.ClumbRechargeItem;
import net.seinsturg.efac.item.custom.GeumbItem;
import net.seinsturg.efac.item.custom.SauceItem;

public class EvansItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EFAC.MOD_ID);

    public static final DeferredItem<Item> CLUMB_MATERIA = ITEMS.register(
            "clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> RANDOM_SAUCE = ITEMS.register(
            "random_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.RANDOM_SAUCE)));
    public static final DeferredItem<Item> GEUMB_SHARD = ITEMS.register(
            "geumb_shard", () -> new GeumbItem(new Item.Properties().food(EvansFoodProperties.GEUMB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
