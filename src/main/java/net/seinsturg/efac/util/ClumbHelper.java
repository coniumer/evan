package net.seinsturg.efac.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.payload.SyncAttachmentsPayload;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;

public class ClumbHelper {
    public static int getCharge(Player player) { return player.getData(EvansData.CHARGES); }
    public static int getMaxCharge(Player player) { return player.getData(EvansData.MAX_CHARGES); }
    public static boolean canClumb(Player player) { return getCharge(player) > 0; }

    public static void addCharges(Player player, int amt, int max) {
        int charges = getCharge(player);
        charges = Math.clamp(charges + amt, 0, max);
        player.setData(EvansData.CHARGES, charges);
        PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncChargePayload(getCharge(player)));
        System.out.println("Charges: " + getCharge(player));
    }

    public static void removeCharges(Player player, int amt, int max) {
        //todo philosophers charm bypass
        if (player.isCreative()) { return; }
        int charges = getCharge(player);
        charges = Math.clamp(charges - amt, 0, max);
        player.setData(EvansData.CHARGES, charges);
        System.out.println("Charges: " + getCharge(player));
    }

    public static void addMaxCharges(Player player) {
        int maxCharges = player.getData(EvansData.MAX_CHARGES);
        maxCharges = Math.clamp(maxCharges + 1, 5, 40);
        player.setData(EvansData.MAX_CHARGES, maxCharges);
        System.out.println("Max charges: " + getMaxCharge(player));
    }
}
