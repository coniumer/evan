package net.seinsturg.efac.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.util.ClumbHelper;

import java.util.List;

//todo implement refined variants
public class GeumbItem extends ClumbFoodItem {
    private final GeumbEnum geumbEnum;
    public static boolean canUse(int maxCharges, int minToUse, int maxToUse) {
        return maxCharges >= minToUse && maxCharges <= maxToUse;
    }

    public GeumbItem(int amt, GeumbEnum geumbEnum, Properties properties) {
        super(amt, properties);
        this.geumbEnum = geumbEnum;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player) {
            geumbCheck(level, (Player) livingEntity);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }

    private void geumbCheck(Level level, Player player) {
        if (canUse(ClumbHelper.getMaxCharge(player), geumbEnum.getMinToUse(), geumbEnum.getMaxToUse())) {
            ClumbHelper.addMaxCharges(player);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.efac.geumb"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
