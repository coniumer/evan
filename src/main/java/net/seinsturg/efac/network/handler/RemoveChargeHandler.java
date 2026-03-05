package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.RemoveChargePayload;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.util.ClumbHelper;

public class RemoveChargeHandler {
    public static void handle(final RemoveChargePayload payload, final IPayloadContext context) {
        ClumbHelper.removeCharges(context.player(), payload.charges(), context.player().getData(EvansData.MAX_CHARGES));
    }
}
