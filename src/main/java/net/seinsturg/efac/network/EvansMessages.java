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

        //clumb charges
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
                ClumbFailPayload.TYPE,
                ClumbFailPayload.STREAM_CODEC,
                ClumbFailHandler::handle);

        //charms
        registrar.playToServer(
                LightningCharmPayload.TYPE,
                LightningCharmPayload.STREAM_CODEC,
                LightningCharmHandler::handle);
        registrar.playToServer(
                FireballCharmPayload.TYPE,
                FireballCharmPayload.STREAM_CODEC,
                FireballCharmHandler::handle);
        registrar.playToServer(
                PrankCharmPayload.TYPE,
                PrankCharmPayload.STREAM_CODEC,
                PrankCharmHandler::handle);
        registrar.playToServer(
                CubingCharmPayload.TYPE,
                CubingCharmPayload.STREAM_CODEC,
                CubingCharmHandler::handle);
        registrar.playToServer(
                HealthCharmPayload.TYPE,
                HealthCharmPayload.STREAM_CODEC,
                HealthCharmHandler::handle);
        registrar.playToServer(
                DashCharmPayload.TYPE,
                DashCharmPayload.STREAM_CODEC,
                DashCharmHandler::handle);
        registrar.playToServer(
                BlinkCharmPayload.TYPE,
                BlinkCharmPayload.STREAM_CODEC,
                BlinkCharmHandler::handle);
        registrar.playToClient(
                FinishedBlinkPayload.TYPE,
                FinishedBlinkPayload.STREAM_CODEC,
                FinishedBlinkHandler::handle);
        registrar.playToServer(
                SyncLastMovementPayload.TYPE,
                SyncLastMovementPayload.STREAM_CODEC,
                SyncLastMovementHandler::handle);
        registrar.playToServer(
                ParryPayload.TYPE,
                ParryPayload.STREAM_CODEC,
                ParryHandler::handle);
    }
}
