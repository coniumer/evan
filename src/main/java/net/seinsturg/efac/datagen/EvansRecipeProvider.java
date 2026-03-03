package net.seinsturg.efac.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

import java.util.concurrent.CompletableFuture;

public class EvansRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public EvansRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    //custom id for recipe for when multiple recipes for the same item should exist:
    //.save(recipeOutput, "efac:itemWithMultipleRecipes_from_second_source");
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EvansBlocks.CLUMB_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', EvansItems.CLUMB_MATERIA.get())
                .unlockedBy("has_clumb_materia", has(EvansItems.CLUMB_MATERIA)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EvansItems.CLUMB_MATERIA.get(), 9)
                .requires(EvansBlocks.CLUMB_BLOCK)
                .unlockedBy("has_clumb_block", has(EvansBlocks.CLUMB_BLOCK)).save(recipeOutput);
    }
}
