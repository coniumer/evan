package net.seinsturg.efac.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.network.handler.*;
import net.seinsturg.efac.network.payload.*;

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
        registrar.playToServer(
                LightningCharmPayload.TYPE,
                LightningCharmPayload.STREAM_CODEC,
                LightningCharmHandler::handle);
        registrar.playToServer(
                DashCharmPayload.TYPE,
                DashCharmPayload.STREAM_CODEC,
                DashCharmHandler::handle);
        registrar.playToServer(
                ParryPayload.TYPE,
                ParryPayload.STREAM_CODEC,
                ParryHandler::handle);
    }
}
