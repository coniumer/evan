package net.seinsturg.efac.item.custom;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.sound.EvansSounds;
import net.seinsturg.efac.util.ClumbHelper;

public class ClumbFoodItem extends Item {
    private final int amt;
    public ClumbFoodItem(int amt, Properties properties) {
        super(properties);
        this.amt = amt;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide && livingEntity instanceof Player player) {
            ClumbHelper.addCharges(player, amt, ClumbHelper.getMaxCharge(player));

            float pitch = (Math.abs(player.level().random.nextInt() % 10) > 5) ? 1f : 0.8f;
            player.level().playSound(null, player.getOnPos(), EvansSounds.CLUMB_CHARGE.get(), SoundSource.PLAYERS, 1f, pitch);

        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
