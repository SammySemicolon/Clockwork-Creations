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
        float rotationTime = 24000;
        float time = Minecraft.getInstance().level.getTimeOfDay(Minecraft.getInstance().getDeltaFrameTime())*rotationTime;


        renderHand(texture, direction, poseStack, time*0.05f, 1.01f); //Hour Hand
        renderHand(texture, direction, poseStack, time*0.3f-150f, 1.02f); //Minute Hand
    }

    public static void renderHand(ResourceLocation texture, Direction direction, PoseStack poseStack, float rotation, float zLevel) {
        VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.TRANSPARENT_TEXTURE.applyAndCache(texture));
        float trueRotation = ((rotation % 300) / 300f) * 360f;


        float start = -0.5f;
        float end = 0.5f;

        Vector3f[] positions = new Vector3f[]{new Vector3f(start, start-0.25f, zLevel), new Vector3f(end, start-0.25f, zLevel), new Vector3f(end, end-0.25f, zLevel), new Vector3f(start, end-0.25f, zLevel)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(0, -0.0625f,-0.375f);

        poseStack.mulPose(Vector3f.ZN.rotationDegrees(trueRotation));

        VFXBuilders.createWorld()
                .setPosColorTexLightmapDefaultFormat()
                .setUV(0.5625f, 0.375f, 0.625f, 0.5f)
                .renderQuad(consumer, poseStack, positions, 0.0625f, 0.125f);
        poseStack.popPose();
    }
}
