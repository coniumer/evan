package net.seinsturg.efac.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.util.ClumbHelper;

public class GeumbItem extends Item {
    public GeumbItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player) {
            ClumbHelper.addMaxCharges((Player) livingEntity);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
