package net.seinsturg.efac.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.seinsturg.efac.util.HungerPlayerHandler;

public class EvansCommonEvents {
    public HungerPlayerHandler playerHandler;
    public EvansCommonEvents () {
        playerHandler = new HungerPlayerHandler();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    private void tick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        playerHandler.tickStart(player);
    }

    @SubscribeEvent
    private void tick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        playerHandler.tickEnd(player, player instanceof ServerPlayer ? LogicalSide.SERVER : LogicalSide.CLIENT);
    }
}
