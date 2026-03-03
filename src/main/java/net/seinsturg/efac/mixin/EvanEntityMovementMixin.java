package net.seinsturg.efac.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.movement.EvansMovementClientPlayer;
import net.seinsturg.efac.util.mixin_interface.EvanMovementInterfaceEntity;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
@Implements({
        @Interface(iface = EvanMovementInterfaceEntity.class, prefix = "sqe$")
})
public abstract class EvanEntityMovementMixin implements EvanMovementInterfaceEntity {
    @Unique
    private int squakeDisableMovementTicks;



    public boolean sqe$getJumping() {
        return false;
    }

    public int sqe$getDisabledMovementTicks_Squake() {
        return squakeDisableMovementTicks;
    }

    public void sqe$setDisabledMovementTicks_Squake(int amt) {
        squakeDisableMovementTicks = amt;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void beforeOnLivingUpdate(CallbackInfo ci) {
        if(squakeDisableMovementTicks > 0)
            --squakeDisableMovementTicks;
    }

    @Inject(method = "moveRelative", at = @At("HEAD"), cancellable = true)
    public void moveRelativeBase(float amount, Vec3 relative, CallbackInfo ci)
    {
        if(EvansMovementClientPlayer.moveRelativeBase((Entity) (Object) this, this, (float) relative.x, (float) relative.y, (float) relative.z, amount))
            ci.cancel();
    }

    @Inject(method = "onInsideBubbleColumn", at = @At("HEAD"))
    public void onInsideBubbleColumn(boolean p_20322_, CallbackInfo ci)
    {
        squakeDisableMovementTicks = 2;
    }
}
