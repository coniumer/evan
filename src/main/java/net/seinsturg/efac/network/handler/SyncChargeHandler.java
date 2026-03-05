package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;

public class SyncChargeHandler {
    public static void handle(final SyncChargePayload payload, final IPayloadContext context) {
        context.player().setData(EvansData.CHARGES, payload.charges());
    }
}
