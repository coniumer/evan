package net.seinsturg.efac.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record ClumbHarvesterRecipe(Ingredient inputItem, ItemStack outputL, ItemStack outputM, ItemStack outputR, float chanceL, float chanceM, float chanceR) implements Recipe<ClumbHarvesterRecipeInput> {

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
                ItemStack.CODEC.fieldOf("resultR").forGetter(ClumbHarvesterRecipe::outputR),
                Codec.FLOAT.optionalFieldOf("chanceL", 1f).forGetter(ClumbHarvesterRecipe::chanceL),
                Codec.FLOAT.optionalFieldOf("chanceM", 1f).forGetter(ClumbHarvesterRecipe::chanceM),
                Codec.FLOAT.optionalFieldOf("chanceR", 1f).forGetter(ClumbHarvesterRecipe::chanceR)
        ).apply(inst, ClumbHarvesterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ClumbHarvesterRecipe> STREAM_CODEC = StreamCodec.of(ClumbHarvesterRecipe.Serializer::toNetwork, ClumbHarvesterRecipe.Serializer::fromNetwork);

        private static ClumbHarvesterRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack outputL = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack outputM = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack outputR = ItemStack.STREAM_CODEC.decode(buffer);
            float chanceL = buffer.readFloat();
            float chanceM = buffer.readFloat();
            float chanceR = buffer.readFloat();

            return new ClumbHarvesterRecipe(ingredient, outputL, outputM, outputR, chanceL, chanceM, chanceR);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ClumbHarvesterRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.inputItem);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputL);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputM);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.outputR);
            ByteBufCodecs.FLOAT.encode(buffer, recipe.chanceL);
            ByteBufCodecs.FLOAT.encode(buffer, recipe.chanceM);
            ByteBufCodecs.FLOAT.encode(buffer, recipe.chanceR);
        }

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
