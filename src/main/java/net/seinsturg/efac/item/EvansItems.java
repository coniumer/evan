package net.seinsturg.efac.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.item.custom.*;

public class EvansItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EFAC.MOD_ID);

    //materials
    public static final DeferredItem<Item> CLUMB_MATERIA = ITEMS.register(
            "clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> RANDOM_SAUCE = ITEMS.register(
            "random_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.RANDOM_SAUCE)));
    public static final DeferredItem<Item> GEUMB_SHARD = ITEMS.register(
            "geumb_shard", () -> new GeumbItem(new Item.Properties().food(EvansFoodProperties.GEUMB)));
    //wands
    //todo ingredients and recipes
    public static final DeferredItem<Item> CLUMBY_WAND = ITEMS.register(
            "clumby_wand", () -> new WandItem(Tiers.STONE, WandTiers.CLUMBY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ALBY_WAND = ITEMS.register(
            "alby_wand", () -> new WandItem(Tiers.IRON, WandTiers.ALBY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CITRY_WAND = ITEMS.register(
            "citry_wand", () -> new WandItem(Tiers.DIAMOND, WandTiers.CITRY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> RUBIED_WAND = ITEMS.register(
            "rubied_wand", () -> new WandItem(Tiers.NETHERITE, WandTiers.RUBIED, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PHILOSOPHERS_WAND = ITEMS.register(
            "philosophers_wand", () -> new WandItem(EvansToolTiers.PHILOSOPHERS, WandTiers.PHILOSOPHERS, new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
