package net.seinsturg.efac.item.custom.charms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.network.payload.DashCharmPayload;
import net.seinsturg.efac.network.payload.SetAirUsesPayload;
import net.seinsturg.efac.util.AirUsesHelper;

public class DashCharm extends CharmItem {
    public DashCharm(Properties properties) {
        super(CharmFunction.DASH_FUNCTION, properties);
    }

    @Override
    public void clientAction(Player player) {
        if (AirUsesHelper.canUse(player)) {
            Vec3 playerLookVec = player.getLookAngle();
            Vec3 dashVec = new Vec3(
                    playerLookVec.x * 0.7f,
                    (player.getDeltaMovement().y * 0.3f) + playerLookVec.y * 0.7f,
                    playerLookVec.z * 0.7f
            );
            player.addDeltaMovement(dashVec);
        }
    }

    @Override
    public void c2sPayloadAction(Player player) {
        if (AirUsesHelper.canUse(player)) {
            PacketDistributor.sendToServer(new DashCharmPayload(1));
            PacketDistributor.sendToServer(new SetAirUsesPayload(AirUsesHelper.getAirUses(player) + 1));
        }
    }
}
