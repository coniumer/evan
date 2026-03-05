package net.seinsturg.efac.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.client.EvansKeyMappings;
import net.seinsturg.efac.util.ClumbHelper;

public class EvansClientEvents {
    public static void clumbDash(Player player) {
        Vec3 playerLookVec = player.getLookAngle();
        Vec3 dashVec = new Vec3(
                playerLookVec.x * 0.7f,
                (player.getDeltaMovement().y * 0.3f) + playerLookVec.y * 0.7f,
                playerLookVec.z * 0.7f
        );
        player.addDeltaMovement(dashVec);

    }

    @EventBusSubscriber(modid = EFAC.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (EvansKeyMappings.CLUMB_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null) {return;}
                Player player = mc.player;
                if (ClumbHelper.canClumb(player)) {
                    clumbDash(player);
                }
            }
        }
    }

    @EventBusSubscriber(modid = EFAC.MOD_ID, value = Dist.CLIENT)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(EvansKeyMappings.CLUMB_KEY);
        }
    }
}
