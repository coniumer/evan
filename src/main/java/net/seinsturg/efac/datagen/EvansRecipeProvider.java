package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EvansRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public EvansRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    //custom id for recipe for when multiple recipes for the same item should exist:
    //.save(recipeOutput, "efac:itemWithMultipleRecipes_from_second_source");
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> CLUMB_SMELTABLES = List.of(
                EvansBlocks.COMPACT_DIRT);
        List<ItemLike> SLIPULON_SMELTABLES = List.of(
                EvansBlocks.SLIPULON_ORE, EvansItems.RAW_SLIPULON);
        List<ItemLike> BURNT_CHICKEN_SMELTABLES = List.of(
                EvansItems.BURNT_CHICKEN);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMB_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CLUMB_MATERIA.get(), 9)
                .requires(EvansBlocks.CLUMB_BLOCK)
                .unlockedBy("has_clumb_block", has(EvansBlocks.CLUMB_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.ALBY_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.ALBY_GEM)
                .unlockedBy("has_alby_gem", has(EvansItems.ALBY_GEM)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.ALBY_GEM.get(), 9)
                .requires(EvansBlocks.ALBY_BLOCK)
                .unlockedBy("has_alby_block", has(EvansBlocks.ALBY_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CITRY_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.CITRY_GEM)
                .unlockedBy("has_citry_gem", has(EvansItems.CITRY_GEM)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CITRY_GEM.get(), 9)
                .requires(EvansBlocks.CITRY_BLOCK)
                .unlockedBy("has_citry_block", has(EvansBlocks.CITRY_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RUBY_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.RUBY_GEM)
                .unlockedBy("has_ruby_gem", has(EvansItems.RUBY_GEM)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.RUBY_GEM.get(), 9)
                .requires(EvansBlocks.RUBY_BLOCK)
                .unlockedBy("has_ruby_block", has(EvansBlocks.RUBY_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.PHILOSOPHERS_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.PHILOSOPHERS_STONE)
                .unlockedBy("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.PHILOSOPHERS_STONE.get(), 9)
                .requires(EvansBlocks.PHILOSOPHERS_BLOCK)
                .unlockedBy("has_philosophers_block", has(EvansBlocks.PHILOSOPHERS_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.SLIPULON_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.SLIPULON_INGOT)
                .unlockedBy("has_slipulon_ingot", has(EvansItems.SLIPULON_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.SLIPULON_INGOT.get(), 9)
                .requires(EvansBlocks.SLIPULON_BLOCK)
                .unlockedBy("has_slipulon_block", has(EvansBlocks.SLIPULON_BLOCK)).save(recipeOutput);
        smelting(recipeOutput, SLIPULON_SMELTABLES, RecipeCategory.MISC, EvansItems.SLIPULON_INGOT, 0.3f, 200, "slipulon_ingot");
        blasting(recipeOutput, SLIPULON_SMELTABLES, RecipeCategory.MISC, EvansItems.SLIPULON_INGOT, 0.3f, 100, "slipulon_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.COMPACT_DIRT.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', Blocks.DIRT)
                .unlockedBy("has_dirt", has(Blocks.DIRT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.DIRT, 4)
                .requires(EvansBlocks.COMPACT_DIRT)
                .unlockedBy("has_compact_dirt", has(EvansBlocks.COMPACT_DIRT)).save(recipeOutput);
        smelting(recipeOutput, CLUMB_SMELTABLES, RecipeCategory.MISC, EvansItems.CLUMB_MATERIA, 0.3f, 200, "clumb_materia");
        blasting(recipeOutput, CLUMB_SMELTABLES, RecipeCategory.MISC, EvansItems.CLUMB_MATERIA, 0.3f, 100, "clumb_materia");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GELWOOD_PLANKS.get())
                .pattern("OO")
                .pattern("OO")
                .define('O', EvansItems.GELWOOD_ORB)
                .unlockedBy("has_gelwood_orb", has(EvansItems.GELWOOD_ORB)).save(recipeOutput);
        stairBuilder(EvansBlocks.GELWOOD_STAIRS.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GELWOOD_SLAB.get(), EvansBlocks.GELWOOD_PLANKS.get());
        buttonBuilder(EvansBlocks.GELWOOD_BUTTON.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, EvansBlocks.GELWOOD_PRESSURE_PLATE.get(), EvansBlocks.GELWOOD_PLANKS.get());
        fenceBuilder(EvansBlocks.GELWOOD_FENCE.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(EvansBlocks.GELWOOD_FENCE_GATE.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);
        doorBuilder(EvansBlocks.GELWOOD_DOOR.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(EvansBlocks.GELWOOD_TRAPDOOR.get(), Ingredient.of(EvansBlocks.GELWOOD_PLANKS.get())).group("gelwood")
                .unlockedBy("has_gelwood_planks", has(EvansBlocks.GELWOOD_PLANKS.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GRONE_BRICKS.get(), 9)
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', EvansBlocks.GRONE)
                .unlockedBy("has_grone", has(EvansBlocks.GRONE)).save(recipeOutput);
        stairBuilder(EvansBlocks.GRONE_BRICK_STAIRS.get(), Ingredient.of(EvansBlocks.GRONE_BRICKS.get())).group("grone")
                .unlockedBy("has_grone_brics", has(EvansBlocks.GRONE_BRICKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GRONE_BRICK_SLAB.get(), EvansBlocks.GRONE_BRICKS.get());
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GRONE_BRICK_WALL, Ingredient.of(EvansBlocks.GRONE_BRICKS)).group("grone")
                .unlockedBy("has_grone_brics", has(EvansBlocks.GRONE_BRICKS.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RONE_BRICKS.get(), 9)
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', Blocks.RED_SAND)
                .unlockedBy("has_red_sand", has(Blocks.RED_SAND)).save(recipeOutput);
        stairBuilder(EvansBlocks.RONE_BRICK_STAIRS.get(), Ingredient.of(EvansBlocks.RONE_BRICKS.get())).group("rone")
                .unlockedBy("has_rone_brics", has(EvansBlocks.RONE_BRICKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RONE_BRICK_SLAB.get(), EvansBlocks.RONE_BRICKS.get());
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RONE_BRICK_WALL, Ingredient.of(EvansBlocks.RONE_BRICKS)).group("rone")
                .unlockedBy("has_rone_brics", has(EvansBlocks.RONE_BRICKS.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.BLONE_BRICKS.get(), 9)
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', Blocks.GRAVEL)
                .unlockedBy("has_gravel", has(Blocks.GRAVEL)).save(recipeOutput);
        stairBuilder(EvansBlocks.BLONE_BRICK_STAIRS.get(), Ingredient.of(EvansBlocks.BLONE_BRICKS.get())).group("blone")
                .unlockedBy("has_blone_brics", has(EvansBlocks.BLONE_BRICKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.BLONE_BRICK_SLAB.get(), EvansBlocks.BLONE_BRICKS.get());
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.BLONE_BRICK_WALL, Ingredient.of(EvansBlocks.BLONE_BRICKS)).group("blone")
                .unlockedBy("has_blone_brics", has(EvansBlocks.BLONE_BRICKS.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.PLONE_BRICKS.get(), 9)
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', Blocks.SAND)
                .unlockedBy("has_sand", has(Blocks.SAND)).save(recipeOutput);
        stairBuilder(EvansBlocks.PLONE_BRICK_STAIRS.get(), Ingredient.of(EvansBlocks.PLONE_BRICKS.get())).group("plone")
                .unlockedBy("has_plone_brics", has(EvansBlocks.PLONE_BRICKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.PLONE_BRICK_SLAB.get(), EvansBlocks.PLONE_BRICKS.get());
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.PLONE_BRICK_WALL, Ingredient.of(EvansBlocks.PLONE_BRICKS)).group("plone")
                .unlockedBy("has_plone_brics", has(EvansBlocks.PLONE_BRICKS.get())).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.ALBY_CLUMB_MATERIA.get())
                .requires(EvansItems.CLUMB_MATERIA)
                .requires(EvansItems.ALBY_GEM)
                .requires(EvansItems.BUTTER_STICK)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CITRY_CLUMB_MATERIA.get())
                .requires(EvansItems.CLUMB_MATERIA)
                .requires(EvansItems.CITRY_GEM)
                .requires(EvansItems.BUTTER_STICK)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.RUBIED_CLUMB_MATERIA.get())
                .requires(EvansItems.CLUMB_MATERIA)
                .requires(EvansItems.RUBY_GEM)
                .requires(EvansItems.BUTTER_STICK)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GEUMB_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', EvansItems.GEUMB_SHARD.get())
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.GEUMB_SHARD.get(), 4)
                .requires(EvansBlocks.GEUMB_BLOCK)
                .unlockedBy("has_geumb_block", has(EvansBlocks.GEUMB_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CLUMBY_GEUMB_SHARD.get())
                .requires(EvansItems.CLUMB_MATERIA)
                .requires(EvansItems.RANDOM_SAUCE)
                .requires(EvansItems.RANDOM_SAUCE)
                .requires(EvansItems.GEUMB_SHARD)
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.ALBY_GEUMB_SHARD.get())
                .requires(EvansItems.ALBY_CLUMB_MATERIA)
                .requires(EvansItems.YUMMY_SAUCE)
                .requires(EvansItems.YUMMY_SAUCE)
                .requires(EvansItems.GEUMB_SHARD)
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CITRY_GEUMB_SHARD.get())
                .requires(EvansItems.CITRY_CLUMB_MATERIA)
                .requires(EvansItems.AWESOME_SAUCE)
                .requires(EvansItems.AWESOME_SAUCE)
                .requires(EvansItems.GEUMB_SHARD)
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.RUBIED_GEUMB_SHARD.get())
                .requires(EvansItems.RUBIED_CLUMB_MATERIA)
                .requires(EvansItems.EPIC_SAUCE)
                .requires(EvansItems.EPIC_SAUCE)
                .requires(EvansItems.GEUMB_SHARD)
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GEUMB_TILES.get(), 4)
                .requires(EvansItems.GEUMB_SHARD)
                .requires(EvansItems.GEUMB_SHARD)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.BUTTER_STICK)
                .unlockedBy("has_geumb_shard", has(EvansItems.GEUMB_SHARD)).save(recipeOutput);

        stairBuilder(EvansBlocks.GEUMB_TILE_STAIRS.get(), Ingredient.of(EvansBlocks.GEUMB_TILES.get())).group("geumb_tile")
                .unlockedBy("has_geumb_tiles", has(EvansBlocks.GEUMB_TILES.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GEUMB_TILE_SLAB.get(), EvansBlocks.GEUMB_TILES.get());

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.GEUMB_TILE_WALL, Ingredient.of(EvansBlocks.GEUMB_TILES)).group("geumb_tile")
                .unlockedBy("has_geumb_tiles", has(EvansBlocks.GEUMB_TILES.get())).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMBY_GEUMB_TILES.get(), 4)
                .requires(EvansItems.CLUMBY_GEUMB_SHARD)
                .requires(EvansItems.CLUMBY_GEUMB_SHARD)
                .requires(EvansItems.RANDOM_SAUCE)
                .requires(EvansItems.RANDOM_SAUCE)
                .unlockedBy("has_clumby_geumb_shard", has(EvansItems.CLUMBY_GEUMB_SHARD)).save(recipeOutput);

        stairBuilder(EvansBlocks.CLUMBY_GEUMB_TILE_STAIRS.get(), Ingredient.of(EvansBlocks.CLUMBY_GEUMB_TILES.get())).group("clumby_geumb_tile")
                .unlockedBy("has_clumby_geumb_tiles", has(EvansBlocks.CLUMBY_GEUMB_TILES.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMBY_GEUMB_TILE_SLAB.get(), EvansBlocks.CLUMBY_GEUMB_TILES.get());

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMBY_GEUMB_TILE_WALL, Ingredient.of(EvansBlocks.CLUMBY_GEUMB_TILES)).group("clumby_geumb_tile")
                .unlockedBy("has_clumby_geumb_tiles", has(EvansBlocks.CLUMBY_GEUMB_TILES.get())).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.ALBY_GEUMB_TILES.get(), 4)
                .requires(EvansItems.ALBY_GEUMB_SHARD)
                .requires(EvansItems.ALBY_GEUMB_SHARD)
                .requires(EvansItems.YUMMY_SAUCE)
                .requires(EvansItems.YUMMY_SAUCE)
                .unlockedBy("has_alby_geumb_shard", has(EvansItems.ALBY_GEUMB_SHARD)).save(recipeOutput);

        stairBuilder(EvansBlocks.ALBY_GEUMB_TILE_STAIRS.get(), Ingredient.of(EvansBlocks.ALBY_GEUMB_TILES.get())).group("alby_geumb_tile")
                .unlockedBy("has_alby_geumb_tiles", has(EvansBlocks.ALBY_GEUMB_TILES.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.ALBY_GEUMB_TILE_SLAB.get(), EvansBlocks.ALBY_GEUMB_TILES.get());

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.ALBY_GEUMB_TILE_WALL, Ingredient.of(EvansBlocks.ALBY_GEUMB_TILES)).group("alby_geumb_tile")
                .unlockedBy("has_alby_geumb_tiles", has(EvansBlocks.ALBY_GEUMB_TILES.get())).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CITRY_GEUMB_TILES.get(), 4)
                .requires(EvansItems.CITRY_GEUMB_SHARD)
                .requires(EvansItems.CITRY_GEUMB_SHARD)
                .requires(EvansItems.AWESOME_SAUCE)
                .requires(EvansItems.AWESOME_SAUCE)
                .unlockedBy("has_citry_geumb_shard", has(EvansItems.CITRY_GEUMB_SHARD)).save(recipeOutput);

        stairBuilder(EvansBlocks.CITRY_GEUMB_TILE_STAIRS.get(), Ingredient.of(EvansBlocks.CITRY_GEUMB_TILES.get())).group("citry_geumb_tile")
                .unlockedBy("has_citry_geumb_tiles", has(EvansBlocks.CITRY_GEUMB_TILES.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CITRY_GEUMB_TILE_SLAB.get(), EvansBlocks.CITRY_GEUMB_TILES.get());

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CITRY_GEUMB_TILE_WALL, Ingredient.of(EvansBlocks.CITRY_GEUMB_TILES)).group("citry_geumb_tile")
                .unlockedBy("has_citry_geumb_tiles", has(EvansBlocks.CITRY_GEUMB_TILES.get())).save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RUBY_GEUMB_TILES.get(), 4)
                .requires(EvansItems.RUBIED_GEUMB_SHARD)
                .requires(EvansItems.RUBIED_GEUMB_SHARD)
                .requires(EvansItems.EPIC_SAUCE)
                .requires(EvansItems.EPIC_SAUCE)
                .unlockedBy("has_rubied_geumb_shard", has(EvansItems.RUBIED_GEUMB_SHARD)).save(recipeOutput);

        stairBuilder(EvansBlocks.RUBY_GEUMB_TILE_STAIRS.get(), Ingredient.of(EvansBlocks.RUBY_GEUMB_TILES.get())).group("ruby_geumb_tile")
                .unlockedBy("has_ruby_geumb_tiles", has(EvansBlocks.RUBY_GEUMB_TILES.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RUBY_GEUMB_TILE_SLAB.get(), EvansBlocks.RUBY_GEUMB_TILES.get());

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.RUBY_GEUMB_TILE_WALL, Ingredient.of(EvansBlocks.RUBY_GEUMB_TILES)).group("ruby_geumb_tile")
                .unlockedBy("has_ruby_geumb_tiles", has(EvansBlocks.RUBY_GEUMB_TILES.get())).save(recipeOutput);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.CLUMBY_WAND.get())
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.CLUMB_MATERIA)
                .define('H', Items.STICK)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.ALBY_WAND.get())
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.ALBY_CLUMB_MATERIA)
                .define('H', Items.IRON_INGOT)
                .unlockedBy("has_alby_clumb_materia", has(EvansItems.ALBY_CLUMB_MATERIA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.CITRY_WAND.get())
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.CITRY_CLUMB_MATERIA)
                .define('H', Items.GOLD_INGOT)
                .unlockedBy("has_citry_clumb_materia", has(EvansItems.CITRY_CLUMB_MATERIA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.RUBIED_WAND.get())
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.RUBIED_CLUMB_MATERIA)
                .define('H', Items.DIAMOND)
                .unlockedBy("has_rubied_clumb_materia", has(EvansItems.RUBIED_CLUMB_MATERIA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.PHILOSOPHERS_WAND.get())
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.PHILOSOPHERS_STONE)
                .define('H', EvansItems.RUBIED_GEUMB_SHARD)
                .unlockedBy("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.CLUMBY_PULSAR.get())
                .pattern("G")
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.CLUMBY_GEUMB_SHARD)
                .define('H', Items.STICK)
                .unlockedBy("has_clumby_geumb_shard", has(EvansItems.CLUMBY_GEUMB_SHARD)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.ALBY_PULSAR.get())
                .pattern("G")
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.ALBY_GEUMB_SHARD)
                .define('H', Items.STICK)
                .unlockedBy("has_alby_geumb_shard", has(EvansItems.ALBY_GEUMB_SHARD)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.CITRY_PULSAR.get())
                .pattern("G")
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.CITRY_GEUMB_SHARD)
                .define('H', Items.STICK)
                .unlockedBy("has_citry_geumb_shard", has(EvansItems.CITRY_GEUMB_SHARD)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EvansItems.RUBIED_PULSAR.get())
                .pattern("G")
                .pattern("G")
                .pattern("H")
                .define('G', EvansItems.RUBIED_GEUMB_SHARD)
                .define('H', Items.STICK)
                .unlockedBy("has_rubied_geumb_shard", has(EvansItems.RUBIED_GEUMB_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.CLUMBELON)
                .requires(Items.MELON_SLICE)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.CLUMBKIE)
                .requires(Items.COOKIE)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.BLUMB)
                .requires(Items.BREAD)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.CLUMBLE)
                .requires(Items.APPLE)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.GOLD_CLUMBLE)
                .requires(Items.APPLE)
                .requires(Blocks.GOLD_BLOCK)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CITRY_CLUMB_MATERIA)
                .unlockedBy("has_citry_clumb_materia", has(EvansItems.CITRY_CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.CLUMBROT)
                .requires(Items.CARROT)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.GOLD_CLUMBROT)
                .requires(Items.CARROT)
                .requires(Blocks.GOLD_BLOCK)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CITRY_CLUMB_MATERIA)
                .unlockedBy("has_citry_clumb_materia", has(EvansItems.CITRY_CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EvansItems.CLUMBURGER)
                .requires(Items.COOKED_BEEF)
                .requires(EvansItems.BUTTER_STICK)
                .requires(EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(EvansItems.RUBIED_WAND),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.COMBAT,
                EvansItems.PHILOSOPHERS_WAND.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(EvansItems.RUBIED_WAND) + "_smithing");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(EvansItems.RUBIED_PULSAR),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.TOOLS,
                EvansItems.PHILOSOPHERS_PULSAR.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(EvansItems.RUBIED_PULSAR) + "_smithing");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Items.NETHERITE_SHOVEL),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.TOOLS,
                EvansItems.PHILOSOPHERS_SHOVEL.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(Items.NETHERITE_SHOVEL) + "_smithing");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Items.NETHERITE_PICKAXE),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.TOOLS,
                EvansItems.PHILOSOPHERS_PICKAXE.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(Items.NETHERITE_PICKAXE) + "_smithing");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Items.NETHERITE_AXE),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.TOOLS,
                EvansItems.PHILOSOPHERS_AXE.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(Items.NETHERITE_AXE) + "_smithing");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Items.NETHERITE_HOE),
                Ingredient.of(EvansItems.PHILOSOPHERS_STONE),
                RecipeCategory.TOOLS,
                EvansItems.PHILOSOPHERS_HOE.get())
                .unlocks("has_philosophers_stone", has(EvansItems.PHILOSOPHERS_STONE.get()))
                .save(recipeOutput, getItemName(Items.NETHERITE_HOE) + "_smithing");

        smelting(recipeOutput, BURNT_CHICKEN_SMELTABLES, RecipeCategory.MISC, Items.COOKED_CHICKEN, 0.3f, 200, "burnt_chicken");
        smoking(recipeOutput, BURNT_CHICKEN_SMELTABLES, RecipeCategory.MISC, Items.COOKED_CHICKEN, 0.3f, 100, "burnt_chicken");

    }

    protected void smelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                            float pExperience, int pCookingTIme, String pGroup) {
        cooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void blasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                            float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected void smoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                            float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smoking");
    }

    protected <T extends AbstractCookingRecipe> void cooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                             List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EFAC.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
