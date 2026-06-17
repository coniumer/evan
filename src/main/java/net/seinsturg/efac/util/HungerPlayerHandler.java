package net.seinsturg.efac.util;

import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.LogicalSide;

public class HungerPlayerHandler {

    public void tickStart(Player player) {
        HungerPlayer hPlayer = HungerPlayer.get(player);
        if (hPlayer != null) {
            hPlayer.tickStart();
        }
    }

    public void tickEnd(Player player, LogicalSide side) {
        HungerPlayer hPlayer = HungerPlayer.get(player);
        if (hPlayer != null) {
            hPlayer.tickEnd(side);
        }
    }
}
