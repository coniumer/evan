package net.seinsturg.efac.movement;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.util.mixin_interface.EvanMovementInterfaceEntity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//based on https://github.com/PaleMannie/Squake-Ported/blob/1.21-1.21.1/src/main/java/mett/palemannie/squakeport_1_21/SquakeClientPlayer.java
public class EvansMovementClientPlayer {
    private static final List<float[]> baseVelocities = new ArrayList<>();
    private static final Method setDidJumpThisTick = null;
    private static final Method setIsJumping = null;

    //config values made constant
    private static final Double ACCELERATE = 10D;
    private static final Double AIR_ACCELERATE = 14D;
    private static final Float MAX_AIR_ACCELERATE = 0.045F;
    private static final Double SURFACE_TENSION = 0.0D;
    private static final Double WATER_FRICTION = 0.0D;
    private static final Double TRIMP_MULT = 1.4D;

    public static boolean moveEntityWithHeading(Player player, EvanMovementInterfaceEntity squakeEntity, float sidemove, float upmove, float forwardmove)
    {
        if(!player.level().isClientSide)
            return false;

        if(player.hasEffect(MobEffects.LEVITATION))
            return false;

        if(squakeEntity.shouldReturnMovement_Squake())
            return false;

        boolean didQuakeMovement;
        double d0 = player.getX();
        double d1 = player.getY();
        double d2 = player.getZ();

        if((player.getAbilities().flying || player.isFallFlying()) && player.getVehicle() == null)
            return false;
        else
            didQuakeMovement = quake_moveEntityWithHeading(player, sidemove, upmove, forwardmove);

        if(didQuakeMovement && player instanceof ServerPlayer splayer) {
            splayer.checkMovementStatistics(player.getX() - d0, player.getY() - d1, player.getZ() - d2);
        }
        return didQuakeMovement;
    }

    public static void beforeOnLivingUpdate(Player player)
    {
        if(!player.level().isClientSide)
            return;

        if(setDidJumpThisTick != null)
        {
            try
            {
                setDidJumpThisTick.invoke(null, false);
            } catch(Exception ignored)
            {
            }
        }

        if(!baseVelocities.isEmpty())
        {
            baseVelocities.clear();
        }

        if(setIsJumping != null)
        {
            try
            {
                setIsJumping.invoke(null, isJumping(player));
            } catch(Exception ignored)
            {
            }
        }
    }

    public static boolean moveRelativeBase(Entity entity, EvanMovementInterfaceEntity squakeEntity, float sidemove, float upmove, float forwardmove, float friction)
    {
        if(entity instanceof Player player)
            return moveRelative(player, squakeEntity, sidemove, upmove, forwardmove, friction);

        return false;
    }

    public static boolean moveRelative(Player player, EvanMovementInterfaceEntity squakeEntity, float sidemove, float upmove, float forwardmove, float friction)
    {
        if(!player.level().isClientSide)
            return false;

        if(player.hasEffect(MobEffects.LEVITATION))
            return false;

        if(squakeEntity.shouldReturnMovement_Squake())
            return false;

        if((player.getAbilities().flying && player.getVehicle() == null) || player.isInWater() || player.isInLava() || player.onClimbable())
        {
            return false;
        }

        // this is probably wrong, but its what was there in 1.10.2
        float wishspeed = friction;
        wishspeed *= 2.15f;
        float[] wishdir = getMovementDirection(player, sidemove, forwardmove);
        float[] wishvel = new float[]{
                wishdir[0] * wishspeed,
                wishdir[1] * wishspeed
        };
        baseVelocities.add(wishvel);

        return true;
    }

    public static void afterJump(Player player)
    {
        if(!player.level().isClientSide)
            return;

        if(player.hasEffect(MobEffects.LEVITATION))
            return;

        // undo this dumb thing
        if(player.isSprinting())
        {
            float f = player.getYRot() * 0.017453292F;

            double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);

            motionX += Mth.sin(f) * 0.2F;
            motionZ -= Mth.cos(f) * 0.2F;

            EvansMotions.setMotionHoriz(player, motionX, motionZ);
        }

        quake_Jump(player);

