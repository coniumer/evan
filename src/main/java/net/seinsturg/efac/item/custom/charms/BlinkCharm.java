package net.seinsturg.efac.item.custom.charms;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.BlinkCharmPayload;
import net.seinsturg.efac.network.payload.SyncLastMovementPayload;

public class BlinkCharm extends CharmItem {
    public BlinkCharm(Properties properties) {
        super(CharmFunction.BLINK_FUNCTION, properties);
    }

    @Override
    public void clientAction(Player player) {
        //todo: increment air use limiter when added
        for(int i = 0; i < 32; ++i) {
            player.level().addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + player.level().random.nextDouble() * (double)2.0F, player.getZ(), player.level().random.nextGaussian(), (double)0.0F, player.level().random.nextGaussian());
        }
    }

    @Override
    public void c2sPayloadAction(Player player) {
        Vec3 lastMovement = player.getDeltaMovement();
        player.setData(EvansData.LAST_MOVEMENT, lastMovement);
        System.out.println("LAST_MOVEMENT = " + player.getData(EvansData.LAST_MOVEMENT));
        PacketDistributor.sendToServer(new SyncLastMovementPayload(lastMovement));

        Vec3 lookDir = player.getLookAngle();
        PacketDistributor.sendToServer(new BlinkCharmPayload(lookDir));
    }
}
