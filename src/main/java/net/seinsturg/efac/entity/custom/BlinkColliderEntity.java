package net.seinsturg.efac.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.PacketDistributor;
import net.seinsturg.efac.data.EvansData;
import net.seinsturg.efac.entity.EvansEntities;
import net.seinsturg.efac.network.payload.FinishedBlinkPayload;

import java.util.Objects;

public class BlinkColliderEntity extends Projectile {
    private int tickCount;
    private double accelerationPower;
    public BlinkColliderEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.accelerationPower = 0.1;
        this.tickCount = 0;
    }

    public BlinkColliderEntity(Player player, Level level, Vec3 movement) {
        super(EvansEntities.BLINK_COLLIDER_ENTITY.get(), level);
        this.setOwner(player);
        this.assignDirectionalMovement(movement, this.accelerationPower);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        Vec3 pos = result.getBlockPos().getCenter();
        Vec3 nPos = new Vec3(pos.x, pos.y + 0.5, pos.z);

        teleportLogic(nPos);

        this.discard();
    }

    @Override
    public void tick() {

        if (this.getOwner() == null) {
            this.discard();
        }

        super.tick();

        if (!this.level().isClientSide) {
            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
                this.hitTargetOrDeflectSelf(hitresult);
            }
            if (this.tickCount > 2) {
                Vec3 pos = this.getOnPos().getCenter();
                teleportLogic(pos);
                this.discard();
            }
        }

        this.checkInsideBlocks();
        Vec3 movement = this.getDeltaMovement();
        this.setPos(this.getX() + movement.x, this.getY() + movement.y, this.getZ() + movement.z);

        tickCount++;
    }

    private void teleportLogic(Vec3 pos) {
        Entity owner = Objects.requireNonNull(getOwner());
        BlockPos soundPos = new BlockPos((int)pos.x, (int)pos.y, (int)pos.z);

        owner.teleportTo(pos.x, pos.y, pos.z);
        owner.level().playSound(null, soundPos, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1f, 1f);

        Vec3 newMovement = owner.getData(EvansData.LAST_MOVEMENT);
        PacketDistributor.sendToPlayer((ServerPlayer) owner, new FinishedBlinkPayload(newMovement));
        System.out.println("newMovement = " + newMovement);
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return false;
    }

    private void assignDirectionalMovement(Vec3 movement, double accelerationPower) {
        this.setDeltaMovement(movement.normalize().scale(accelerationPower));
        this.hasImpulse = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }
}
