package net.seinsturg.efac.effect;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.seinsturg.efac.util.ClumbHelper;

public class InstantClumbEffect extends InstantenousMobEffect {
    public InstantClumbEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player player) {
            ClumbHelper.addCharges(player, 2 * (amplifier + 1), ClumbHelper.getMaxCharge(player));
        }
        return true;
    }
}
