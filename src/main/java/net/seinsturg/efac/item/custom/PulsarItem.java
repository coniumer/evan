package net.seinsturg.efac.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.component.EvansComponents;
import net.seinsturg.efac.item.component.PulsarComponent;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.ClumbHelper;
import net.seinsturg.efac.util.EvansDamage;

import java.util.List;

//todo sounds and textures for alt modes
public class PulsarItem extends SwordItem {
    private PulsarTiers pulsarTier;
    public PulsarItem(Tier tier, PulsarTiers pulsarTier, Properties properties) {
        super(tier, properties);
        this.pulsarTier = pulsarTier;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        PulsarComponent pulsar = stack.get(EvansComponents.PULSAR_COMPONENT);
        assert pulsar != null;

        super.postHurtEnemy(stack, target, attacker);

        if (pulsar.mode() && !attacker.level().isClientSide && attacker instanceof Player player) {
            if (ClumbHelper.canClumb(player)) {
                target.hurt(pulseDamage(player), this.pulsarTier.getPulseDamage());
                ClumbHelper.removeCharges(player, 1, ClumbHelper.getMaxCharge(player));

                float pitch = (Math.abs(player.level().random.nextInt() % 10) > 5) ? 1f : 0.8f;
                player.level().playSound(null, player.getOnPos(), EvansSounds.CLUMB_PULSE.get(), SoundSource.PLAYERS, 1f, pitch);
            } else {
                player.hurt(pulseDamage(player), this.pulsarTier.getPulseDamage());

                float pitch = (Math.abs(player.level().random.nextInt() % 10) > 5) ? 1f : 0.5f;
                player.level().playSound(null, player.getOnPos(), EvansSounds.CLUMB_FAIL.get(), SoundSource.PLAYERS, 1f, pitch);
            }
        } else if (!pulsar.mode()  && !attacker.level().isClientSide && attacker instanceof Player player) {
            if (tryChargeAbsorption(attacker.level())) {
                ClumbHelper.addCharges(player, 1, ClumbHelper.getMaxCharge(player));

                float pitch = (Math.abs(player.level().random.nextInt() % 10) > 5) ? 1f : 0.8f;
                player.level().playSound(null, player.getOnPos(), EvansSounds.CLUMB_CHARGE.get(), SoundSource.PLAYERS, 1f, pitch);
            }
        }
    }

    private boolean tryChargeAbsorption(Level level) {
        return (Math.abs(level.random.nextInt()) % 10) < this.pulsarTier.getChargeAbsorbChance();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        PulsarComponent pulsar = itemStack.get(EvansComponents.PULSAR_COMPONENT);
        itemStack.set(EvansComponents.PULSAR_COMPONENT.get(), new PulsarComponent(!pulsar.mode()));

        float pitch = (Math.abs(level.random.nextInt() % 10) > 5) ? 1f : 0.8f;
        SoundEvent soundEvent = getSoundBasedOnMode(itemStack.get(EvansComponents.PULSAR_COMPONENT).mode());
        level.playSound(player, player.getOnPos(), soundEvent, SoundSource.PLAYERS, 1f, pitch);

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    private SoundEvent getSoundBasedOnMode(boolean mode) {
        return (mode) ? EvansSounds.CLUMB_PULSE.get() : EvansSounds.CLUMB_CHARGE.get();
    }

    private static DamageSource pulseDamage(Player cause) {
        return new DamageSource(cause.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(EvansDamage.CLUMB_BYPASS), cause);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        //display text about mode
        PulsarComponent pulsar = stack.get(EvansComponents.PULSAR_COMPONENT);
        assert pulsar != null;
        if (pulsar.mode()) {
            tooltipComponents.add(Component.translatable("tooltip.efac.pulsar_pulse.title"));
            tooltipComponents.add(Component.translatable("tooltip.efac.pulsar_pulse.desc"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.efac.pulsar_charge.title"));
            tooltipComponents.add(Component.translatable("tooltip.efac.pulsar_charge.desc"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
