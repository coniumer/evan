package net.seinsturg.efac.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.entity.villager.EvansVillagers;
import net.seinsturg.efac.item.EvansItems;
import net.seinsturg.efac.network.payload.SetAirUsesPayload;
import net.seinsturg.efac.network.payload.SyncAirUsesPayload;
import net.seinsturg.efac.network.payload.SyncChargePayload;
import net.seinsturg.efac.network.payload.SyncMaxChargePayload;
import net.seinsturg.efac.potion.EvansPotions;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.*;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = EFAC.MOD_ID)
public class EvansEvents {

    @SubscribeEvent
    private static void playerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().onGround() && event.getEntity().level().isClientSide) {
            PacketDistributor.sendToServer(new SetAirUsesPayload(0));
        }
    }

    /// parry
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

    /// adds clumb charge when appropriate after breaking a block
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        int chance = getChance(event.getState());
        if (tryCharge(event.getPlayer().level(), chance)) {

            ClumbHelper.addCharges(event.getPlayer(), 1);

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

    /// sync clumb data
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
                    new SyncAirUsesPayload(event.getEntity().getData(EvansData.AIR_USES)),
                    new SyncMaxChargePayload(event.getEntity().getData(EvansData.MAX_CHARGES)));
        }
    }

    /// potion recipes
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, EvansItems.CLUMB_MATERIA.get(), EvansPotions.CLUMB_POTION);
    }

    /// custom trades
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == EvansVillagers.CLUMBIST.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //clumbist level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansItems.CLUMB_MATERIA.get(), 6),
                    12, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(EvansItems.RANDOM_SAUCE.get(), 7),
                    8, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.CLUMBROT.get(), 7),
                    new ItemStack(Items.EMERALD, 2),
                    12, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.GEUMB_SHARD.get(), 12),
                    new ItemStack(Items.EMERALD, 1),
                    8, 4, 0.05F));

            //clumbist level two
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(EvansItems.YUMMY_SAUCE.get(), 5),
                    8, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.ALBY_GEM.get(), 12),
                    new ItemStack(Items.EMERALD, 1),
                    6, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.CITRY_GEM.get(), 10),
                    new ItemStack(Items.EMERALD, 2),
                    6, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.RUBY_GEM.get(), 8),
                    new ItemStack(Items.EMERALD, 3),
                    6, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(EvansBlocks.CLUMB_BLOCK.get(), 2),
                    7, 8, 0.05F));

            //clumbist level three
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansItems.AWESOME_SAUCE.get(), 3),
                    8, 16, 0.05F));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(EvansItems.BUTTER_STICK.get(), 24),
                    new ItemStack(Items.EMERALD, 1),
                    8, 16, 0.05F));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 18),
                    new ItemStack(EvansItems.CITRY_PULSAR.get()),
                    4, 16, 0.05F));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(EvansItems.CITRY_WAND.get()),
                    4, 16, 0.05F));

            //clumbist level four
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansItems.EPIC_SAUCE.get(), 1),
                    8, 24, 0.05F));
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 32),
                    new ItemStack(EvansItems.RUBIED_PULSAR.get()),
                    3, 24, 0.05F));
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 28),
                    new ItemStack(EvansItems.RUBIED_WAND.get()),
                    3, 24, 0.05F));

            //clumbist level five
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.GEUMB_TILES.get(), 12),
                    12, 8, 0.05F));
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.CLUMBY_GEUMB_TILES.get(), 12),
                    12, 8, 0.05F));
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.ALBY_GEUMB_TILES.get(), 12),
                    12, 8, 0.05F));
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.CITRY_GEUMB_TILES.get(), 12),
                    12, 8, 0.05F));
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.RUBY_GEUMB_TILES.get(), 12),
                    12, 8, 0.05F));
        }

        if (event.getType() == EvansVillagers.SHAMAN.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //shaman level one
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(EvansBlocks.NILENE_BLOCK.get(), 16),
                    12, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Blocks.DIORITE, 20),
                    new ItemStack(Items.EMERALD, 1),
                    14, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Blocks.ANDESITE, 20),
                    new ItemStack(Items.EMERALD, 1),
                    14, 4, 0.05F));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Blocks.GRANITE, 20),
                    new ItemStack(Items.EMERALD, 1),
                    14, 4, 0.05F));

            //shaman level two
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(EvansItems.DASH_CHARM.get(), 1),
                    3, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.COPPER_INGOT, 12),
                    new ItemStack(Items.EMERALD, 3),
                    12, 8, 0.05F));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Blocks.TNT, 8),
                    new ItemStack(Items.EMERALD, 6),
                    12, 8, 0.05F));

            //shaman level three
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 22),
                    new ItemStack(EvansItems.FIREBALL_CHARM.get(), 1),
                    3, 16, 0.05F));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.GLISTERING_MELON_SLICE, 4),
                    new ItemStack(Items.EMERALD, 2),
                    12, 16, 0.05F));

            //shaman level four
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 24),
                    new ItemStack(EvansItems.HEALTH_CHARM.get(), 1),
                    3, 24, 0.05F));
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.BLAZE_POWDER, 7),
                    new ItemStack(Items.EMERALD, 3),
                    12, 24, 0.05F));

            //shaman level five
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 9),
                    new ItemStack(EvansBlocks.SLIPULON_BLOCK, 2),
                    3, 24, 0.05F));
        }
    }
}
