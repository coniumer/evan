package net.seinsturg.efac.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seinsturg.efac.recipe.ClumbHarvesterRecipe;
import net.seinsturg.efac.recipe.ClumbHarvesterRecipeInput;
import net.seinsturg.efac.recipe.EvansRecipes;
import net.seinsturg.efac.screen.custom.ClumbHarvesterMenu;
import net.seinsturg.efac.sound.EvansSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ClumbHarvesterBlockEntity extends BlockEntity implements MenuProvider {
    public static final int SIZE = 4;
    public final ItemStackHandler inventory = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private static final int INPUT_SLOT_INDEX = 0;
    private static final int OUTPUT_SLOT_L = 1;
    private static final int OUTPUT_SLOT_M = 2;
    private static final int OUTPUT_SLOT_R = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public ClumbHarvesterBlockEntity(BlockPos pos, BlockState blockState) {
        super(EvansBlockEntities.CLUMB_HARVESTER_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> ClumbHarvesterBlockEntity.this.progress;
                    case 1 -> ClumbHarvesterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: ClumbHarvesterBlockEntity.this.progress = value;
                    case 1: ClumbHarvesterBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void tick(Level useLevel, BlockPos blockPos, BlockState blockState) {
        if (hasRecipe()) {
            increaseProgress();
            setChanged(useLevel, blockPos, blockState);

            if (hasCraftingFinished()) {
                harvest();
                useLevel.playSound(null, blockPos, EvansSounds.HARVESTER_HARVEST.get(), SoundSource.BLOCKS, 0.7f + (level.random.nextFloat() * 0.3f), 1f);
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void harvest() {
        Optional<RecipeHolder<ClumbHarvesterRecipe>> recipe = getCurrentRecipe();

        ItemStack outputL = recipe.get().value().outputL();
        ItemStack outputM = recipe.get().value().outputM();
        ItemStack outputR = recipe.get().value().outputR();

        float chanceL = recipe.get().value().chanceL();
        float chanceM = recipe.get().value().chanceM();
        float chanceR = recipe.get().value().chanceR();

        inventory.extractItem(INPUT_SLOT_INDEX, 1, false);

        List<ItemStack> outputs = List.of(outputL, outputM, outputR);
        float[] chances = { chanceL, chanceM, chanceR };

        for (int i = 0; i < 3; i++) {
            if (shouldFillSlot(chances[i])) {
                inventory.setStackInSlot(i + 1, new ItemStack(outputs.get(i).getItem(), inventory.getStackInSlot(i + 1).getCount() + outputs.get(i).getCount()));
            }
        }
    }

    private boolean shouldFillSlot(float chance) {
        float roll = level.random.nextFloat();
        return roll <= chance;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<ClumbHarvesterRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack outputL = recipe.get().value().outputL();
        ItemStack outputM = recipe.get().value().outputM();
        ItemStack outputR = recipe.get().value().outputR();

        return canInsertItemIntoOutputSlot(outputL, OUTPUT_SLOT_L) && canInsertAmountIntoOutputSlot(outputL.getCount(), OUTPUT_SLOT_L)
            && canInsertItemIntoOutputSlot(outputM, OUTPUT_SLOT_M) && canInsertAmountIntoOutputSlot(outputM.getCount(), OUTPUT_SLOT_M)
            && canInsertItemIntoOutputSlot(outputR, OUTPUT_SLOT_R) && canInsertAmountIntoOutputSlot(outputR.getCount(), OUTPUT_SLOT_R);
    }

    private Optional<RecipeHolder<ClumbHarvesterRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(EvansRecipes.CLUMB_HARVESTER_TYPE.get(), new ClumbHarvesterRecipeInput(inventory.getStackInSlot(INPUT_SLOT_INDEX)), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output, int slot) {
        return inventory.getStackInSlot(slot).isEmpty() ||
                inventory.getStackInSlot(slot).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count, int slot) {
        int maxCount = inventory.getStackInSlot(slot).isEmpty() ? 64 : inventory.getStackInSlot(slot).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(slot).getCount();

        return maxCount >= currentCount + count;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void increaseProgress() {
        progress++;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("clumb_harvester.progress", progress);
        tag.putInt("clumb_harvester.max_progress", maxProgress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        tag.getInt("clumb_harvester.progress");
        tag.getInt("clumb_harvester.max_progress");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.efac.clumb_harvester");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ClumbHarvesterMenu(i, inventory, this, data);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
