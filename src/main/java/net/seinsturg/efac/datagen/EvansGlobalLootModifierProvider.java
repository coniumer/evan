package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.item.EvansItems;
import net.seinsturg.efac.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class EvansGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public EvansGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, EFAC.MOD_ID);
    }

    @Override
    protected void start() {
        add("clumb_materia_from_zombie",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/zombie")).build()
                }, EvansItems.CLUMB_MATERIA.get(), 0, 3));
        add("looting_clumb_materia_from_zombie",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/zombie")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.22f).build()
                }, EvansItems.CLUMB_MATERIA.get(), 1, 3));

        add("clumb_materia_from_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/skeleton")).build()
                }, EvansItems.CLUMB_MATERIA.get(), 0, 5));
        add("looting_clumb_materia_from_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/skeleton")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.25f).build()
                }, EvansItems.CLUMB_MATERIA.get(), 1, 4));

        add("random_sauce_from_spider",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/spider")).build()
                }, EvansItems.RANDOM_SAUCE.get(), 0, 3));
        add("looting_random_sauce_from_spider",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/spider")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.3f).build()
                }, EvansItems.RANDOM_SAUCE.get(), 1, 4));

        add("random_sauce_from_creeper",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/creeper")).build()
                }, EvansItems.RANDOM_SAUCE.get(), 0, 3));
        add("looting_random_sauce_from_creeper",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/creeper")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.3f).build()
                }, EvansItems.RANDOM_SAUCE.get(), 1, 4));
        add("looting_yummy_sauce_from_creeper",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/creeper")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.1f, 0.15f).build()
                }, EvansItems.YUMMY_SAUCE.get(), 0, 2));

        add("yummy_sauce_from_enderman",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/enderman")).build()
                }, EvansItems.YUMMY_SAUCE.get(), 0, 3));
        add("looting_yummy_sauce_from_enderman",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/enderman")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.3f).build()
                }, EvansItems.YUMMY_SAUCE.get(), 1, 4));
        add("looting_awesome_sauce_from_enderman",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/enderman")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.1f, 0.15f).build()
                }, EvansItems.AWESOME_SAUCE.get(), 0, 2));

        add("awesome_sauce_from_blaze",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/blaze")).build()
                }, EvansItems.AWESOME_SAUCE.get(), 0, 2));
        add("looting_awesome_sauce_from_blaze",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/blaze")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.3f).build()
                }, EvansItems.AWESOME_SAUCE.get(), 0, 2));

        add("awesome_sauce_from_wither_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/wither_skeleton")).build()
                }, EvansItems.AWESOME_SAUCE.get(), 0, 3));
        add("looting_awesome_sauce_from_wither_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/wither_skeleton")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.15f, 0.3f).build()
                }, EvansItems.AWESOME_SAUCE.get(), 1, 4));
        add("looting_epic_sauce_from_wither_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/wither_skeleton")).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.1f, 0.15f).build()
                }, EvansItems.EPIC_SAUCE.get(), 0, 2));
    }

}
