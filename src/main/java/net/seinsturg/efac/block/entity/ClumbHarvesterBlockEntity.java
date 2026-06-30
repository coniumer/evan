package net.seinsturg.efac.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.screen.custom.ClumbHarvesterMenu;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void harvest() {
        inventory.extractItem(INPUT_SLOT_INDEX, 1, false);

        ItemStack outputL = new ItemStack(EvansBlocks.NILENE_BLOCK, 1);
        ItemStack outputM = new ItemStack(EvansBlocks.YES, 1);
        ItemStack outputR = new ItemStack(EvansBlocks.CLUMB_BLOCK, 1);
        List<ItemStack> outputs = List.of(outputL, outputM, outputR);

        for (int i = 0; i < 3; i++) {
            inventory.setStackInSlot(i + 1, new ItemStack(outputs.get(i).getItem(), inventory.getStackInSlot(i + 1).getCount() + outputs.get(i).getCount()));
        }
    }

    private boolean hasRecipe() {
        ItemStack outputL = new ItemStack(EvansBlocks.NILENE_BLOCK, 1);
        ItemStack outputM = new ItemStack(EvansBlocks.YES, 1);
        ItemStack outputR = new ItemStack(EvansBlocks.CLUMB_BLOCK, 1);

        return inventory.getStackInSlot(INPUT_SLOT_INDEX).is(Blocks.DIORITE.asItem())
                && canInsertItemIntoOutputSlot(outputL, OUTPUT_SLOT_L) && canInsertAmountIntoOutputSlot(outputL.getCount(), OUTPUT_SLOT_L)
                && canInsertItemIntoOutputSlot(outputM, OUTPUT_SLOT_M) && canInsertAmountIntoOutputSlot(outputM.getCount(), OUTPUT_SLOT_M)
                && canInsertItemIntoOutputSlot(outputR, OUTPUT_SLOT_R) && canInsertAmountIntoOutputSlot(outputR.getCount(), OUTPUT_SLOT_R);
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
