package net.seinsturg.efac.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.network.handler.AddChargeHandler;
import net.seinsturg.efac.network.handler.RemoveChargeHandler;
import net.seinsturg.efac.network.handler.SyncChargeHandler;
import net.seinsturg.efac.network.handler.SyncMaxChargeHandler;
import net.seinsturg.efac.network.payload.AddChargePayload;
import net.seinsturg.efac.network.payload.RemoveChargePayload;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncMaxChargePayload;

@EventBusSubscriber(modid = EFAC.MOD_ID)
public class EvansMessages {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1") // Update this version if the payload semantics change.
                .optional();

        registrar.playToClient(
                SyncChargePayload.TYPE,
                SyncChargePayload.STREAM_CODEC,
                SyncChargeHandler::handle);
        registrar.playToClient(
                SyncMaxChargePayload.TYPE,
                SyncMaxChargePayload.STREAM_CODEC,
                SyncMaxChargeHandler::handle);
        registrar.playToServer(
                AddChargePayload.TYPE,
                AddChargePayload.STREAM_CODEC,
                AddChargeHandler::handle);
        registrar.playToServer(
                RemoveChargePayload.TYPE,
                RemoveChargePayload.STREAM_CODEC,
                RemoveChargeHandler::handle);
    }
}
