package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.DashCharmPayload;
import net.seinsturg.efac.network.payload.LightningCharmPayload;
import net.seinsturg.efac.sound.EvansSounds;

public class DashCharmHandler {
    public static void handle(final DashCharmPayload payload, final IPayloadContext context) {
        context.player().level().playSound(null, context.player().getOnPos(), EvansSounds.CLUMB_DASH.get(), SoundSource.PLAYERS, (float) payload.i(), 1f);
    }
}
