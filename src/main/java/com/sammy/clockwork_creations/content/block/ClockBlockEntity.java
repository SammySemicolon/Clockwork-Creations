package com.sammy.clockwork_creations.content.block;

import com.sammy.clockwork_creations.client.ClockSoundInstance;
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
        needsSync = true;
    }


    @Override
    public void init() {
        if (level.isClientSide) {
            ClockSoundInstance.playSound(this);
        }
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        if (level instanceof ServerLevel serverLevel) {
            float dayTime = (serverLevel.getDayTime() % 24000);
            float time = serverLevel.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT) ? dayTime : (int) (level.getGameTime() % 24000);
            if (time == 6000 || time == 6030 || time == 18000) {
                serverLevel.playSound(null, worldPosition, getBlock().getChimeSound(), SoundSource.BLOCKS, 2f, 1f);
            }
        }
    }

    public AbstractClockBlock getBlock() {
        return (AbstractClockBlock) getBlockState().getBlock();
    }
}
