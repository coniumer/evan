package net.seinsturg.efac.entity.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.entity.EvansEntities;
import net.seinsturg.efac.item.custom.WandTiers;
import net.seinsturg.efac.sound.EvansSounds;

import java.util.Optional;

public class ClumbProjectileEntity extends AbstractWindCharge {
    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR;
    private float rotation;
    private int damage;

    public ClumbProjectileEntity(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public ClumbProjectileEntity(Player player, Level level, double x, double y, double z, WandTiers tier) {
        super(EvansEntities.CLUMB_PROJECTILE.get(), level, player, x, y, z);
        damage = tier.getDamage();
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void explode(Vec3 pos) {
        this.level().explode(this, (DamageSource) null, EXPLOSION_DAMAGE_CALCULATOR,
                pos.x(), pos.y(), pos.z(), 1.2F, false, Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.LARGE_SMOKE, ParticleTypes.SMOKE, Holder.direct(EvansSounds.CLUMB_PULSE.get()));
    }

    static {
        EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(false, true, Optional.empty(), Optional.empty());
    }
}
