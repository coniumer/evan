package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.FireballCharmPayload;

public class FireballCharmHandler {
    public static void handle(final FireballCharmPayload payload, final IPayloadContext context) {
        //summon fireball
        SmallFireball fireball = new SmallFireball(context.player().level(), context.player(), payload.lookDir());
        fireball.setPos(payload.pos().x, payload.pos().y, payload.pos().z);
        context.player().level().addFreshEntity(fireball);

        //play sound
        context.player().level().playSound(null, context.player().getOnPos(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1f, 1f);
    }
}
