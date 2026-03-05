package net.seinsturg.efac.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.util.ClumbHelper;

public class ClumbRechargeItem extends Item {
    public ClumbRechargeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player) {
            ClumbHelper.addCharges((Player) livingEntity, 1, ClumbHelper.getMaxCharge((Player) livingEntity));
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
