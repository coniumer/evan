package net.seinsturg.efac.item.custom;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BloodCanisterItem extends Item {
    public BloodCanisterItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player player) {
            AttributeInstance maxHealth = player.getAttributes().getInstance(Attributes.MAX_HEALTH);
            maxHealth.setBaseValue(Mth.clamp(maxHealth.getValue() + 2, 0, 40));
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
