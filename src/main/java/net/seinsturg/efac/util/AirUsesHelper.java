package net.seinsturg.efac.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncAirUsesPayload;

public class AirUsesHelper {
    public static int getAirUses(Player player) { return player.getData(EvansData.AIR_USES); }
    public static int getMaxAirUses() { return 3; }

    public static boolean canUse(Player player) {
        return getAirUses(player) <= getMaxAirUses();
    }

    public static void setAirUses(Player player, int amt) {
        int airUses = Math.clamp(amt, 0, getMaxAirUses());
        player.setData(EvansData.AIR_USES, airUses);
    }

    public static void syncAirUses(Player player, int amt) {
        PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncAirUsesPayload(amt));
    }
}
