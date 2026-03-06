package net.seinsturg.efac.item.custom.charms;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.network.payload.LightningCharmPayload;

public class LightningCharm extends CharmItem {
    public LightningCharm(Properties properties) {
        super(CharmFunction.LIGHTNING_FUNCTION, properties);
    }

    @Override
    public void serverAction(Player player) {
        assert Minecraft.getInstance().hitResult != null;
        Vec3 pos = Minecraft.getInstance().hitResult.getLocation();
        PacketDistributor.sendToServer(new LightningCharmPayload(pos));
    }
}
