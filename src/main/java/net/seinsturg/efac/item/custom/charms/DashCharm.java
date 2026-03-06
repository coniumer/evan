package net.seinsturg.efac.item.custom.charms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.network.payload.DashCharmPayload;

public class DashCharm extends CharmItem {
    public DashCharm(Properties properties) {
        super(CharmFunction.DASH_FUNCTION, properties);
    }

    @Override
    public void clientAction(Player player) {
        Vec3 playerLookVec = player.getLookAngle();
        Vec3 dashVec = new Vec3(
                playerLookVec.x * 0.7f,
                (player.getDeltaMovement().y * 0.3f) + playerLookVec.y * 0.7f,
                playerLookVec.z * 0.7f
        );
        player.addDeltaMovement(dashVec);
    }

    @Override
    public void serverAction(Player player) {
        PacketDistributor.sendToServer(new DashCharmPayload(1));
    }
}
