package com.sammy.clockwork_creations.setup;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

public class CCItems {
    public static final Registrate ITEM_REGISTRATE = ClockworkCreationsMod.registrate();

    public static final ItemEntry<Item> CLOCK_KEY = setupItem("clock_key", Item::new).register();
    public static final ItemEntry<Item> CLOCK_SPRING = setupItem("clock_spring", Item::new).register();
    public static final ItemEntry<Item> CLOCKWORK_MECHANISM = setupItem("clockwork_mechanism", Item::new).register();
    public static final ItemEntry<Item> PENDULUM_WOOD = setupItem("pendulum_wood", Item::new).register();
    public static final ItemEntry<Item> PENDULUM_IRON = setupItem("pendulum_iron", Item::new).register();
    public static final ItemEntry<Item> PENDULUM_GOLD = setupItem("pendulum_gold", Item::new).register();

    public static <T extends Item> ItemBuilder<T, Registrate> setupItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return ITEM_REGISTRATE.item(name, factory);
    }

    public static void register() {
    }
}