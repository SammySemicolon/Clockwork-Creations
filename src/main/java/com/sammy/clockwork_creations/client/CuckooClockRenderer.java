package com.sammy.clockwork_creations.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.setup.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class CuckooClockRenderer extends ClockBlockEntityRenderer{
    public CuckooClockRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void renderMovingClockParts(ClockBlockEntity blockEntity, ResourceLocation texture, Direction direction, PoseStack poseStack) {
        VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.TRANSPARENT_TEXTURE.applyAndCache(texture));

        float start = -0.5f;
        float end = 0.5f;

        Vector3f[] positions = new Vector3f[]{new Vector3f(start, start-0.25f, 1.01f), new Vector3f(end, start-0.25f, 1.01f), new Vector3f(end, end-0.25f, 1.01f), new Vector3f(start, end-0.25f, 1.01f)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(0, -0.0625f,-0.375f);

        poseStack.mulPose(Vector3f.ZP.rotationDegrees(blockEntity.getLevel().getGameTime()+ Minecraft.getInstance().getDeltaFrameTime()));

        VFXBuilders.createWorld()
                .setPosColorTexLightmapDefaultFormat()
                .setUV(0.5625f, 0.375f, 0.625f, 0.5f)
                .renderQuad(consumer, poseStack, positions, 0.0625f, 0.125f);
        poseStack.popPose();
    }
}
