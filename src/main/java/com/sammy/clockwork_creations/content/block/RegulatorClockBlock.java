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

public class RegulatorClockBlock extends AbstractClockBlock{

    public static final VoxelShape SOUTH = Shapes.box(0.125, 0, 0, 0.875, 1, 0.375);
    public static final VoxelShape NORTH = Shapes.box(0.125, 0, 0.625, 0.875, 1, 1);
    public static final VoxelShape WEST = Shapes.box(0.625, 0, 0.125, 1, 1, 0.875);
    public static final VoxelShape EAST = Shapes.box(0, 0, 0.125, 0.375, 1, 0.875);

    public RegulatorClockBlock(Properties properties) {
        super(properties);
        setBlockEntity(CCBlockEntities.REGULATOR_CLOCK);
    }

    @Override
    public SoundEvent getTickSound() {
        return CCSounds.REGULATOR_TICK.get();
    }

    @Override
    public SoundEvent getChimeSound() {
        return CCSounds.REGULATOR_CHIME.get();
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
