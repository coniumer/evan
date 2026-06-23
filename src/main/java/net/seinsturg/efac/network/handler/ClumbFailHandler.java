package net.seinsturg.efac.network.handler;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.ClumbFailPayload;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.EvansDamage;

public class ClumbFailHandler {
    public static void handle(final ClumbFailPayload payload, final IPayloadContext context) {
        if (shouldDamagePlayer(context)) {
            context.player().hurt(clumbDamage(context.player()), context.player().level().random.nextIntBetweenInclusive(payload.i(), 5));
        }

        float pitch = (Math.abs(context.player().level().random.nextInt() % 10) > 5) ? 1f : 0.5f;
        context.player().level().playSound(null, context.player().getOnPos(), EvansSounds.CLUMB_FAIL.get(), SoundSource.PLAYERS, 0.5f, pitch);
    }

    private static boolean shouldDamagePlayer(IPayloadContext context) {
        return (context.player().level().random.nextInt() % 10) > 6;
    }

    private static DamageSource clumbDamage(Player cause) {
        return new DamageSource(cause.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(EvansDamage.CLUMB), cause);
    }
}
