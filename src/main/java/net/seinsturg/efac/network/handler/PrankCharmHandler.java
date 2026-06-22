package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.PrankCharmPayload;

public class PrankCharmHandler {
    public static void handle(final PrankCharmPayload payload, final IPayloadContext context) {
        //todo: enchantments for fuse & toss mult

        //summon tnt
        PrimedTnt tnt = new PrimedTnt(context.player().level(), payload.pos().x, payload.pos().y, payload.pos().z, context.player());
        tnt.setPos(payload.pos());

        Vec3 lookDir = payload.lookDir();
        Vec3 velocity = new Vec3(lookDir.x * payload.mult(), lookDir.y * payload.mult(), lookDir.z * payload.mult());
        tnt.setDeltaMovement(velocity);

        tnt.setFuse(payload.fuse());

        context.player().level().addFreshEntity(tnt);

        //play sound
        context.player().level().playSound(null, context.player().getOnPos(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1f, 1.2f);
    }
}
