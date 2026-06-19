package net.seinsturg.efac.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.seinsturg.efac.block.EvansBlocks;

public class BuddingGeumbBlock extends GeumbBlock {
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingGeumbBlock(Properties properties) {
        super(properties);
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = EvansBlocks.SMALL_GEUMB_BUD.get();
            } else if (blockstate.is(EvansBlocks.SMALL_GEUMB_BUD) && blockstate.getValue(GeumbClusterBlock.FACING) == direction) {
                block = EvansBlocks.MEDIUM_GEUMB_BUD.get();
            } else if (blockstate.is(EvansBlocks.MEDIUM_GEUMB_BUD) && blockstate.getValue(GeumbClusterBlock.FACING) == direction) {
                block = EvansBlocks.LARGE_GEUMB_BUD.get();
            } else if (blockstate.is(EvansBlocks.LARGE_GEUMB_BUD) && blockstate.getValue(GeumbClusterBlock.FACING) == direction) {
                block = EvansBlocks.GEUMB_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = (BlockState)((BlockState)block.defaultBlockState().setValue(GeumbClusterBlock.FACING, direction)).setValue(GeumbClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }

    }

    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
