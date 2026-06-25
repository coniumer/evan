package net.seinsturg.efac.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;

public class EvansEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, EFAC.MOD_ID);

    public static final Holder<MobEffect> INSTANT_CLUMB_EFFECT = MOB_EFFECTS.register("instant_clumb",
            () -> new InstantClumbEffect(MobEffectCategory.BENEFICIAL, 0xd0c5b5));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
