package com.sammy.clockwork_creations.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.setup.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class RegulatorClockRenderer extends ClockBlockEntityRenderer {

    public RegulatorClockRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public void renderHand(MultiBufferSource bufferIn, ResourceLocation texture, Direction direction, PoseStack poseStack, float rotation, float zLevel, int light, boolean hourHand) {
        VertexConsumer consumer = bufferIn.getBuffer(LodestoneRenderTypeRegistry.TEXTURE.applyAndCache(texture));
        float trueRotation = ((rotation % 300) / 300f) * 360f;
        float start = -0.5f;
        float end = 0.5f;

        Vector3f[] positions = new Vector3f[]{new Vector3f(start, start - 0.375f, zLevel), new Vector3f(end, start - 0.375f, zLevel), new Vector3f(end, end - 0.375f, zLevel), new Vector3f(start, end - 0.375f, zLevel)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(0, 0.25f, -0.1875f);

        poseStack.mulPose(Vector3f.ZN.rotationDegrees(trueRotation));

        VFXBuilders.createWorld()
                .setPosColorTexLightmapDefaultFormat()
                .setUV(0.875f, 0.03125f, 0.90625f, 0.125f)
                .setLight(light)
                .renderQuad(consumer, poseStack, positions, 0.0625f, 0.1875f);

        poseStack.popPose();
    }
}