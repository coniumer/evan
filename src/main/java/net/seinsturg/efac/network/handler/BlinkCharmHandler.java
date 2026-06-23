package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.entity.custom.BlinkColliderEntity;
import net.seinsturg.efac.network.payload.BlinkCharmPayload;

public class BlinkCharmHandler {
    public static void handle(final BlinkCharmPayload payload, final IPayloadContext context) {
        BlinkColliderEntity collider = new BlinkColliderEntity(context.player(), context.player().level(), payload.movement());
        collider.setPos(context.player().getX(), context.player().getEyeY(), context.player().getZ());
        collider.shootFromRotation(context.player(), context.player().getXRot(), context.player().getYRot(), 0f, 10f, 0);
        context.player().level().addFreshEntity(collider);

        context.player().level().playSound(null, context.player().getOnPos(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 0.5f, 1.2f);
    }
}
