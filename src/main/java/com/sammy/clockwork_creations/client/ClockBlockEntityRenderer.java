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
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.setup.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ClockBlockEntityRenderer implements BlockEntityRenderer<ClockBlockEntity> {

    public static Map<Block, ResourceLocation> overlayHashmap = new HashMap<>();

    public ClockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        if (overlayHashmap.isEmpty()) {
            List<BlockEntry<? extends AbstractClockBlock>> clocks = List.of(
                    CCBlocks.OAK_CUCKOO_CLOCK, CCBlocks.SPRUCE_CUCKOO_CLOCK, CCBlocks.DARK_OAK_CUCKOO_CLOCK,
                    CCBlocks.OAK_PENDULUM_CLOCK, CCBlocks.SPRUCE_PENDULUM_CLOCK, CCBlocks.DARK_OAK_PENDULUM_CLOCK,
                    CCBlocks.OAK_REGULATOR_CLOCK, CCBlocks.SPRUCE_REGULATOR_CLOCK, CCBlocks.DARK_OAK_REGULATOR_CLOCK);
            clocks.forEach((b) ->
                    overlayHashmap.put(b.get(), ClockworkCreationsMod.path("textures/block/"+b.get().getRegistryName().getPath()+".png"))
            );
        }
    }

    @Override
    public void render(ClockBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = blockEntityIn.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        renderMovingClockParts(blockEntityIn, overlayHashmap.get(blockEntityIn.getBlockState().getBlock()), direction, poseStack);
    }

    public abstract void renderMovingClockParts(ClockBlockEntity blockEntity, ResourceLocation texture, Direction direction, PoseStack poseStack);
}