package net.seinsturg.efac.network.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.network.payload.CubingCharmPayload;

public class CubingCharmHandler {
    public static void handle(final CubingCharmPayload payload, final IPayloadContext context) {
        //todo: enchantments for count & toss mult
        BlockState block = EvansBlocks.GRONE.get().defaultBlockState(); //todo: replace with selected block
        BlockPos blockPos = new BlockPos((int)payload.pos().x, (int)payload.pos().y, (int)payload.pos().z);

        for (int i = 0; i < payload.count(); i++) {
            summonBlock(context.player().level(), blockPos, payload.lookDir(), payload.mult(), block);
        }

        context.player().level().playSound(null, context.player().getOnPos(), SoundEvents.WIND_CHARGE_THROW, SoundSource.PLAYERS, 1f, 1.2f);
    }

    private static void summonBlock(Level level, BlockPos pos, Vec3 angle, int mult, BlockState block) {
        FallingBlockEntity blockEntity = FallingBlockEntity.fall(level, pos, block);

        Vec3 velocity = (angle.normalize()
                .add(level.random.triangle(0, 0.2), level.random.triangle(0, 0.2), level.random.triangle(0, 0.2))
                .scale(mult));
        blockEntity.setDeltaMovement(velocity);
    }
}
