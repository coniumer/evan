package net.seinsturg.efac.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncMaxChargePayload;

@EventBusSubscriber(modid = EFAC.MOD_ID)
public class EvansEvents {
    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
    @SubscribeEvent
    public static void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
}
