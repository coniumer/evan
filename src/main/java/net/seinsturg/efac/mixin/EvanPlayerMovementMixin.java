package net.seinsturg.efac.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.movement.EvansMovementClientPlayer;
import net.seinsturg.efac.util.mixin_interface.EvanMovementInterfaceEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class EvanPlayerMovementMixin extends LivingEntity implements EvanMovementInterfaceEntity {
    @Unique
    private boolean wasVelocityChangedBeforeFall = false;

    protected EvanPlayerMovementMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    public void moveEntityWithHeading(Vec3 vec, CallbackInfo ci) {
        var ePlayer = (Player) (LivingEntity) this;
        if (EvansMovementClientPlayer.moveEntityWithHeading(ePlayer, this, (float) vec.x, (float) vec.y, (float) vec.z)) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void beforeOnLivingUpdate(CallbackInfo ci) {
        var ePlayer = (Player) (LivingEntity) this;
        EvansMovementClientPlayer.beforeOnLivingUpdate(ePlayer);
    }

    @Inject(method = "jumpFromGround", at = @At("TAIL"))
    public void afterJump(CallbackInfo ci) {
        var ePlayer = (Player) (LivingEntity) this;
        EvansMovementClientPlayer.afterJump(ePlayer);
    }

    @Inject(method = "causeFallDamage", at = @At("HEAD"))
    public void beforeFall(float fallDistance, float multiplier, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (level().isClientSide) return;
        wasVelocityChangedBeforeFall = hasImpulse;
    }

    @Inject(method = "causeFallDamage", at = @At("RETURN"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/resources/ResourceLocation;I)V"), to = @At("TAIL")))
    public void afterFall(float fallDistance, float multiplier, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (level().isClientSide) return;
        hasImpulse = wasVelocityChangedBeforeFall;
    }

    @Override
    public boolean getJumping() {
        return this.jumping;
    }

    @Override
    public int getDisabledMovementTicks_Squake() {
        return 0;
    }

    @Override
    public void setDisabledMovementTicks_Squake(int amt) {
    }


    //LivingEntity methods unused
}
