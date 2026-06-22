package net.seinsturg.efac.item.custom.charms;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.network.payload.HealthCharmPayload;

public class HealthCharm extends CharmItem {
    public HealthCharm(Properties properties) {
        super(CharmFunction.HEALTH_FUNCTION, properties);
    }

    @Override
    public void serverAction(Player player) {
        //todo: enchantments for healing amount and for regen
        PacketDistributor.sendToServer(new HealthCharmPayload(4, true, 100, 1));
    }
}
