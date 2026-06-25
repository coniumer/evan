package net.seinsturg.efac.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seinsturg.efac.item.EvansItems;

public class ClumbrotCropBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)3.0F, (double)16.0F),
            Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)5.0F, (double)16.0F),
            Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)7.0F, (double)16.0F),
            Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)9.0F, (double)16.0F)};

    public ClumbrotCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return EvansItems.CLUMBROT;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