        if(setDidJumpThisTick != null)
        {
            try
            {
                setDidJumpThisTick.invoke(null, true);
            } catch(Exception ignored)
            {
            }
        }
    }

    /* =================================================
     * START HELPERS
     * =================================================
     */

    private static double getSpeed(Player player)
    {
        double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    private static float getSurfaceFriction(Player player)
    {
        float f2 = 1.0F;

        if(player.onGround())
        {
            BlockPos groundPos = new BlockPos(Mth.floor(player.getX()), Mth.floor(player.getBoundingBox().minY) - 1, Mth.floor(player.getZ()));
            f2 = 1.0F - EvansMotions.getSlipperiness(player, groundPos);
        }

        return f2;
    }

    private static float getSlipperiness(Player player)
    {
        float f2 = 0.91F;
        if(player.onGround())
        {
            BlockPos groundPos = new BlockPos(Mth.floor(player.getX()), Mth.floor(player.getBoundingBox().minY) - 1, Mth.floor(player.getZ()));
            f2 = EvansMotions.getSlipperiness(player, groundPos) * 0.91F;
        }
        return f2;
    }

    private static float minecraft_getMoveSpeed(Player player)
    {
        float f2 = getSlipperiness(player);

        float f3 = 0.16277136F / (f2 * f2 * f2);

        return player.getSpeed() * f3;
    }

    private static float[] getMovementDirection(Player player, float sidemove, float forwardmove)
    {
        float f3 = sidemove * sidemove + forwardmove * forwardmove;
        float[] dir = {
                0.0F,
                0.0F
        };

        if(f3 >= 1.0E-4F)
        {
            f3 = Mth.sqrt(f3);

            if(f3 < 1.0F)
            {
                f3 = 1.0F;
            }

            f3 = 1.0F / f3;
            sidemove *= f3;
            forwardmove *= f3;
            float f4 = Mth.sin(player.getYRot() * (float) Math.PI / 180.0F);
            float f5 = Mth.cos(player.getYRot() * (float) Math.PI / 180.0F);
            dir[0] = (sidemove * f5 - forwardmove * f4);
            dir[1] = (forwardmove * f5 + sidemove * f4);
        }

        return dir;
    }

    private static float quake_getMoveSpeed(Player player)
    {
        float baseSpeed = player.getSpeed();
        return !player.isShiftKeyDown() ? baseSpeed * 2.15F : baseSpeed * 1.11F;
    }

    private static float quake_getMaxMoveSpeed(Player player)
    {
        float baseSpeed = player.getSpeed();
        return baseSpeed * 2.15F;
    }

    private static void spawnBunnyhopParticles(Player player, int numParticles)
    {
        // taken from sprint
        int j = Mth.floor(player.getX());
        int i = Mth.floor(player.getY() - 0.20000000298023224D);
        int k = Mth.floor(player.getZ());
        BlockState blockState = player.level().getBlockState(new BlockPos(j, i, k));

        var motion = player.getDeltaMovement();
        RandomSource random = player.getRandom();

        if(blockState.getRenderShape() != RenderShape.INVISIBLE)
        {
            for(int iParticle = 0; iParticle < numParticles; iParticle++)
            {
                player.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState), player.getX() + (random.nextFloat() - 0.5D) * player.getBbWidth(), player.getBoundingBox().minY + 0.1D, player.getZ() + (random.nextFloat() - 0.5D) * player.getBbWidth(), -motion.x * 4.0D, 1.5D, -motion.z * 4.0D);
            }
        }
    }

    private static boolean isJumping(Player player)
    {
        EvanMovementInterfaceEntity ePlayer = (EvanMovementInterfaceEntity) player;
        return ePlayer.getJumping();
    }

    /* =================================================
     * END HELPERS
     * =================================================
     */

    /* =================================================
     * START MINECRAFT PHYSICS
     * =================================================
     */

    private static void minecraft_ApplyGravity(Player player)
    {
        double motionY = EvansMotions.getMotionY(player);

        if(player.level().isClientSide && (player.level().getChunk(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).getPersistedStatus() != ChunkStatus.FULL))
        {
            if(player.getY() > 0.0D)
            {
                motionY = -0.1D;
            } else
            {
                motionY = 0.0D;
            }
        } else
        {
            // gravity
            var gravity = player.getAttribute(Attributes.GRAVITY);
            if(gravity != null) motionY -= gravity.getValue();
        }

        // air resistance
        motionY *= 0.9800000190734863D;

        EvansMotions.setMotionY(player, motionY);
    }

    private static void minecraft_ApplyFriction(Player player, float momentumRetention)
    {
        double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
        motionX *= momentumRetention;
        motionZ *= momentumRetention;
        EvansMotions.setMotionHoriz(player, motionX, motionZ);
    }

    private static void minecraft_WaterMove(Player player, float sidemove, float upmove, float forwardmove)
    {
        double d0 = player.getY();
        player.moveRelative(0.04F, new Vec3(sidemove, upmove, forwardmove));

        double motionX = EvansMotions.getMotionX(player), motionY = EvansMotions.getMotionY(player), motionZ = EvansMotions.getMotionZ(player);

        player.move(MoverType.SELF, player.getDeltaMovement());

        motionX *= 0.800000011920929D;
        motionY *= 0.800000011920929D;
        motionZ *= 0.800000011920929D;
        motionY -= 0.02D;

        player.setDeltaMovement(motionX, motionY, motionZ);

        if(player.horizontalCollision && EvansMotions.isOffsetPositionInLiquid(player, EvansMotions.getMotionX(player), EvansMotions.getMotionY(player) + 0.6000000238418579D - player.getY() + d0, EvansMotions.getMotionZ(player)))
        {
            EvansMotions.setMotionY(player, 0.30000001192092896D);
        }
    }

    /* =================================================
     * END MINECRAFT PHYSICS
     * =================================================
     */

    /* =================================================
     * START QUAKE PHYSICS
     * =================================================
     */

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public static boolean quake_moveEntityWithHeading(Player player, float sidemove, float upmove, float forwardmove)
    {
        // take care of ladder movement using default code
        if(player.onClimbable())
        {
            return false;
        }
        // take care of lava movement using default code
        else if((player.isInLava() && !player.getAbilities().flying))
        {
            return false;
        } else if(player.isInWater() && !player.getAbilities().flying)
        {
            quake_WaterMove(player, sidemove, upmove, forwardmove);
        } else
        {
            // get all relevant movement values
            float wishspeed = (sidemove != 0.0F || forwardmove != 0.0F) ? quake_getMoveSpeed(player) : 0.0F;
            float[] wishdir = getMovementDirection(player, sidemove, forwardmove);
            boolean isOnGroundForReal = player.onGround() && !isJumping(player);
            float momentumRetention = getSlipperiness(player);

            // ground movement
            if(isOnGroundForReal)
            {
                // apply friction before acceleration so we can accelerate back up to maxspeed afterwards
                //quake_Friction(); // buggy because material-based friction uses a totally different format
                minecraft_ApplyFriction(player, momentumRetention);

                double sv_accelerate = ACCELERATE;

                if(wishspeed != 0.0F)
                {
                    // alter based on the surface friction
                    sv_accelerate *= minecraft_getMoveSpeed(player) * 2.15F / wishspeed;

                    quake_Accelerate(player, wishspeed, wishdir[0], wishdir[1], sv_accelerate);
                }

                if(!baseVelocities.isEmpty())
                {
                    float speedMod = wishspeed / quake_getMaxMoveSpeed(player);

                    double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);

                    // add in base velocities
                    for(float[] baseVel : baseVelocities)
                    {
                        motionX += baseVel[0] * speedMod;
                        motionZ += baseVel[1] * speedMod;
                    }

                    EvansMotions.setMotionHoriz(player, motionX, motionZ);
                }
            }
            // air movement
            else
            {
                double sv_airaccelerate = AIR_ACCELERATE;
                quake_AirAccelerate(player, wishspeed, wishdir[0], wishdir[1], sv_airaccelerate);

                if(isJumping(player) && EvansMotions.getMotionY(player) < 0.0F)
                {
                    var aabb = player.getBoundingBox().move(player.getDeltaMovement());
                    boolean isFallingIntoWater = player.level().containsAnyLiquid(aabb);

                    if(isFallingIntoWater)
                        EvansMotions.setMotionY(player, EvansMotions.getMotionY(player) * SURFACE_TENSION);
                }
            }

            // apply velocity
            player.move(MoverType.SELF, player.getDeltaMovement());

            // HL2 code applies half gravity before acceleration and half after acceleration, but this seems to work fine
            minecraft_ApplyGravity(player);
        }

        return true;
    }

    private static void quake_Jump(Player player)
    {
        quake_ApplySoftCap(player, quake_getMaxMoveSpeed(player));

        boolean didTrimp = quake_DoTrimp(player);

        if(!didTrimp)
        {
            quake_ApplyHardCap(player, quake_getMaxMoveSpeed(player));
        }
    }

    private static boolean quake_DoTrimp(Player player)
    {
        if(player.isShiftKeyDown())
        {
            double curspeed = getSpeed(player);
            float movespeed = quake_getMaxMoveSpeed(player);
            if(curspeed > movespeed)
            {
                double speedbonus = curspeed / movespeed * 0.5F;
                if(speedbonus > 1.0F)
                    speedbonus = 1.0F;

                EvansMotions.setMotionY(player, EvansMotions.getMotionY(player) + speedbonus * curspeed * TRIMP_MULT);

                float mult = (float) (1.0f / TRIMP_MULT);
                double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
                motionX *= mult;
                motionZ *= mult;
                EvansMotions.setMotionHoriz(player, motionX, motionZ);

                spawnBunnyhopParticles(player, 30);

                return true;
            }
        }

        return false;
    }

    private static void quake_ApplyWaterFriction(Player player)
    {
        player.setDeltaMovement(player.getDeltaMovement().scale(EvansMovementClientPlayer.WATER_FRICTION));
    }

    @SuppressWarnings("unused")
    private static void quake_WaterAccelerate(Player player, float wishspeed, float speed, double wishX, double wishZ, double accel)
    {
        float addspeed = wishspeed - speed;
        if(addspeed > 0)
        {
            float accelspeed = (float) (accel * wishspeed * 0.05F);
            if(accelspeed > addspeed)
            {
                accelspeed = addspeed;
            }
            double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
            motionX += accelspeed * wishX;
            motionZ += accelspeed * wishZ;
            EvansMotions.setMotionHoriz(player, motionX, motionZ);
        }
    }

    private static void quake_WaterMove(Player player, float sidemove, float upmove, float forwardmove)
    {
        double posY = player.getY();

        // get all relevant movement values
        float wishspeed = (sidemove != 0.0F || forwardmove != 0.0F) ? quake_getMaxMoveSpeed(player) : 0.0F;
        float[] wishdir = getMovementDirection(player, sidemove, forwardmove);
        boolean isSharking = isJumping(player) && EvansMotions.isOffsetPositionInLiquid(player, 0.0D, 1.0D, 0.0D);
        double curspeed = getSpeed(player);

        if(!isSharking || curspeed < 0.078F)
        {
            minecraft_WaterMove(player, sidemove, upmove, forwardmove);
        } else
        {
            if(curspeed > 0.09)
                quake_ApplyWaterFriction(player);

            if(curspeed > 0.098)
                quake_AirAccelerate(player, wishspeed, wishdir[0], wishdir[1], ACCELERATE);
            else
                quake_Accelerate(player, .0980F, wishdir[0], wishdir[1], ACCELERATE);

            player.move(MoverType.SELF, player.getDeltaMovement());

            EvansMotions.setMotionY(player, 0);
        }

        // water jump
        if(player.horizontalCollision && EvansMotions.isOffsetPositionInLiquid(player, EvansMotions.getMotionX(player), EvansMotions.getMotionY(player) + 0.6000000238418579D - player.getY() + posY, EvansMotions.getMotionZ(player)))
        {
            EvansMotions.setMotionY(player, 0.30000001192092896D);
        }

        if(!baseVelocities.isEmpty())
        {
            float speedMod = wishspeed / quake_getMaxMoveSpeed(player);
            // add in base velocities

            double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
            for(float[] baseVel : baseVelocities)
            {
                motionX += baseVel[0] * speedMod;
                motionZ += baseVel[1] * speedMod;
            }
            EvansMotions.setMotionHoriz(player, motionX, motionZ);
        }
    }

    private static void quake_Accelerate(Player player, float wishspeed, double wishX, double wishZ, double accel)
    {
        double addspeed, accelspeed, currentspeed;

        // Determine veer amount
        // this is a dot product
        currentspeed = EvansMotions.getMotionX(player) * wishX + EvansMotions.getMotionZ(player) * wishZ;

        // See how much to add
        addspeed = wishspeed - currentspeed;

        // If not adding any, done.
        if(addspeed <= 0)
            return;

        // Determine acceleration speed after acceleration
        accelspeed = accel * wishspeed / getSlipperiness(player) * 0.05F;

        // Cap it
        if(accelspeed > addspeed)
            accelspeed = addspeed;

        // Adjust pmove vel.
        double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
        motionX += accelspeed * wishX;
        motionZ += accelspeed * wishZ;
        EvansMotions.setMotionHoriz(player, motionX, motionZ);
    }

    private static void quake_AirAccelerate(Player player, float wishspeed, double wishX, double wishZ, double accel)
    {
        double addspeed, accelspeed, currentspeed;

        float wishspd = wishspeed;
        float maxAirAcceleration = MAX_AIR_ACCELERATE;

        if(wishspd > maxAirAcceleration)
            wishspd = maxAirAcceleration;

        // Determine veer amount
        // this is a dot product
        currentspeed = EvansMotions.getMotionX(player) * wishX + EvansMotions.getMotionZ(player) * wishZ;

        // See how much to add
        addspeed = wishspd - currentspeed;

        // If not adding any, done.
        if(addspeed <= 0)
            return;

        // Determine acceleration speed after acceleration
        accelspeed = accel * wishspeed * 0.05F;

        // Cap it
        if(accelspeed > addspeed)
            accelspeed = addspeed;

        // Adjust pmove vel.
        double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
        motionX += accelspeed * wishX;
        motionZ += accelspeed * wishZ;
        EvansMotions.setMotionHoriz(player, motionX, motionZ);
    }

    @SuppressWarnings("unused")
    private static void quake_Friction(Player player)
    {
        double speed, newspeed, control;
        float friction;
        float drop;

        // Calculate speed
        speed = getSpeed(player);

        // If too slow, return
        if(speed <= 0.0F)
        {
            return;
        }

        drop = 0.0F;

        // convars
        float sv_friction = 1.0F;
        float sv_stopspeed = 0.005F;

        float surfaceFriction = getSurfaceFriction(player);
        friction = sv_friction * surfaceFriction;

        // Bleed off some speed, but if we have less than the bleed
        //  threshold, bleed the threshold amount.
        control = (speed < sv_stopspeed) ? sv_stopspeed : speed;

        // Add the amount to the drop amount.
        drop += (float) (control * friction * 0.05F);

        // scale the velocity
        newspeed = speed - drop;
        if(newspeed < 0.0F)
            newspeed = 0.0F;
        double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
        if(newspeed != speed)
        {
            // Determine proportion of old speed we are using.
            newspeed /= speed;
            // Adjust velocity according to proportion.
            motionX *= newspeed;
            motionZ *= newspeed;
        }
        EvansMotions.setMotionHoriz(player, motionX, motionZ);
    }

    private static void quake_ApplySoftCap(Player player, float movespeed)
    {
        float softCapPercent = 1.0F;
        float softCapDegen = 1.0F;

        float speed = (float) (getSpeed(player));
        float softCap = movespeed * softCapPercent;

        // apply soft cap first; if soft -> hard is not done, then you can continually trigger only the hard cap and stay at the hard cap
        if(speed > softCap)
        {
            float applied_cap = (speed - softCap) * softCapDegen + softCap;
            float multi = applied_cap / speed;
            double motionX = EvansMotions.getMotionX(player), motionZ = EvansMotions.getMotionZ(player);
            motionX *= multi;
            motionZ *= multi;
            EvansMotions.setMotionHoriz(player, motionX, motionZ);

            spawnBunnyhopParticles(player, 10);
        }
    }

    private static void quake_ApplyHardCap(Player player, float movespeed)
    {
    }

    @SuppressWarnings("unused")
    private static void quake_OnLivingUpdate()
    {
    }

    /* =================================================
     * END QUAKE PHYSICS
     * =================================================
     */
}