package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncAirUsesPayload;
import net.seinsturg.efac.network.payload.SyncChargePayload;

public class SyncAirUsesHandler {
    public static void handle(final SyncAirUsesPayload payload, final IPayloadContext context) {
        context.player().setData(EvansData.AIR_USES, payload.airUses());
    }
}
