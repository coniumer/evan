package net.seinsturg.efac.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Debug(export = true)
@Mixin(LivingEntity.class)
public class NoSprintLivingEntityMixin {
    @ModifyArg(method = "setSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;setSprinting(Z)V"))
    private boolean disableSprint(boolean sprinting) {
        return false;
    }
}
