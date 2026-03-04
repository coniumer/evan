package net.seinsturg.efac.block.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class ClumbBlock extends Block {
    private static final EnumProperty<ClumbPhase> CLUMB_PHASE = EnumProperty.create("clumb_phase", ClumbPhase.class);
    private static final Map<ClumbPhase, VoxelShape> SHAPES = ImmutableMap.of(
            ClumbPhase.SOLID,
            Shapes.block(),
            ClumbPhase.PASSABLE,
            Shapes.empty()
    );
    private final Map<BlockState, VoxelShape> shapes;

    public ClumbBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.getStateDefinition().any().setValue(CLUMB_PHASE, ClumbPhase.SOLID)
        );
        this.shapes = this.getShapeForEachState(ClumbBlock::getShapeForState);
    }

    private static VoxelShape getShapeForState(BlockState state) {
        return Shapes.or(SHAPES.get(state.getValue(CLUMB_PHASE)));
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        makePassableAndScheduleTick(level, state, hit.getBlockPos());
    }

    private void makePassableAndScheduleTick(Level level, BlockState state, BlockPos pos) {
        if (state.getValue(CLUMB_PHASE) == ClumbPhase.SOLID) {
            level.setBlock(pos, state.setValue(CLUMB_PHASE, ClumbPhase.PASSABLE), Block.UPDATE_ALL);
            level.scheduleTick(pos, this, 128);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(CLUMB_PHASE) == ClumbPhase.PASSABLE) {
            level.setBlock(pos, state.setValue(CLUMB_PHASE, ClumbPhase.SOLID), Block.UPDATE_ALL);
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(CLUMB_PHASE));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapes.get(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLUMB_PHASE);
    }
}
