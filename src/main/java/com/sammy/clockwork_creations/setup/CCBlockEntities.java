package com.sammy.clockwork_creations.setup;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.sammy.clockwork_creations.client.ClockBlockEntityRenderer;
import com.sammy.clockwork_creations.client.CuckooClockRenderer;
import com.sammy.clockwork_creations.client.PendulumClockRenderer;
import com.sammy.clockwork_creations.client.RegulatorClockRenderer;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class CCBlockEntities {

    public static final Registrate BLOCK_ENTITY_REGISTRATE = ClockworkCreationsMod.registrate();

    public static final BlockEntityEntry<ClockBlockEntity> CUCKOO_CLOCK =
            BLOCK_ENTITY_REGISTRATE.blockEntity("cuckoo_clock", ClockBlockEntity::new)
                    .validBlocks(CCBlocks.OAK_CUCKOO_CLOCK, CCBlocks.SPRUCE_CUCKOO_CLOCK, CCBlocks.DARK_OAK_CUCKOO_CLOCK)
                    .renderer(() -> CuckooClockRenderer::new)
                    .register();

    public static final BlockEntityEntry<ClockBlockEntity> PENDULUM_CLOCK =
            BLOCK_ENTITY_REGISTRATE.blockEntity("pendulum_clock", ClockBlockEntity::new)
                    .validBlocks(CCBlocks.OAK_PENDULUM_CLOCK, CCBlocks.SPRUCE_PENDULUM_CLOCK, CCBlocks.DARK_OAK_PENDULUM_CLOCK)
                    .renderer(() -> PendulumClockRenderer::new)
                    .register();

    public static final BlockEntityEntry<ClockBlockEntity> REGULATOR_CLOCK =
            BLOCK_ENTITY_REGISTRATE.blockEntity("regulator_clock", ClockBlockEntity::new)
                    .validBlocks(CCBlocks.OAK_REGULATOR_CLOCK, CCBlocks.SPRUCE_REGULATOR_CLOCK, CCBlocks.DARK_OAK_REGULATOR_CLOCK)
                    .renderer(() -> RegulatorClockRenderer::new)
                    .register();

    public static void register() {
    }
}