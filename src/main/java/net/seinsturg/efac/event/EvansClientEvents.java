package net.seinsturg.efac.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.client.EvansKeyMappings;
import net.seinsturg.efac.item.custom.charms.CharmItem;
import net.seinsturg.efac.network.payload.RemoveChargePayload;
import net.seinsturg.efac.util.ClumbHelper;

public class EvansClientEvents {

    @EventBusSubscriber(modid = EFAC.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (EvansKeyMappings.CLUMB_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null) {return;}
                Player player = mc.player;
                if (ClumbHelper.canClumb(player)) {
                    performClumbAction(player);
                    PacketDistributor.sendToServer(new RemoveChargePayload(1));
                }
            }
        }
    }

    private static void performClumbAction(Player player) {
        if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof CharmItem charm) {
            switch (charm.getDirection()) {
                case SERVER -> charm.serverAction(player);
                case CLIENT -> charm.clientAction(player);
                case BIDIRECTIONAL -> {
                    charm.serverAction(player);
                    charm.clientAction(player);
                }
                default -> {}
            }
        } else {
            ClumbHelper.parry(4);
        }
    }

    @EventBusSubscriber(modid = EFAC.MOD_ID, value = Dist.CLIENT)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(EvansKeyMappings.CLUMB_KEY);
            event.register(EvansKeyMappings.CHARM_CYCLE);
            event.register(EvansKeyMappings.MOUTH);
        }
    }
}
