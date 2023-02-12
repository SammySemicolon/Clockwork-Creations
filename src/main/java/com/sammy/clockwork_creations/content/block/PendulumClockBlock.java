package com.sammy.clockwork_creations.content.block;

import com.sammy.clockwork_creations.setup.CCBlockEntities;
import com.sammy.clockwork_creations.setup.CCSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PendulumClockBlock extends AbstractClockBlock{

    public static final VoxelShape SOUTH = Shapes.box(0.0625, 0, 0, 0.9375, 1, 0.3125);
    public static final VoxelShape NORTH = Shapes.box(0.0625, 0, 0.6875, 0.9375, 1, 1);
    public static final VoxelShape WEST = Shapes.box(0.6875, 0, 0.0625, 1, 1, 0.9375);
    public static final VoxelShape EAST = Shapes.box(0, 0, 0.0625, 0.3125, 1, 0.9375);

    public PendulumClockBlock(Properties properties) {
        super(properties);
        setBlockEntity(CCBlockEntities.PENDULUM_CLOCK);
    }

    @Override
    public SoundEvent getTickSound() {
        return CCSounds.GRANDFATHER_TICK.get();
    }

    @Override
    public SoundEvent getChimeSound() {
        return CCSounds.GRANDFATHER_CHIME.get();
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
