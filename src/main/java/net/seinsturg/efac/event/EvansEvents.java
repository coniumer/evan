package net.seinsturg.efac.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncMaxChargePayload;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.ClumbHelper;
import net.seinsturg.efac.util.EvansDamage;
import net.seinsturg.efac.util.EvansTags;

@EventBusSubscriber(modid = EFAC.MOD_ID)
public class EvansEvents {
    @SubscribeEvent
    private static void preventDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity().getData(EvansData.DAMAGE_FLAG)) {
            event.getEntity().level().playSound(null, event.getEntity().getOnPos(), EvansSounds.PARRY.get(), SoundSource.PLAYERS, 1f, 1f);
            if (event.getSource().getEntity() != null && event.getSource().getEntity() != event.getEntity()) {
                event.getSource().getEntity().hurt(clumbDamage((Player) event.getEntity()), event.getAmount());
            }
            event.setAmount(0f);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        int chance = getChance(event.getState());
        if (tryCharge(event.getPlayer().level(), chance)) {

            ClumbHelper.addCharges(event.getPlayer(), 1, event.getPlayer().getData(EvansData.MAX_CHARGES));

            float pitch = (Math.abs(event.getPlayer().level().random.nextInt() % 10) > 5) ? 1f : 0.8f;
            event.getPlayer().level().playSound(null, event.getPlayer().getOnPos(), EvansSounds.CLUMB_CHARGE.get(), SoundSource.PLAYERS, 1f, pitch);

        } else if (event.getState().is(EvansTags.Blocks.CONSUMES_CHARGE)) {

            removeChargeOrHurt(event.getPlayer());

            float pitch = (Math.abs(event.getPlayer().level().random.nextInt() % 10) > 5) ? 1f : 0.8f;
            event.getPlayer().level().playSound(null, event.getPlayer().getOnPos(), EvansSounds.CLUMB_FAIL.get(), SoundSource.PLAYERS, 1f, pitch);

        }
    }

    private static void removeChargeOrHurt(Player player) {
        if (ClumbHelper.canClumb(player)) {
            ClumbHelper.removeCharges(player, 1, player.getData(EvansData.MAX_CHARGES));
        } else if (player.getHealth() > 1f) {
            player.hurt(clumbDamage(player), 1);
        }
    }

    private static DamageSource clumbDamage(Player cause) {
        return new DamageSource(cause.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(EvansDamage.CLUMB_BYPASS), cause);
    }

    private static boolean tryCharge(Level level, int chance) {
        return (Math.abs(level.random.nextInt()) % 10) < chance;
    }

    private static int getChance(BlockState state) {
        int chance = 0;
        if (state.is(EvansTags.Blocks.PROVIDES_CHARGE_RARE)) {
            chance = 1;
        } else if (state.is(EvansTags.Blocks.PROVIDES_CHARGE_COMMON)) {
            chance = 5;
        } else if (state.is(EvansTags.Blocks.PROVIDES_CHARGE_ALWAYS)) {
            chance = 10;
        }
        return chance;
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
    @SubscribeEvent
    public static void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
                    new SyncChargePayload(event.getEntity().getData(EvansData.CHARGES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }
}
