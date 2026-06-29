package net.seinsturg.efac.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;

import java.util.function.Supplier;

public class EvansBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EFAC.MOD_ID);

    public static final Supplier<BlockEntityType<ClumbHarvesterBlockEntity>> CLUMB_HARVESTER_BE =
            BLOCK_ENTITIES.register("clumb_harvester_be", () -> BlockEntityType.Builder.of(
                    ClumbHarvesterBlockEntity::new, EvansBlocks.CLUMB_HARVESTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
