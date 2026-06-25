package net.seinsturg.efac.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.effect.EvansEffects;


public class EvansPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, EFAC.MOD_ID);

    public static final Holder<Potion> CLUMB_POTION = POTIONS.register("efac.instant_clumb",
            () -> new Potion(new MobEffectInstance(EvansEffects.INSTANT_CLUMB_EFFECT)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
