package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncMaxChargePayload;

public class SyncMaxChargeHandler {
    public static void handle(final SyncMaxChargePayload payload, final IPayloadContext context) {
        context.player().setData(EvansData.MAX_CHARGES, payload.maxCharges());
    }
}
