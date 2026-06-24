package net.seinsturg.efac.item.custom.charms;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.network.payload.CubingCharmPayload;

public class CubingCharm extends CharmItem {
    public CubingCharm(Properties properties) {
        super(CharmFunction.CUBING_FUNCTION, properties);
    }

    @Override
    public void c2sPayloadAction(Player player) {
        double pX = player.getX();
        double pY = player.getEyeY();
        double pZ = player.getZ();

        Vec3 pos = new Vec3(pX, pY, pZ);
        Vec3 lookDir = player.getLookAngle();

        PacketDistributor.sendToServer(new CubingCharmPayload(pos, lookDir, 3, 2));
    }
}
