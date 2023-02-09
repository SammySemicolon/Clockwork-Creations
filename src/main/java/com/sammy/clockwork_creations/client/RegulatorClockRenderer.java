package com.sammy.clockwork_creations.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class RegulatorClockRenderer extends ClockBlockEntityRenderer{
    public RegulatorClockRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void renderMovingClockParts(ClockBlockEntity blockEntity, ResourceLocation texture, Direction direction, PoseStack poseStack) {

    }
}
