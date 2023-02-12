package com.sammy.clockwork_creations.client;

import com.sammy.clockwork_creations.content.block.AbstractClockBlock;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import team.lodestar.lodestone.systems.sound.LodestoneBlockEntitySoundInstance;

public class ClockSoundInstance extends LodestoneBlockEntitySoundInstance<ClockBlockEntity> {
    public ClockSoundInstance(ClockBlockEntity blockEntity, SoundEvent soundEvent) {
        super(blockEntity, soundEvent, 1, 1);
        this.x = blockEntity.getBlockPos().getX() + 0.5f;
        this.y = blockEntity.getBlockPos().getY() + 0.5f;
        this.z = blockEntity.getBlockPos().getZ() + 0.5f;
    }

    public static void playSound(ClockBlockEntity tileEntity) {
        final AbstractClockBlock block = tileEntity.getBlock();
        Minecraft.getInstance().getSoundManager().queueTickingSound(new ClockSoundInstance(tileEntity, block.getTickSound()));
    }
}