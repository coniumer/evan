package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMB_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.CLUMB_MATERIA)
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CLUMB_MATERIA.get(), 9)
                .requires(EvansBlocks.CLUMB_BLOCK)
                .unlockedBy("has_clumb_block", has(EvansBlocks.CLUMB_BLOCK)).save(recipeOutput);


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
