package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.AddChargePayload;
import net.seinsturg.efac.network.payload.RemoveChargePayload;
import net.seinsturg.efac.util.ClumbHelper;

public class AddChargeHandler {
    public static void handle(final AddChargePayload payload, final IPayloadContext context) {
        ClumbHelper.addCharges(context.player(), payload.charges(), context.player().getData(EvansData.MAX_CHARGES));
    }
}
