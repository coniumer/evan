package net.seinsturg.efac.network.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.network.payload.FinishedBlinkPayload;

public class FinishedBlinkHandler {
    public static void handle(final FinishedBlinkPayload payload, final IPayloadContext context) {
        context.player().addDeltaMovement(payload.lastMovement());
        System.out.println("movement = " + context.player().getDeltaMovement());
    }
}
