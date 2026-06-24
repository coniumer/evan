package net.seinsturg.efac.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
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


                        ///wands & swords
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
                        ///philosophers tools
                        output.accept(EvansItems.PHILOSOPHERS_SHOVEL);
                        output.accept(EvansItems.PHILOSOPHERS_PICKAXE);
                        output.accept(EvansItems.PHILOSOPHERS_AXE);
                        output.accept(EvansItems.PHILOSOPHERS_HOE);

                        output.accept(EvansItems.PHILOSOPHERS_HELMET);
                        output.accept(EvansItems.PHILOSOPHERS_CHESTPLATE);
                        output.accept(EvansItems.PHILOSOPHERS_LEGGINGS);
                        output.accept(EvansItems.PHILOSOPHERS_BOOTS);
                        ///blocks
                        output.accept(EvansBlocks.CLUMB_BLOCK);
                        output.accept(EvansBlocks.NILENE_BLOCK);
                        output.accept(EvansBlocks.ALBY_ORE);
                        output.accept(EvansBlocks.ALBY_BLOCK);
                        output.accept(EvansBlocks.CITRY_ORE);
                        output.accept(EvansBlocks.CITRY_BLOCK);
                        output.accept(EvansBlocks.RUBY_ORE);
                        output.accept(EvansBlocks.RUBY_BLOCK);
                        output.accept(EvansBlocks.PHILOSOPHERS_ORE);
                        output.accept(EvansBlocks.PHILOSOPHERS_BLOCK);

                        output.accept(EvansBlocks.GEUMB_BLOCK);
                        output.accept(EvansBlocks.BUDDING_GEUMB);
                        output.accept(EvansBlocks.GEUMB_TILES);
                        output.accept(EvansBlocks.GEUMB_TILE_STAIRS);
                        output.accept(EvansBlocks.GEUMB_TILE_SLAB);
                        output.accept(EvansBlocks.GEUMB_TILE_WALL);
                        output.accept(EvansBlocks.CLUMBY_GEUMB_TILES);
                        output.accept(EvansBlocks.CLUMBY_GEUMB_TILE_STAIRS);
                        output.accept(EvansBlocks.CLUMBY_GEUMB_TILE_SLAB);
                        output.accept(EvansBlocks.CLUMBY_GEUMB_TILE_WALL);
                        output.accept(EvansBlocks.ALBY_GEUMB_TILES);
                        output.accept(EvansBlocks.ALBY_GEUMB_TILE_STAIRS);
                        output.accept(EvansBlocks.ALBY_GEUMB_TILE_SLAB);
                        output.accept(EvansBlocks.ALBY_GEUMB_TILE_WALL);
                        output.accept(EvansBlocks.CITRY_GEUMB_TILES);
                        output.accept(EvansBlocks.CITRY_GEUMB_TILE_STAIRS);
                        output.accept(EvansBlocks.CITRY_GEUMB_TILE_SLAB);
                        output.accept(EvansBlocks.CITRY_GEUMB_TILE_WALL);
                        output.accept(EvansBlocks.RUBY_GEUMB_TILES);
                        output.accept(EvansBlocks.RUBY_GEUMB_TILE_STAIRS);
                        output.accept(EvansBlocks.RUBY_GEUMB_TILE_SLAB);
                        output.accept(EvansBlocks.RUBY_GEUMB_TILE_WALL);

                        ///items
                        output.accept(EvansItems.CLUMB_MATERIA);
                        output.accept(EvansItems.ALBY_CLUMB_MATERIA);
                        output.accept(EvansItems.CITRY_CLUMB_MATERIA);
                        output.accept(EvansItems.RUBIED_CLUMB_MATERIA);
                        output.accept(EvansItems.GEUMB_SHARD);
                        output.accept(EvansItems.CLUMBY_GEUMB_SHARD);
                        output.accept(EvansItems.ALBY_GEUMB_SHARD);
                        output.accept(EvansItems.CITRY_GEUMB_SHARD);
                        output.accept(EvansItems.RUBIED_GEUMB_SHARD);
                        output.accept(EvansItems.RANDOM_SAUCE);
                        output.accept(EvansItems.YUMMY_SAUCE);
                        output.accept(EvansItems.AWESOME_SAUCE);
                        output.accept(EvansItems.EPIC_SAUCE);
                        output.accept(EvansItems.ALBY_GEM);
                        output.accept(EvansItems.CITRY_GEM);
                        output.accept(EvansItems.RUBY_GEM);
                        output.accept(EvansItems.PHILOSOPHERS_STONE);
                        output.accept(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(EvansItems.BLOOD_CANISTER);
                        output.accept(EvansItems.CLUMBELON);
                        output.accept(EvansItems.CLUMBKIE);
                        output.accept(EvansItems.BLUMB);
                        output.accept(EvansItems.CLUMBROT);
                        output.accept(EvansItems.GOLD_CLUMBROT);
                        output.accept(EvansItems.CLUMBLE);
                        output.accept(EvansItems.GOLD_CLUMBLE);
                        output.accept(EvansItems.CLUMBURGER);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> CHARM_TAB = CREATIVE_MODE_TAB.register("charm_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EvansItems.PHILOSOPHERS_CHARM.get()))
                    .title(Component.translatable("creativetab.efac.charm_tab"))
                    .withTabsBefore(EFAC.res("clumb_item_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(EvansItems.HEALTH_CHARM);
                        output.accept(EvansItems.DASH_CHARM);
                        output.accept(EvansItems.BLINK_CHARM);
                        output.accept(EvansItems.LIGHTNING_CHARM);
                        output.accept(EvansItems.FIREBALL_CHARM);
                        output.accept(EvansItems.PRANK_CHARM);
                        output.accept(EvansItems.PHILOSOPHERS_CHARM);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> ENVIRONMENT_TAB = CREATIVE_MODE_TAB.register("environment_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EvansBlocks.GRONE.get()))
                    .title(Component.translatable("creativetab.efac.environment_tab"))
                    .withTabsBefore(EFAC.res("charm_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Blocks.GRAVEL);
                        output.accept(EvansBlocks.BLONE_BRICKS);
                        output.accept(EvansBlocks.BLONE_BRICK_STAIRS);
                        output.accept(EvansBlocks.BLONE_BRICK_SLAB);
                        output.accept(EvansBlocks.BLONE_BRICK_WALL);

                        output.accept(Blocks.SAND);
                        output.accept(EvansBlocks.PLONE_BRICKS);
                        output.accept(EvansBlocks.PLONE_BRICK_STAIRS);
                        output.accept(EvansBlocks.PLONE_BRICK_SLAB);
                        output.accept(EvansBlocks.PLONE_BRICK_WALL);

                        output.accept(Blocks.RED_SAND);
                        output.accept(EvansBlocks.RONE_BRICKS);
                        output.accept(EvansBlocks.RONE_BRICK_STAIRS);
                        output.accept(EvansBlocks.RONE_BRICK_SLAB);
                        output.accept(EvansBlocks.RONE_BRICK_WALL);

                        output.accept(EvansBlocks.GRONE);
                        output.accept(EvansBlocks.GRONE_BRICKS);
                        output.accept(EvansBlocks.GRONE_BRICK_STAIRS);
                        output.accept(EvansBlocks.GRONE_BRICK_SLAB);
                        output.accept(EvansBlocks.GRONE_BRICK_WALL);

                        output.accept(EvansBlocks.GELWOOD_ORE);
                        output.accept(EvansBlocks.GELWOOD_PLANKS);
                        output.accept(EvansBlocks.GELWOOD_STAIRS);
                        output.accept(EvansBlocks.GELWOOD_SLAB);
                        output.accept(EvansBlocks.GELWOOD_PRESSURE_PLATE);
                        output.accept(EvansBlocks.GELWOOD_BUTTON);
                        output.accept(EvansBlocks.GELWOOD_FENCE);
                        output.accept(EvansBlocks.GELWOOD_FENCE_GATE);
                        output.accept(EvansBlocks.GELWOOD_DOOR);
                        output.accept(EvansBlocks.GELWOOD_TRAPDOOR);
                        output.accept(EvansItems.GELWOOD_ORB);
                        output.accept(EvansBlocks.SLIPULON_ORE);
                        output.accept(EvansBlocks.SLIPULON_BLOCK);
                        output.accept(EvansItems.RAW_SLIPULON);
                        output.accept(EvansItems.SLIPULON_INGOT);
                        output.accept(EvansBlocks.BUTTER);
                        output.accept(EvansItems.BUTTER_STICK);
                        output.accept(EvansItems.BURNT_CHICKEN);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
