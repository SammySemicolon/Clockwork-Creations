package com.sammy.clockwork_creations.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.sammy.clockwork_creations.content.block.AbstractClockBlock;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import com.sammy.clockwork_creations.setup.CCBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.setup.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClockBlockEntityRenderer implements BlockEntityRenderer<ClockBlockEntity> {

    public static Map<Block, Material> overlayHashmap = new HashMap<>();

    public ClockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        if (overlayHashmap.isEmpty()) {
            List<BlockEntry<? extends AbstractClockBlock>> clocks = List.of(
                    CCBlocks.OAK_CUCKOO_CLOCK, CCBlocks.SPRUCE_CUCKOO_CLOCK, CCBlocks.DARK_OAK_CUCKOO_CLOCK,
                    CCBlocks.OAK_PENDULUM_CLOCK, CCBlocks.SPRUCE_PENDULUM_CLOCK, CCBlocks.DARK_OAK_PENDULUM_CLOCK,
                    CCBlocks.OAK_REGULATOR_CLOCK, CCBlocks.SPRUCE_REGULATOR_CLOCK, CCBlocks.DARK_OAK_REGULATOR_CLOCK);
            clocks.forEach((b) ->
                    overlayHashmap.put(b.get(), new Material(TextureAtlas.LOCATION_BLOCKS, ClockworkCreationsMod.path("block/"+b.get().getRegistryName().getPath())))
            );
        }
    }

    @Override
    public void render(ClockBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = blockEntityIn.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        renderQuad(overlayHashmap.get(blockEntityIn.getBlockState().getBlock()), direction, poseStack);
    }

    public void renderQuad(Material material, Direction direction, PoseStack poseStack) {
        TextureAtlasSprite sprite = material.sprite();
        VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.ADDITIVE_BLOCK);

        Vector3f[] positions = new Vector3f[]{new Vector3f(0, 0, 2.01f), new Vector3f(2, 0, 2.01f), new Vector3f(2, 2, 2.01f), new Vector3f(0, 2, 2.01f)};

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.translate(-0.5f, -0.5f, -0.5f);
        VFXBuilders.createWorld()
                .setPosColorTexLightmapDefaultFormat()
                .setUV(sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1())
                .renderQuad(consumer, poseStack, positions, 0.5f);
        poseStack.popPose();
    }

    public float rotation(Direction direction) {
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            direction = direction.getOpposite();
        }
        return direction.toYRot();
    }
}