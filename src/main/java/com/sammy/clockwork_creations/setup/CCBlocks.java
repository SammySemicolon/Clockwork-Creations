package com.sammy.clockwork_creations.setup;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.sammy.clockwork_creations.content.block.AbstractClockBlock;
import com.sammy.clockwork_creations.content.block.CuckooClockBlock;
import com.sammy.clockwork_creations.content.block.PendulumClockBlock;
import com.sammy.clockwork_creations.content.block.RegulatorClockBlock;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import team.lodestar.lodestone.systems.block.LodestoneBlockProperties;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;


public class CCBlocks {
    public static final Registrate BLOCK_REGISTRATE = ClockworkCreationsMod.registrate();

    public static final BlockEntry<CuckooClockBlock> OAK_CUCKOO_CLOCK = setupBlock("oak_cuckoo_clock", CuckooClockBlock::new, LodestoneBlockProperties.copy(Blocks.OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<CuckooClockBlock> SPRUCE_CUCKOO_CLOCK = setupBlock("spruce_cuckoo_clock", CuckooClockBlock::new, LodestoneBlockProperties.copy(Blocks.SPRUCE_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.SPRUCE))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<CuckooClockBlock> DARK_OAK_CUCKOO_CLOCK = setupBlock("dark_oak_cuckoo_clock", CuckooClockBlock::new, LodestoneBlockProperties.copy(Blocks.DARK_OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.DARK_OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<PendulumClockBlock> OAK_PENDULUM_CLOCK = setupBlock("oak_pendulum_clock", PendulumClockBlock::new, LodestoneBlockProperties.copy(Blocks.OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<PendulumClockBlock> SPRUCE_PENDULUM_CLOCK = setupBlock("spruce_pendulum_clock", PendulumClockBlock::new, LodestoneBlockProperties.copy(Blocks.SPRUCE_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.SPRUCE))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<PendulumClockBlock> DARK_OAK_PENDULUM_CLOCK = setupBlock("dark_oak_pendulum_clock", PendulumClockBlock::new, LodestoneBlockProperties.copy(Blocks.DARK_OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.DARK_OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<RegulatorClockBlock> OAK_REGULATOR_CLOCK = setupBlock("oak_regulator_clock", RegulatorClockBlock::new, LodestoneBlockProperties.copy(Blocks.OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<RegulatorClockBlock> SPRUCE_REGULATOR_CLOCK = setupBlock("spruce_regulator_clock", RegulatorClockBlock::new, LodestoneBlockProperties.copy(Blocks.SPRUCE_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.SPRUCE))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static final BlockEntry<RegulatorClockBlock> DARK_OAK_REGULATOR_CLOCK = setupBlock("dark_oak_regulator_clock", RegulatorClockBlock::new, LodestoneBlockProperties.copy(Blocks.DARK_OAK_PLANKS).setCutoutRenderType())
            .blockstate(clockBlockstate(WoodType.DARK_OAK))
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .item().model((c, p) -> p.generated(c)).build()
            .register();

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> clockBlockstate(WoodType woodType) {
        return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates(s -> {
            int rotation = ((int) s.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() - 180) % 360;
            String textureName = ctx.getEntry().getRegistryName().getPath();
            String modelName = textureName.replace(woodType.name() + "_", "");
            ModelFile model = p.models().withExistingParent(ctx.getName(), ClockworkCreationsMod.path(modelName)).texture("clock", ClockworkCreationsMod.path("block/" + textureName));
            return ConfiguredModel.builder().modelFile(model).rotationY(rotation).build();
        });
    }

    public static <T extends Block> BlockBuilder<T, Registrate> setupBlock(String name, NonNullFunction<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        return BLOCK_REGISTRATE.block(name, factory).properties((x) -> properties);
    }

    public static void register() {
    }
}