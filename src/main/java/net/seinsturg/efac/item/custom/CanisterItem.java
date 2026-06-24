package net.seinsturg.efac.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;

public class CanisterItem extends Item {
    private final CanisterEnum canisterEnum;
    public static boolean canUse(int maxHealth, int minToUse, int maxToUse) {
        return maxHealth >= minToUse && maxHealth <= maxToUse;
    }

    public CanisterItem(CanisterEnum canisterEnum, Properties properties) {
        super(properties);
        this.canisterEnum = canisterEnum;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player player) {
            canisterCheck(player);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }

    public void canisterCheck(Player player) {
        if (canUse((int)Objects.requireNonNull(player.getAttributes().getInstance(Attributes.MAX_HEALTH)).getValue(), canisterEnum.getMinToUse(), canisterEnum.getMaxToUse())) {
            AttributeInstance maxHealth = player.getAttributes().getInstance(Attributes.MAX_HEALTH);
            maxHealth.setBaseValue(Mth.clamp(maxHealth.getValue() + 2, 0, 160));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.efac.canister"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
