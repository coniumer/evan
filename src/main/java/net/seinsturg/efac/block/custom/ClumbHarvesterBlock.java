package net.seinsturg.efac.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.seinsturg.efac.block.entity.ClumbHarvesterBlockEntity;
import net.seinsturg.efac.block.entity.EvansBlockEntities;
import net.seinsturg.efac.sound.EvansSounds;
import org.jetbrains.annotations.Nullable;

public class ClumbHarvesterBlock extends BaseEntityBlock {
    public static final MapCodec<ClumbHarvesterBlock> CODEC = simpleCodec(ClumbHarvesterBlock::new);

    public ClumbHarvesterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ClumbHarvesterBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof ClumbHarvesterBlockEntity harvesterBlockEntity) {
                harvesterBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof ClumbHarvesterBlockEntity harvesterBlockEntity) {
            if (!level.isClientSide()) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(harvesterBlockEntity, harvesterBlockEntity.getDisplayName()), pos);
            }
            level.playSound(player, pos, EvansSounds.HARVESTER_OPEN.get(), SoundSource.BLOCKS, 1f, 1f);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return null;
        } else {
            return createTickerHelper(blockEntityType, EvansBlockEntities.CLUMB_HARVESTER_BE.get(),
                    (useLevel, blockPos, blockState, blockEntity) -> blockEntity.tick(useLevel, blockPos, blockState));
        }
    }
}
