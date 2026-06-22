package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.HealthCharmPayload;

public class HealthCharmHandler {
    public static void handle(final HealthCharmPayload payload, final IPayloadContext context) {
        //heal player
        context.player().heal(payload.amt());
        //apply regen when appropriate
        if (payload.regenFlag()) {
            MobEffectInstance regen = new MobEffectInstance(MobEffects.REGENERATION, payload.regenDuration(), payload.regenAmp(), false, false, false);
            context.player().addEffect(regen);
        }
        //play sound
        context.player().level().playSound(null, context.player().getOnPos(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1f, 1f);
    }
}
