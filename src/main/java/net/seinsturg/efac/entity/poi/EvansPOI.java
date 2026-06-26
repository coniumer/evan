package net.seinsturg.efac.entity.poi;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;

public class EvansPOI {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, EFAC.MOD_ID);

    public static final Holder<PoiType> CLUMBIST_POI = POI_TYPES.register("clumbist_poi",
            () -> new PoiType(ImmutableSet.copyOf(EvansBlocks.CLUMB_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<PoiType> SHAMAN_POI = POI_TYPES.register("shaman_poi",
            () -> new PoiType(ImmutableSet.copyOf(EvansBlocks.NILENE_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}
