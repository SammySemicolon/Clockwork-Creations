package com.sammy.clockwork_creations.data;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import com.sammy.clockwork_creations.setup.CCBlocks;
import com.sammy.clockwork_creations.setup.CCItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class CCRecipeProvider extends RecipeProvider {
    public CCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    public String getName() {
        return "Clockwork Creations Recipe Provider";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shaped(CCItems.CLOCK_KEY.get())
                .define('X', Tags.Items.INGOTS_GOLD)
                .pattern("XXX").pattern("XXX").pattern(" X ")
                .unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
                .save(consumer, ClockworkCreationsMod.path("clock_key"));

        shaped(CCItems.MAINSPRING.get())
                .define('X', Tags.Items.INGOTS_IRON)
                .pattern("XXX").pattern("X X").pattern("XXX")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(consumer, ClockworkCreationsMod.path("mainspring"));

        shaped(CCItems.CLOCKWORK_MECHANISM.get())
                .define('X', Tags.Items.INGOTS_IRON)
                .define('Y', CCItems.CLOCK_KEY.get())
                .define('Z', CCItems.MAINSPRING.get())
                .pattern("YXZ").pattern("XXX")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(consumer, ClockworkCreationsMod.path("clockwork_mechanism"));

        shaped(CCItems.WOODEN_PENDULUM.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', ItemTags.PLANKS)
                .pattern("X").pattern("Y")
                .unlockedBy("has_wood", has(ItemTags.PLANKS))
                .save(consumer, ClockworkCreationsMod.path("wooden_pendulum"));

        shaped(CCItems.IRON_PENDULUM.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', Tags.Items.INGOTS_IRON)
                .pattern("X").pattern("Y")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(consumer, ClockworkCreationsMod.path("iron_pendulum"));

        shaped(CCItems.GOLDEN_PENDULUM.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', Tags.Items.INGOTS_GOLD)
                .pattern("X").pattern("Y")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_GOLD))
                .save(consumer, ClockworkCreationsMod.path("golden_pendulum"));

        makeCuckooClockRecipe(consumer, Items.OAK_PLANKS, CCBlocks.OAK_CUCKOO_CLOCK.get());
        makeCuckooClockRecipe(consumer, Items.SPRUCE_PLANKS, CCBlocks.SPRUCE_CUCKOO_CLOCK.get());
        makeCuckooClockRecipe(consumer, Items.DARK_OAK_PLANKS, CCBlocks.DARK_OAK_CUCKOO_CLOCK.get());

        makePendulumClockRecipe(consumer, Items.OAK_PLANKS, CCBlocks.OAK_PENDULUM_CLOCK.get());
        makePendulumClockRecipe(consumer, Items.SPRUCE_PLANKS, CCBlocks.SPRUCE_PENDULUM_CLOCK.get());
        makePendulumClockRecipe(consumer, Items.DARK_OAK_PLANKS, CCBlocks.DARK_OAK_PENDULUM_CLOCK.get());

        makeRegulatorClockRecipe(consumer, Items.OAK_PLANKS, CCBlocks.OAK_REGULATOR_CLOCK.get());
        makeRegulatorClockRecipe(consumer, Items.SPRUCE_PLANKS, CCBlocks.SPRUCE_REGULATOR_CLOCK.get());
        makeRegulatorClockRecipe(consumer, Items.DARK_OAK_PLANKS, CCBlocks.DARK_OAK_REGULATOR_CLOCK.get());
    }

    public static void makeCuckooClockRecipe(Consumer<FinishedRecipe> consumer, Item planks, ItemLike pResult) {
        shaped(pResult)
                .define('X', planks)
                .define('Y', CCItems.WOODEN_PENDULUM.get())
                .define('Z', CCItems.CLOCKWORK_MECHANISM.get())
                .pattern("XZX").pattern("XYX").pattern("X X")
                .unlockedBy("has_clockwork_mechanism", has(CCItems.CLOCKWORK_MECHANISM.get()))
                .save(consumer, ClockworkCreationsMod.path(pResult.asItem().getRegistryName().getPath()));
    }

    public static void makePendulumClockRecipe(Consumer<FinishedRecipe> consumer, Item planks, ItemLike pResult) {
        shaped(pResult)
                .define('X', planks)
                .define('Y', CCItems.IRON_PENDULUM.get())
                .define('Z', CCItems.CLOCKWORK_MECHANISM.get())
                .define('W', Tags.Items.GLASS_PANES)
                .pattern("XZX").pattern("XYX").pattern("XWX")
                .unlockedBy("has_clockwork_mechanism", has(CCItems.CLOCKWORK_MECHANISM.get()))
                .save(consumer, ClockworkCreationsMod.path(pResult.asItem().getRegistryName().getPath()));
    }

    public static void makeRegulatorClockRecipe(Consumer<FinishedRecipe> consumer, Item planks, ItemLike pResult) {
        shaped(pResult)
                .define('X', planks)
                .define('Y', CCItems.GOLDEN_PENDULUM.get())
                .define('Z', CCItems.CLOCKWORK_MECHANISM.get())
                .define('W', Tags.Items.GLASS_PANES)
                .pattern("XZX").pattern("XYX").pattern("XWX")
                .unlockedBy("has_clockwork_mechanism", has(CCItems.CLOCKWORK_MECHANISM.get()))
                .save(consumer, ClockworkCreationsMod.path(pResult.asItem().getRegistryName().getPath()));
    }
}
