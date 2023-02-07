package com.sammy.clockwork_creations.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CuckooClockBlock extends AbstractClockBlock{

    public static final VoxelShape SOUTH = Shapes.box(0.125, 0.0625, 0, 0.875, 0.9375, 0.25);
    public static final VoxelShape NORTH = Shapes.box(0.125, 0.0625, 0.75, 0.875, 0.9375, 1);
    public static final VoxelShape WEST = Shapes.box(0.75, 0.0625, 0.125, 1, 0.9375, 0.875);
    public static final VoxelShape EAST = Shapes.box(0, 0.0625, 0.125, 0.25, 0.9375, 0.875);

    public CuckooClockBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH -> {
                return SOUTH;
            }
            case NORTH -> {
                return NORTH;
            }
            case WEST -> {
                return WEST;
            }
            case EAST -> {
                return EAST;
            }
        }
        return super.getShape(state, level, pos, context);
    }
}
