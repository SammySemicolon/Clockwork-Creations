package com.sammy.clockwork_creations.setup;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.sammy.clockwork_creations.content.block.ClockBlockEntity;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class CCBlockEntities {

    public static final Registrate BLOCK_ENTITY_REGISTRATE = ClockworkCreationsMod.registrate();

    public static final BlockEntityEntry<ClockBlockEntity> CLOCK =
            BLOCK_ENTITY_REGISTRATE.blockEntity("clock", ClockBlockEntity::new)
                    .validBlocks(
                            CCBlocks.OAK_CUCKOO_CLOCK, CCBlocks.SPRUCE_CUCKOO_CLOCK, CCBlocks.DARK_OAK_CUCKOO_CLOCK,
                            CCBlocks.OAK_PENDULUM_CLOCK, CCBlocks.SPRUCE_PENDULUM_CLOCK, CCBlocks.DARK_OAK_PENDULUM_CLOCK,
                            CCBlocks.OAK_REGULATOR_CLOCK, CCBlocks.SPRUCE_REGULATOR_CLOCK, CCBlocks.DARK_OAK_REGULATOR_CLOCK).register();

    public static void register() {
    }
}