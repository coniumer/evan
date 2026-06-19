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
                EvansBlocks.COMPACT_DIRT
        );
        List<ItemLike> SLIPULON_SMELTABLES = List.of(
                EvansBlocks.SLIPULON_ORE, EvansItems.RAW_SLIPULON
        );

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
