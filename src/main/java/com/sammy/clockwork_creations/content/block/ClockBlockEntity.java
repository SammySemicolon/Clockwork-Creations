package com.sammy.clockwork_creations.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntity;

public class ClockBlockEntity extends LodestoneBlockEntity {
    public ClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (level instanceof ServerLevel serverLevel) {
            int dayTime = (int) (serverLevel.getDayTime() % 24000);
            int time = serverLevel.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT) ? dayTime : (int) (level.getGameTime() % 24000);
            if (dayTime % 20 == 0) {
                serverLevel.playSound(null, worldPosition, getBlock().getTickSound(), SoundSource.BLOCKS, 1f, 1f);
            }
            if (time == 6000 || time == 6030 || time == 18000) {
                serverLevel.playSound(null, worldPosition, getBlock().getChimeSound(), SoundSource.BLOCKS, 0.75f, 1f);
            }
        }
    }

    public AbstractClockBlock getBlock() {
        return (AbstractClockBlock) getBlockState().getBlock();
    }
}
