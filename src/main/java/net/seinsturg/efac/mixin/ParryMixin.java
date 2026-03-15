package net.seinsturg.efac.mixin;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.seinsturg.efac.data.EvansData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Player.class)
public abstract class ParryMixin extends LivingEntity {
    protected ParryMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void handleParry(CallbackInfo ci) {

        Player player = (Player) (LivingEntity) this;
        player.setData(EvansData.DAMAGE_FLAG, player.getData(EvansData.PARRY_TIME) > 0);

        player.setData(EvansData.PARRY_TIME, Mth.clamp(player.getData(EvansData.PARRY_TIME) - 1, 0, 100));
    }
}
