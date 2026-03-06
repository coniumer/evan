package net.seinsturg.efac.network.handler;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.LightningCharmPayload;

public class LightningCharmHandler {
    public static void handle(final LightningCharmPayload payload, final IPayloadContext context) {
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(context.player().level());
        assert lightningBolt != null;
        lightningBolt.moveTo(payload.pos().x, payload.pos().y, payload.pos().z);
        context.player().level().addFreshEntity(lightningBolt);
    }
}
