package net.seinsturg.efac.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.ClumbHelper;
import net.seinsturg.efac.util.EvansDamage;

//todo sounds, custom projectile entity
public class WandItem extends TieredItem {
    private final WandTiers wandTier;
    public WandItem(Tier tier, WandTiers wandTier, Properties properties) {
        super(tier, properties);
        this.wandTier = wandTier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        boolean flag = ClumbHelper.canClumb(player);

        InteractionResultHolder<ItemStack> holder = EventHooks.onArrowNock(itemStack, level, player, usedHand, flag);
        if (holder != null) { return holder; }

        if (!flag) {
            return InteractionResultHolder.fail(itemStack);
        } else {
            player.startUsingItem(usedHand);
            return InteractionResultHolder.consume(itemStack);
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {
        if (livingEntity instanceof Player player && !level.isClientSide) {
            if (ClumbHelper.canClumb(player)) {
                int i = this.getUseDuration(stack, player) - timeCharged;
                if (i < 0) return;
                float f = getPowerForTime(i);
                if (!((double)f < 0.1)) {
                    summonProjectile(player, level, f * wandTier.getVelMult());
                    ClumbHelper.removeCharges(player, 1, ClumbHelper.getMaxCharge(player));

                    player.getCooldowns().addCooldown(this, wandTier.getCooldown());
                    player.awardStat(Stats.ITEM_USED.get(this));

                    if (!player.isCreative()) {
                        EquipmentSlot equipmentslot = stack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND))
                                ? EquipmentSlot.OFFHAND
                                : EquipmentSlot.MAINHAND;
                        stack.hurtAndBreak(1, player, equipmentslot);
                    }

                    float pitch = (Math.abs(level.random.nextInt() % 10) > 5) ? 1f : 0.5f;
                    level.playSound(null, player.getOnPos(), EvansSounds.CLUMB_SUCCESS.get(), SoundSource.PLAYERS, 1f, pitch);
                }
            } else {
                player.hurt(wandFailDamage(player), this.getTier().getAttackDamageBonus());

                float pitch = (Math.abs(level.random.nextInt() % 10) > 5) ? 1f : 0.5f;
                level.playSound(null, player.getOnPos(), EvansSounds.CLUMB_FAIL.get(), SoundSource.PLAYERS, 1f, pitch);
            }
        }
    }

    private static DamageSource wandFailDamage(Player cause) {
        return new DamageSource(cause.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(EvansDamage.CLUMB_BYPASS), cause);
    }

    public static float getPowerForTime(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    private void summonProjectile(Player player, Level level, float velocity) {
        WindCharge windcharge = new WindCharge(player, level, player.position().x, player.getEyeY(), player.position().z);
        windcharge.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity, wandTier.getAccuracy());
        level.addFreshEntity(windcharge);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }
}
