package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.SetAirUsesPayload;
import net.seinsturg.efac.util.AirUsesHelper;

public class SetAirUsesHandler {
    public static void handle(final SetAirUsesPayload payload, final IPayloadContext context) {
        AirUsesHelper.setAirUses(context.player(), payload.airUses());
        AirUsesHelper.syncAirUses(context.player(), payload.airUses());
    }
}
