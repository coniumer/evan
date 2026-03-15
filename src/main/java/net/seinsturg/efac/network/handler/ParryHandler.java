package net.seinsturg.efac.network.handler;

import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.ParryPayload;
import net.seinsturg.efac.sound.EvansSounds;

public class ParryHandler {
    public static void handle(final ParryPayload payload, final IPayloadContext context) {
        context.player().setData(EvansData.PARRY_TIME, payload.duration());
        context.player().level().playSound(null, context.player().getOnPos(), EvansSounds.TRY_PARRY.get(), SoundSource.PLAYERS, 1f, 1f);
    }
}
