package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncLastMovementPayload;

public class SyncLastMovementHandler {
    public static void handle(final SyncLastMovementPayload payload, final IPayloadContext context) {
        context.player().setData(EvansData.LAST_MOVEMENT, payload.lastMovement());
    }
}
