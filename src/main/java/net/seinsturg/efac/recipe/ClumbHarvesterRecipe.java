package net.seinsturg.efac.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record ClumbHarvesterRecipe(Ingredient inputItem, ItemStack outputL, ItemStack outputM, ItemStack outputR) implements Recipe<ClumbHarvesterRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(ClumbHarvesterRecipeInput clumbHarvesterRecipeInput, Level level) {
        if (level.isClientSide) {
            return false;
        }

        return inputItem.test(clumbHarvesterRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(ClumbHarvesterRecipeInput clumbHarvesterRecipeInput, HolderLookup.Provider provider) {
        return outputL;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return outputL;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EvansRecipes.CLUMB_HARVESTER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return EvansRecipes.CLUMB_HARVESTER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ClumbHarvesterRecipe> {
        public static final MapCodec<ClumbHarvesterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ClumbHarvesterRecipe::inputItem),
                ItemStack.CODEC.fieldOf("resultL").forGetter(ClumbHarvesterRecipe::outputL),
                ItemStack.CODEC.fieldOf("resultM").forGetter(ClumbHarvesterRecipe::outputM),
                ItemStack.CODEC.fieldOf("resultR").forGetter(ClumbHarvesterRecipe::outputR)
        ).apply(inst, ClumbHarvesterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ClumbHarvesterRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ClumbHarvesterRecipe::inputItem,
                        ItemStack.STREAM_CODEC, ClumbHarvesterRecipe::outputL,
                        ItemStack.STREAM_CODEC, ClumbHarvesterRecipe::outputM,
                        ItemStack.STREAM_CODEC, ClumbHarvesterRecipe::outputR,
                        ClumbHarvesterRecipe::new);

        @Override
        public MapCodec<ClumbHarvesterRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ClumbHarvesterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
