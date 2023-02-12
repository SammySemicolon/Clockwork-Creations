package com.sammy.clockwork_creations;

import com.sammy.clockwork_creations.data.CCRecipeProvider;
import com.sammy.clockwork_creations.data.LangMerger;
import com.sammy.clockwork_creations.setup.*;
import com.tterrag.registrate.Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.sammy.clockwork_creations.ClockworkCreationsMod.MODID;

@Mod(MODID)
public class ClockworkCreationsMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "clockwork_creations";
	public static final Random RANDOM = new Random();
	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID) {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(CCItems.CLOCKWORK_MECHANISM.get());
		}
	};

	public static Registrate registrate() {
		return REGISTRATE.get();
	}

	public ClockworkCreationsMod() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		registrate().creativeModeTab(() -> ITEM_GROUP);

		CCBlockEntities.register();
		CCItems.register();
		CCBlocks.register();

		CCSounds.SOUNDS.register(modBus);

		modBus.addListener(DataOnly::gatherData);
	}

	public static ResourceLocation path(String path) {
		return new ResourceLocation(MODID, path);
	}


	public static class DataOnly {
		public static void gatherData(GatherDataEvent event) {
			DataGenerator generator = event.getGenerator();
			generator.addProvider(new LangMerger(generator));
			generator.addProvider(new CCRecipeProvider(generator));
		}
	}
}