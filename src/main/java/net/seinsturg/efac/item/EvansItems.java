package net.seinsturg.efac.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.component.EvansComponents;
import net.seinsturg.efac.item.component.PulsarComponent;
import net.seinsturg.efac.item.custom.*;
import net.seinsturg.efac.item.custom.charms.DashCharm;
import net.seinsturg.efac.item.custom.charms.LightningCharm;
import net.seinsturg.efac.item.custom.charms.PhilosopherCharm;

import java.util.List;

public class EvansItems {
    private static final Component PHILOSOPHERS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID, "philosophers_upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component PHILOSOPHERS_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID, "smithing_template.philosophers_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    private static final Component PHILOSOPHERS_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,"smithing_template.philosophers_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    private static final Component PHILOSOPHERS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,"smithing_template.philosophers_upgrade.base_slot_description")));
    private static final Component PHILOSOPHERS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,"smithing_template.philosophers_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EFAC.MOD_ID);

    //materials
    public static final DeferredItem<Item> CLUMB_MATERIA = ITEMS.register(
            "clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> ALBY_CLUMB_MATERIA = ITEMS.register(
            "alby_clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> CITRY_CLUMB_MATERIA = ITEMS.register(
            "citry_clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> RUBIED_CLUMB_MATERIA = ITEMS.register(
            "rubied_clumb_materia", () -> new ClumbRechargeItem(new Item.Properties().food(EvansFoodProperties.CLUMB_MATERIA)));
    public static final DeferredItem<Item> RANDOM_SAUCE = ITEMS.register(
            "random_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.SAUCE)));
    public static final DeferredItem<Item> YUMMY_SAUCE = ITEMS.register(
            "yummy_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.SAUCE)));
    public static final DeferredItem<Item> AWESOME_SAUCE = ITEMS.register(
            "awesome_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.SAUCE)));
    public static final DeferredItem<Item> EPIC_SAUCE = ITEMS.register(
            "epic_sauce", () -> new SauceItem(new Item.Properties().food(EvansFoodProperties.SAUCE)));
    public static final DeferredItem<Item> GEUMB_SHARD = ITEMS.register(
            "geumb_shard", () -> new Item(new Item.Properties().food(EvansFoodProperties.GEUMB)));
    public static final DeferredItem<Item> CLUMBY_GEUMB_SHARD = ITEMS.register(
            "clumby_geumb_shard", () -> new GeumbItem(GeumbEnum.CLUMBY, new Item.Properties().food(EvansFoodProperties.GEUMB)));
    public static final DeferredItem<Item> ALBY_GEUMB_SHARD = ITEMS.register(
            "alby_geumb_shard", () -> new GeumbItem(GeumbEnum.ALBY, new Item.Properties().food(EvansFoodProperties.GEUMB)));
    public static final DeferredItem<Item> CITRY_GEUMB_SHARD = ITEMS.register(
            "citry_geumb_shard", () -> new GeumbItem(GeumbEnum.CITRY, new Item.Properties().food(EvansFoodProperties.GEUMB)));
    public static final DeferredItem<Item> RUBIED_GEUMB_SHARD = ITEMS.register(
            "rubied_geumb_shard", () -> new GeumbItem(GeumbEnum.RUBIED, new Item.Properties().food(EvansFoodProperties.GEUMB)));
    public static final DeferredItem<Item> ALBY_GEM = ITEMS.register(
            "alby_gem", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CITRY_GEM = ITEMS.register(
            "citry_gem", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_GEM = ITEMS.register(
            "ruby_gem", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PHILOSOPHERS_STONE = ITEMS.register(
            "philosophers_stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GELWOOD_ORB = ITEMS.register(
            "gelwood_orb", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_SLIPULON = ITEMS.register(
            "raw_slipulon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SLIPULON_INGOT = ITEMS.register(
            "slipulon_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BUTTER_STICK = ITEMS.register(
            "butter_stick", () -> new Item(new Item.Properties().food(EvansFoodProperties.BUTTER)));
    public static final DeferredItem<Item> BLOOD_CANISTER = ITEMS.register(
            "blood_canister", () -> new BloodCanisterItem(new Item.Properties().food(EvansFoodProperties.BLOOD_CANISTER)));

    public static SmithingTemplateItem createPhilosophersUpgradeTemplate() {
        return new SmithingTemplateItem(PHILOSOPHERS_UPGRADE_APPLIES_TO,
                PHILOSOPHERS_UPGRADE_INGREDIENTS,
                PHILOSOPHERS_UPGRADE,
                PHILOSOPHERS_UPGRADE_BASE_SLOT_DESCRIPTION,
                PHILOSOPHERS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createPhilosophersUpgradeIconList(),
                createPhilosophersUpgradeMaterialList(),
                new FeatureFlag[0]);
    }

    private static List<ResourceLocation> createPhilosophersUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD,
                EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE,
                EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createPhilosophersUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static final DeferredItem<Item> PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register(
            "philosophers_upgrade_smithing_template", EvansItems::createPhilosophersUpgradeTemplate);

    //wands
    public static final DeferredItem<Item> CLUMBY_WAND = ITEMS.register(
            "clumby_wand", () -> new WandItem(Tiers.STONE, WandTiers.CLUMBY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ALBY_WAND = ITEMS.register(
            "alby_wand", () -> new WandItem(Tiers.IRON, WandTiers.ALBY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CITRY_WAND = ITEMS.register(
            "citry_wand", () -> new WandItem(Tiers.DIAMOND, WandTiers.CITRY, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> RUBIED_WAND = ITEMS.register(
            "rubied_wand", () -> new WandItem(Tiers.NETHERITE, WandTiers.RUBIED, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PHILOSOPHERS_WAND = ITEMS.register(
            "philosophers_wand", () -> new WandItem(EvansToolTiers.PHILOSOPHERS, WandTiers.PHILOSOPHERS, new Item.Properties()
            .fireResistant().stacksTo(1)));
    //swords
    public static final DeferredItem<Item> CLUMBY_PULSAR = ITEMS.register(
            "clumby_pulsar", () -> new PulsarItem(Tiers.STONE,
            PulsarTiers.CLUMBY, new Item.Properties().attributes(PulsarItem.createAttributes(
            Tiers.STONE, 3.0f, -2.4f))
            .component(EvansComponents.PULSAR_COMPONENT.value(), new PulsarComponent(false))));
    public static final DeferredItem<Item> ALBY_PULSAR = ITEMS.register(
            "alby_pulsar", () -> new PulsarItem(Tiers.IRON,
            PulsarTiers.ALBY, new Item.Properties().attributes(PulsarItem.createAttributes(
            Tiers.IRON, 3.0f, -2.4f))
            .component(EvansComponents.PULSAR_COMPONENT.value(), new PulsarComponent(false))));
    public static final DeferredItem<Item> CITRY_PULSAR = ITEMS.register(
            "citry_pulsar", () -> new PulsarItem(Tiers.DIAMOND,
            PulsarTiers.CITRY, new Item.Properties().attributes(PulsarItem.createAttributes(
            Tiers.DIAMOND, 3.0f, -2.4f))
            .component(EvansComponents.PULSAR_COMPONENT.value(), new PulsarComponent(false))));
    public static final DeferredItem<Item> RUBIED_PULSAR = ITEMS.register(
            "rubied_pulsar", () -> new PulsarItem(Tiers.NETHERITE,
            PulsarTiers.RUBIED, new Item.Properties().attributes(PulsarItem.createAttributes(
            Tiers.NETHERITE, 3.0f, -2.4f))
            .component(EvansComponents.PULSAR_COMPONENT.value(), new PulsarComponent(false))));
    public static final DeferredItem<Item> PHILOSOPHERS_PULSAR = ITEMS.register(
            "philosophers_pulsar", () -> new PulsarItem(EvansToolTiers.PHILOSOPHERS,
            PulsarTiers.PHILOSOPHERS, new Item.Properties().fireResistant().attributes(PulsarItem.createAttributes(
            EvansToolTiers.PHILOSOPHERS, 3.0f, -2.4f))
            .component(EvansComponents.PULSAR_COMPONENT.value(), new PulsarComponent(false))));
    //philosophers tools
    public static final DeferredItem<Item> PHILOSOPHERS_SHOVEL = ITEMS.register(
            "philosophers_shovel", () -> new ShovelItem(EvansToolTiers.PHILOSOPHERS,
            new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(
            EvansToolTiers.PHILOSOPHERS, 1.0f, -3.0f))));
    public static final DeferredItem<Item> PHILOSOPHERS_PICKAXE = ITEMS.register(
            "philosophers_pickaxe", () -> new PickaxeItem(EvansToolTiers.PHILOSOPHERS,
            new Item.Properties().fireResistant().attributes(PickaxeItem.createAttributes(
            EvansToolTiers.PHILOSOPHERS, 1.0f, -2.8f))));
    public static final DeferredItem<Item> PHILOSOPHERS_AXE = ITEMS.register(
            "philosophers_axe", () -> new AxeItem(EvansToolTiers.PHILOSOPHERS,
            new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(
            EvansToolTiers.PHILOSOPHERS, 5.0f, -3.0f))));
    public static final DeferredItem<Item> PHILOSOPHERS_HOE = ITEMS.register(
            "philosophers_hoe", () -> new HoeItem(EvansToolTiers.PHILOSOPHERS,
            new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(
            EvansToolTiers.PHILOSOPHERS, 8.0f, -6.0f))));
    //charms
    public static final DeferredItem<Item> LIGHTNING_CHARM = ITEMS.register(
            "lightning_charm", () -> new LightningCharm(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DASH_CHARM = ITEMS.register(
            "dash_charm", () -> new DashCharm(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PHILOSOPHERS_CHARM = ITEMS.register(
            "philosophers_charm", () -> new PhilosopherCharm(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
