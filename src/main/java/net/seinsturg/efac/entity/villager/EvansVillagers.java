package net.seinsturg.efac.entity.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.entity.poi.EvansPOI;
import net.seinsturg.efac.sound.EvansSounds;

public class EvansVillagers {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, EFAC.MOD_ID);

    public static final Holder<VillagerProfession> CLUMBIST = VILLAGER_PROFESSIONS.register("clumbist",
            () -> new VillagerProfession("clumbist", holder -> holder.value() == EvansPOI.CLUMBIST_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == EvansPOI.CLUMBIST_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    EvansSounds.CLUMB_CHARGE.get()));

    public static final Holder<VillagerProfession> SHAMAN = VILLAGER_PROFESSIONS.register("shaman",
            () -> new VillagerProfession("shaman", holder -> holder.value() == EvansPOI.SHAMAN_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == EvansPOI.SHAMAN_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    EvansSounds.CLUMB_DASH.get()));

    public static void register(IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
